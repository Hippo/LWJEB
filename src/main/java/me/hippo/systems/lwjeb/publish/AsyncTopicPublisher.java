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

package me.hippo.systems.lwjeb.publish;

import me.hippo.systems.lwjeb.exception.EventBusException;
import me.hippo.systems.lwjeb.message.MessageHandler;
import me.hippo.systems.lwjeb.message.impl.ConcurrentListenerSubscriber;
import me.hippo.systems.lwjeb.subscribe.ListenerSubscriber;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <h1>The Concurrent MessageHandler Publish</h1>
 *
 * @author Hippo
 * @since 1/28/2019
 */
public final class AsycTopicPublisher implements SubscriptionPublisher {

    /**
     * The {@link ConcurrentListenerSubscriber} instance.
     */
    private final ConcurrentListenerSubscriber registry;

    /**
     * Creates a new {@link AsycTopicPublisher} with an {@link ConcurrentListenerSubscriber} instance.
     *
     * @param registry  The {@link ConcurrentListenerSubscriber}.
     */
    public AsycTopicPublisher(final ConcurrentListenerSubscriber registry){
        this.registry = registry;
    }

    /**
     * Post's a message to the {@link me.hippo.systems.lwjeb.EventBus} saying to invoke all the {@link MessageHandler}s.
     *
     * @param topic  The message topic.
     */
    public static void post(Object topic, ListenerSubscriber<?, ?> subscriber) {
        List<MessageHandler> messageHandlers = subscriber.getEventMap().get(topic.getClass());
        if(messageHandlers != null){
            for(MessageHandler messageHandler : messageHandlers){
                messageHandler.invoke(topic);
            }
        }
    }
}
