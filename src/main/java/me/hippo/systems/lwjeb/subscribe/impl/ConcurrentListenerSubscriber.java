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

package me.hippo.systems.lwjeb.message.impl;

import me.hippo.systems.lwjeb.collector.SubscriptionCollector;
import me.hippo.systems.lwjeb.message.EventRegistry;
import me.hippo.systems.lwjeb.message.MessageHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <h1>The Immediate Event Registry</h1>
 * An implementation of {@link EventRegistry},
 * stores registered events in {@link CopyOnWriteArrayList}.
 *
 * @author Hippo
 * @since 1/6/2019
 */
public final class ConcurrentEventRegistry extends EventRegistry<CopyOnWriteArrayList<MessageHandler>> {

    /**
     * The event map.
     */
    private final HashMap<Class<?>, CopyOnWriteArrayList<MessageHandler>> eventMap = new HashMap<>();

    /**
     * Creates a new {@link ConcurrentEventRegistry} with the desired collector.
     *
     * @param collector  The collector.
     */
    public ConcurrentEventRegistry(final SubscriptionCollector collector) {
        super(collector);
    }

    /**
     * Registers {@code parent}.
     * <p>
     *     Loops through the cached {@link MessageHandler}s,
     *     then adds it to the {@link CopyOnWriteArrayList} that is mapped to the event class,
     *     if the {@link CopyOnWriteArrayList} doesn't exist it will create one.
     * </p>
     *
     * @param parent  The object to register.
     */
    @Override
    public void register(final Object parent) {
        for(final MessageHandler messageHandler : getCachedSubscriptions(parent)){
            eventMap.computeIfAbsent(messageHandler.getEvent(), ignored -> new CopyOnWriteArrayList<>()).add(messageHandler);
        }
    }

    /**
     * Gets the event map.
     * <p>
     *     Maps a class (the event) to a {@link CopyOnWriteArrayList} of {@link MessageHandler}s.
     *     The {@link MessageHandler}s will be registered under the event.
     * </p>
     *
     * @return  The event map.
     */
    @Override
    public Map<Class<?>, CopyOnWriteArrayList<MessageHandler>> getEventMap() {
        return eventMap;
    }
}
