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

package me.hippo.systems.lwjeb.subscriber;

import java.util.function.Predicate;

/**
 * @author Hippo
 * @since 06/19/2019
 */
public abstract class TopicSubscriber {

    private final Class<?> topic;
    private final Predicate<Class<?>> filter;

    public TopicSubscriber(Class<?> topic) {
        this.topic = topic;
        this.filter = topic::equals;
    }

    public abstract void invoke(Object event);

    public boolean passesFilter(Class<?> topic) {
        return filter.test(topic);
    }

    public Class<?> getTopic() {
        return topic;
    }
}
