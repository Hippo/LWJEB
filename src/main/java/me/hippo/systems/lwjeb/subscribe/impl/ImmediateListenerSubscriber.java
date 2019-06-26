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

package me.hippo.systems.lwjeb.registry.impl;

import me.hippo.systems.lwjeb.collect.TopicSubscriberCollector;
import me.hippo.systems.lwjeb.registry.EventRegistry;
import me.hippo.systems.lwjeb.subscriber.TopicSubscriber;

import java.util.*;

/**
 * @author Hippo
 * @since 06/22/2019
 */
public final class ImmediateEventRegistry extends EventRegistry<ArrayList<TopicSubscriber>, HashMap<Class<?>, ArrayList<TopicSubscriber>>> {

    private final HashMap<Class<?>, ArrayList<TopicSubscriber>> eventMap;

    public ImmediateEventRegistry(TopicSubscriberCollector collector) {
        super(new HashMap<>(), collector);
        this.eventMap = new HashMap<>();
    }

    @Override
    public void register(Object parent) {
        List<TopicSubscriber> cache = getCachedSubscribers(parent);
        for (TopicSubscriber topicSubscriber : cache) {
            eventMap.computeIfAbsent(topicSubscriber.getTopic(),  ignored -> new ArrayList<>()).add(topicSubscriber);
        }
    }

    @Override
    public HashMap<Class<?>, ArrayList<TopicSubscriber>> getEventMap() {
        return eventMap;
    }
}
