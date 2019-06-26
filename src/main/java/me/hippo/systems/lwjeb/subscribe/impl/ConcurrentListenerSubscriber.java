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

package me.hippo.systems.lwjeb.subscribe.impl;

import me.hippo.systems.lwjeb.collector.SubscriptionCollector;
import me.hippo.systems.lwjeb.subscribe.ListenerSubscriber;
import me.hippo.systems.lwjeb.message.MessageHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <h1>The Concurrent Listener Subscriber</h1>
 * An implementation of {@link ListenerSubscriber},
 * stores message handlers in {@link CopyOnWriteArrayList}.
 *
 * @author Hippo
 * @since 1/6/2019
 */
public final class ConcurrentListenerSubscriber extends ListenerSubscriber<CopyOnWriteArrayList<MessageHandler>, ConcurrentHashMap<Class<?>, CopyOnWriteArrayList<MessageHandler>>> {

    private final ConcurrentHashMap<Class<?>, CopyOnWriteArrayList<MessageHandler>> eventMap;

    public ConcurrentListenerSubscriber(SubscriptionCollector collector) {
        super(new ConcurrentHashMap<>(), collector);
        this.eventMap = new ConcurrentHashMap<>();
    }

    /**
     * @InheritDoc
     */
    @Override
    public void subscribe(Object parent) {
        for(MessageHandler messageHandler : getCachedMessageHanlders(parent)){
            eventMap.computeIfAbsent(messageHandler.getTopic(), ignored -> new CopyOnWriteArrayList<>()).add(messageHandler);
        }
    }

    /**
     * @InheritDoc
     */
    @Override
    public ConcurrentHashMap<Class<?>, CopyOnWriteArrayList<MessageHandler>> getEventMap() {
        return eventMap;
    }
}
