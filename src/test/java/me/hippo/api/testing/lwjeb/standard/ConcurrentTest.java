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

import me.hippo.api.lwjeb.annotation.Handler;
import me.hippo.api.lwjeb.bus.PubSub;
import me.hippo.api.lwjeb.configuration.BusConfigurations;
import me.hippo.api.lwjeb.configuration.config.impl.BusPubSubConfiguration;
import me.hippo.api.lwjeb.subscribe.impl.StrongReferencedListenerSubscriber;


/**
 * @author Hippo
 * @version 5.0.0, 11/2/19
 * @since 5.0.0
 */
public enum ConcurrentTest {
    INSTANCE;

    public static void main(String[] args) {

        PubSub<Event> pubSub = new PubSub<>(new BusConfigurations.Builder().setConfiguration(BusPubSubConfiguration.class, () -> {
            BusPubSubConfiguration busPubSubConfiguration = BusPubSubConfiguration.getDefault();
            busPubSubConfiguration.setSubscriber(new StrongReferencedListenerSubscriber<>());
            return busPubSubConfiguration;
        }).build());
        pubSub.subscribe(INSTANCE);

        pubSub.post(new MyEvent(pubSub)).dispatch();
    }

    @Handler
    public void onMessage(MyEvent message) {
        message.test();
    }

    public static abstract class Event {
        protected PubSub<?> pubSub;

        public Event(PubSub<?> pubSub) {
            this.pubSub = pubSub;
        }


        public abstract void test();
    }

    public static final class MyEvent extends Event {
        public MyEvent(PubSub<?> pubSub) {
            super(pubSub);
        }

        @Override
        public void test() {
            pubSub.unsubscribe(INSTANCE);
            System.out.println("nignog");
        }
    }


}
