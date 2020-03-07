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

package com.vaadin.addon.leaflet4vaadin.testbench;

import com.vaadin.testbench.TestBenchElement;

import org.junit.Assert;
import org.junit.Test;

public class ViewIT extends AbstractViewTest {

    @Test
    public void componentWorks() {
        final TestBenchElement leaflet = $("leaflet").first();
        // Check that paper-slider contains at least one other element, which means that
        // is has been upgraded to a custom element and not just rendered as an empty
        // tag
        Assert.assertTrue(
            leaflet.$(TestBenchElement.class).all().size() > 0);
    }
}
