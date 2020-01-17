/*
 * Copyright 2020 Hippo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package me.hippo.api.testing.lwjeb.standard;

import me.hippo.api.lwjeb.annotation.Filter;
import me.hippo.api.lwjeb.annotation.Handler;
import me.hippo.api.lwjeb.bus.PubSub;
import me.hippo.api.testing.lwjeb.filter.StringCasingFilter;


/**
 * @author Hippo
 * @version 5.0.0, 1/16/2020
 * @since 5.0.0
 */
public enum StandardFiltrationTest {
    INSTANCE;

    public static void main(String[] args) {

        PubSub<String> pubSub = new PubSub<>();
        pubSub.subscribe(INSTANCE);

        pubSub.post("message").dispatch();
        pubSub.post("MESSAGE").dispatch();
    }

    @Handler
    @Filter(StringCasingFilter.class)
    public void onMessage(String message) {
        System.out.println(message);
    }


}
