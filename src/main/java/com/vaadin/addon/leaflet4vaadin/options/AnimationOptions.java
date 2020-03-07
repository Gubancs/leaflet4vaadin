// Copyright 2020 Gabor Kokeny and contributors
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.vaadin.addon.leaflet4vaadin.options;

import java.io.Serializable;

public interface AnimationOptions extends Serializable {

    default boolean isZoomAnimation() {
        return true;
    }

    void setZoomAnimation(boolean zoomAnimation);

    default int getZoomAnimationThreshold() {
        return 4;
    }

    void setZoomAnimationThreshold(int zoomAnimationThreshold);

    default boolean isFadeAnimation() {
        return true;
    }

    void setFadeAnimation(boolean fadeAnimation);

    default boolean isMarkerZoomAnimation() {
        return true;
    }

    void setMarkerZoomAnimation(boolean markerZoomAnimation);

    default double getTransform3DLimit() {
        return Math.pow(2, 23);
    }

    void setTransform3DLimit(double transform3DLimit);
}
