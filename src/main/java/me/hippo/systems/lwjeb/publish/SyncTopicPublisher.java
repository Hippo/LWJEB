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

import me.hippo.systems.lwjeb.message.MessageHandler;
import me.hippo.systems.lwjeb.subscribe.ListenerSubscriber;

import java.util.List;

/**
 * <h1>The Sync MessageHandler Publish</h1>
 * Post messages to the {@link me.hippo.systems.lwjeb.EventBus}.
 *
 * @author Hippo
 * @since 06/23/2019
 */
public final class SyncTopicPublisher {

    private SyncTopicPublisher(){
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
