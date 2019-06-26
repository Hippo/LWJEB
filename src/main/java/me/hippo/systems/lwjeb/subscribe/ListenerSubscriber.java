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

package me.hippo.systems.lwjeb.subscribe;

import me.hippo.systems.lwjeb.collector.SubscriptionCollector;
import me.hippo.systems.lwjeb.message.MessageHandler;

import java.util.*;

/**
 * <h1>The Event Registry</h1>
 * Provides an abstract framework to make event registries.
 * An {@link ListenerSubscriber} must handle the (un)registration of objects.
 *
 * @author Hippo
 * @since 1/6/2018
 * @param <T>  The type of list to store registered events in.
 */
public abstract class ListenerSubscriber<T extends List<MessageHandler>, S extends Map<Class<?>, T>> {

    /**
     * The cache map.
     * <p>
     *     Stores an object with all its {@link MessageHandler}s, they will remain in the cache even when the object is unsubscribed.
     * </p>
     */
    private final Map<Object, List<MessageHandler>> cacheMap;

    /**
     * The {@link SubscriptionCollector}, used to me.hippo.systems.lwjeb.collect {@link MessageHandler}s in objects.
     */
    private final SubscriptionCollector collector;

    /**
     * Creates a new {@link ListenerSubscriber} with the desired collector and cache.
     *
     * @param collector  The collector.
     * @param cacheMap  The cache.
     */
    public ListenerSubscriber(Map<Object, List<MessageHandler>> cacheMap, SubscriptionCollector collector) {
        this.cacheMap = cacheMap;
        this.collector = collector;
    }

    /**
     * Subscribes the {@code parent}.
     *
     * @param parent  The object to subscribe.
     */
    public abstract void subscribe(Object parent);

    /**
     * Unsubscribes the {@code parent}.
     *
     * @param parent  The object to unsubscribe.
     */
    public void unsubscribe(Object parent){
        List<MessageHandler> cache = getCachedMessageHanlders(parent);
        for (MessageHandler messageHandler : cache) {
            List<MessageHandler> messageHandlers = getEventMap().get(messageHandler.getTopic());
            if(messageHandlers != null) {
                getEventMap().values().removeIf(messageHandlers::equals);
            }
        }
    }

    /**
     * Gets all the {@link MessageHandler}s from {@code parent} in the cache map.
     * <p>
     *     If the {@code parent} is not in the cache it will me.hippo.systems.lwjeb.collect all the {@link MessageHandler}s and store it in there.
     * </p>
     *
     * @param parent  The parent to get the cached {@link MessageHandler}s.
     * @return  The cached {@link MessageHandler}s.
     */
    protected final List<MessageHandler> getCachedMessageHanlders(Object parent){
        return cacheMap.computeIfAbsent(parent, collector::collect);
    }

    /**
     * Gets the event map.
     *
     * @return  The event map.
     */
    public abstract S getEventMap();

}
