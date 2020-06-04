package com.vaadin.addon.leaflet4vaadin;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.addon.leaflet4vaadin.annotations.LeafletArgument;
import com.vaadin.addon.leaflet4vaadin.layer.Identifiable;
import com.vaadin.addon.leaflet4vaadin.layer.map.functions.ExecutableFunctions;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(value = { "json" })
public abstract class LeafletObject implements ExecutableFunctions, Serializable {
    private static final long serialVersionUID = -1268468998583672106L;
    private String json;
    private String uuid;
    private ExecutableFunctions parent;
    private final ObjectMapper objectMapper = new ObjectMapper();

    protected LeafletObject() {
        configureObjectMapper(objectMapper);
        this.uuid = UUID.randomUUID().toString();
    }

    protected void configureObjectMapper(final ObjectMapper objectMapper) {
    }

    @Override
    public String getUuid() {
        return this.uuid;
    }
    
    public String getJson() {
        if (this.json == null) {
            try {
                this.json = objectMapper.writeValueAsString(this);
                return this.json;
            } catch (IOException e) {
                throw new RuntimeException("Unable to convert Layer into JSON type", e);
            }
        }
        return this.json;
    }

    public List<String> getConstructorArgumentNames() {
        return findLeafletArguments(getClass()).stream().map(Field::getName).distinct().collect(Collectors.toList());
    }

    public String getLeafletType() {
        return getClass().getSimpleName();
    }

    static List<Field> findLeafletArguments(Class<?> layer) {
        List<Field> fields = Arrays.asList(layer.getDeclaredFields()).stream()
                .filter(f -> f.isAnnotationPresent(LeafletArgument.class))
                .sorted(Comparator.comparingInt(field-> field.getAnnotation(LeafletArgument.class).index()))
                .collect(Collectors.toList());
        if (layer.getSuperclass() != null) {
            fields.addAll(findLeafletArguments(layer.getSuperclass()));
        }
        return fields;
    }
    
    @Override
    public void executeJs(Identifiable target, String functionName, Serializable... arguments) {
        if (parent instanceof ExecutableFunctions) {
            parent.executeJs(target, functionName, arguments);
        }
    }

    @Override
    public <T extends Serializable> CompletableFuture<T> call(Identifiable target, String functionName, Class<T> resultType, Serializable... arguments) {
        if (parent instanceof ExecutableFunctions) {
            return parent.call(target, functionName, resultType, arguments);
        } else {
            return null;
        }
    }
    
    protected void setParent(ExecutableFunctions parent) {
        this.parent = parent;
    }
    
    protected ExecutableFunctions getParent() {
        return parent;
    }
}
