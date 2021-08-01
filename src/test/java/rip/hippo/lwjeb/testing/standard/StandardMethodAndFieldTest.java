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

package rip.hippo.lwjeb.testing.standard;

import rip.hippo.lwjeb.annotation.Handler;
import rip.hippo.lwjeb.bus.PubSub;
import rip.hippo.lwjeb.configuration.BusConfigurations;
import rip.hippo.lwjeb.configuration.config.impl.BusPubSubConfiguration;
import rip.hippo.lwjeb.message.scan.impl.MethodAndFieldBasedMessageScanner;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * @author Hippo
 * @version 5.0.0, 1/12/20
 * @since 5.0.0
 */
public final class StandardMethodAndFieldTest {

    @Test
    public void test() {

        PubSub<String> pubSub = new PubSub<>(new BusConfigurations.Builder().setConfiguration(
                BusPubSubConfiguration.class, () -> {
                    BusPubSubConfiguration busPubSubConfiguration = BusPubSubConfiguration.getDefault();
                    busPubSubConfiguration.setScanner(new MethodAndFieldBasedMessageScanner<>());
                    return busPubSubConfiguration;
                }
        ).build());

        pubSub.subscribe(this);

        pubSub.post("first").dispatch();
        pubSub.post("second").dispatch();
    }


    @Handler
    public final Consumer<String> onMessage = message -> System.out.println("from field: " + message);

    @Handler
    public void onMessage(String message) {
        System.out.println("from method: " + message);
    }
}
