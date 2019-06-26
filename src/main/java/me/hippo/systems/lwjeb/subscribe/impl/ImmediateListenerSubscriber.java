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

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <h1>The Immediate Listener Subscriber</h1>
 * An implementation of {@link ListenerSubscriber},
 * stores message handlers in {@link ArrayList}.
 *
 * @author Hippo
 * @since 1/6/2019
 */
public final class ImmediateListenerSubscriber extends ListenerSubscriber<ArrayList<MessageHandler>, HashMap<Class<?>, ArrayList<MessageHandler>>> {

    private final HashMap<Class<?>, ArrayList<MessageHandler>> eventMap;


    /**
     * @InheritDoc
     */
    public ImmediateListenerSubscriber(SubscriptionCollector collector) {
        super(new HashMap<>(), collector);
        this.eventMap = new HashMap<>();
    }


    /**
     * @InheritDoc
     */
    @Override
    public void subscribe(Object parent) {
        for(MessageHandler messageHandler : getCachedMessageHanlders(parent)){
            eventMap.computeIfAbsent(messageHandler.getTopic(), ignored -> new ArrayList<>()).add(messageHandler);
        }
    }

    /**
     * @InheritDoc
     */
    @Override
    public HashMap<Class<?>, ArrayList<MessageHandler>> getEventMap() {
        return eventMap;
    }
}
