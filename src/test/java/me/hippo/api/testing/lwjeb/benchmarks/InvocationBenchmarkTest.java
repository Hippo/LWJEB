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

package me.hippo.api.testing.lwjeb.benchmarks;

import me.hippo.api.lwjeb.annotation.Handler;
import me.hippo.api.lwjeb.bus.PubSub;
import me.hippo.api.lwjeb.configuration.BusConfigurations;
import me.hippo.api.lwjeb.configuration.config.impl.BusPubSubConfiguration;
import me.hippo.api.lwjeb.message.publish.impl.ExperimentalMessagePublisher;

/**
 * @author Hippo
 * @version 5.0.0, 1/16/2020
 * @since 5.0.0
 */
public final class InvocationBenchmarkTest {

    public static void main(String[] args) {
        InvocationBenchmarkTest publishBenchmarkTest = new InvocationBenchmarkTest();
        publishBenchmarkTest.benchmark();
    }

    private void benchmark() {
        PubSub<String> pubSub = new PubSub<>(new BusConfigurations.Builder().setConfiguration(
                BusPubSubConfiguration.class, () -> {
                    BusPubSubConfiguration busPubSubConfiguration = BusPubSubConfiguration.getDefault();
                    busPubSubConfiguration.setPublisher(new ExperimentalMessagePublisher<>());
                    return busPubSubConfiguration;
                }
        ).build());

        pubSub.subscribe(this);

        long start = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            pubSub.post("").dispatch();
        }
        long finish = System.currentTimeMillis();
        System.out.println("Finished in " + (finish - start) + "ms");
    }

    @Handler
    public void onMessage(String message){}

}
