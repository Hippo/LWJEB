/*
 * Copyright 2019 Hippo
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

import collect.TopicSubscriberCollector;
import publisher.TopicPublisher;
import publisher.impl.StandardTopicPublisher;
import registry.EventRegistry;
import registry.impl.ImmediateEventRegistry;

/**
 * @author Hippo
 * @since 06/19/2019
 */
public final class EventBus {

    private final Builder builder;

    private EventBus(Builder builder) {
        this.builder = builder;
    }
    public EventBus() {
        this(new Builder().immedateRegistry().standardPublisher());
    }

    public void register(Object parent) {
        builder.eventRegistry.register(parent);
    }

    public void unregister(Object parent) {
        builder.eventRegistry.unregister(parent);
    }

    public void post(Object topic) {
        builder.topicPublisher.post(topic);
    }

    /**
     * @author Hippo
     * @since 06/23/2019
     */
    public static final class Builder {
        private TopicSubscriberCollector collector;
        private EventRegistry<?, ?> eventRegistry;
        private TopicPublisher topicPublisher;

        public Builder collectWith(TopicSubscriberCollector collector) {
            this.collector = collector;
            return this;
        }
        public Builder registerWith(EventRegistry<?, ?> eventRegistry) {
            this.eventRegistry = eventRegistry;
            return this;
        }
        public Builder immedateRegistry() {
            if(collector == null) {
                collector = TopicSubscriberCollector.method();
            }
            this.eventRegistry = new ImmediateEventRegistry(collector);
            return this;
        }

        public Builder standardPublisher() {
            if(eventRegistry == null) {
                immedateRegistry();
            }
            this.topicPublisher = new StandardTopicPublisher(eventRegistry);
            return this;
        }

        public EventBus build() {
            return new EventBus(this);
        }

    }
}
