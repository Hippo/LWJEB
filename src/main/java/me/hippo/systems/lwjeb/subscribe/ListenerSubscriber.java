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

package me.hippo.systems.lwjeb.registry;

import me.hippo.systems.lwjeb.collect.TopicSubscriberCollector;
import me.hippo.systems.lwjeb.subscriber.TopicSubscriber;

import java.util.List;
import java.util.Map;
import java.util.List;

/**
 * @author Hippo
 * @since 06/21/2019
 */
public abstract class EventRegistry<T extends List<TopicSubscriber>, S extends Map<Class<?>, T>> {

    private final Map<Object, List<TopicSubscriber>> cacheMap;
    private final TopicSubscriberCollector collector;

    public EventRegistry(Map<Object, List<TopicSubscriber>> cacheMap, TopicSubscriberCollector collector) {
        this.cacheMap = cacheMap;
        this.collector = collector;
    }

    public abstract void register(Object parent);

    public void unregister(Object parent) {
        List<TopicSubscriber> cache = getCachedSubscribers(parent);
        for (TopicSubscriber topicSubscriber : cache) {
            List<TopicSubscriber> subscribers = getEventMap().get(topicSubscriber.getTopic());
            if(subscribers != null) {
                getEventMap().values().removeIf(subscribers::equals);
            }
        }
    }

    protected List<TopicSubscriber> getCachedSubscribers(Object parent) {
        return cacheMap.computeIfAbsent(parent, collector::collect);
    }

    public abstract S getEventMap();
}
