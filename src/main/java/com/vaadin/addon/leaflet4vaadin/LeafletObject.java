package com.vaadin.addon.leaflet4vaadin;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.addon.leaflet4vaadin.annotations.LeafletArgument;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(value = { "json" })
public abstract class LeafletObject implements Serializable {
    private static final long serialVersionUID = -1268468998583672106L;
    private String json;
    private final ObjectMapper objectMapper = new ObjectMapper();

    protected LeafletObject() {
        configureObjectMapper(objectMapper);
    }

    protected void configureObjectMapper(final ObjectMapper objectMapper) {
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
                .filter(f -> f.isAnnotationPresent(LeafletArgument.class)).collect(Collectors.toList());
        if (layer.getSuperclass() != null) {
            fields.addAll(findLeafletArguments(layer.getSuperclass()));
        }
        return fields;
    }
}
