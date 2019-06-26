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

package me.hippo.systems.lwjeb.message;

/**
 * <h1>The MessageHandler</h1>
 * A message handler is an object that will execute some code via {@link #invoke(Object)} when a message is posted to the {@link #getTopic()}.
 *
 * @author Hippo
 * @since 11/6/2018
 */
public abstract class MessageHandler {

    /**
     * The topic that the {@link MessageHandler} is listening for.
     */
    private final Class<?> topic;

    /**
     * Creates a new {@link MessageHandler} with the desired topic.
     *
     * @param topic  The topic.
     */
    public MessageHandler(Class<?> topic) {
        this.topic = topic;
    }

    /**
     * Invokes the handler.
     *
     * @param topic  The topic.
     */
    public abstract void invoke(Object topic);

    /**
     * Gets get topic.
     *
     * @return  The topic.
     */
    public Class<?> getTopic(){
        return topic;
    }
}
