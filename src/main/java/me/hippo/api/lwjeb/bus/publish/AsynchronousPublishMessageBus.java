/*
 * Copyright 2020 Hippo
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

package me.hippo.api.lwjeb.bus.publish;


import me.hippo.api.lwjeb.message.result.MessagePublicationResult;

import java.util.concurrent.TimeUnit;

/**
 * @author Hippo
 * @version 5.0.0, 10/26/19
 * @since 5.0.0
 *
 * An asynchronous publisher allows messages to be published asynchronously.
 */
public interface AsynchronousPublishMessageBus<T> extends PublishMessageBus<T> {

    /**
     * Sets up all the dispatchers.
     *
     * <p>
     *     A dispatcher is a thread that is dedicated to handling topics.
     * </p>
     */
    void setupDispatchers();

    /**
     * Adds a publish result to the asynchronous queue.
     *
     * @param result  The result.
     */
    void addMessage(MessagePublicationResult<T> result);

    /**
     * Adds a publish result to the asynchronous queue, if the result hasn't been handled in enough time then it will be removed from the queue.
     *
     * @param result  The result.
     * @param timeout  The timout.
     * @param timeUnit  The time unit.
     */
    void addMessage(MessagePublicationResult<T> result, long timeout, TimeUnit timeUnit);

    /**
     * Shuts down all the dispatchers.
     *
     * <p>
     *     This is only used if the dispatchers have been started.
     * </p>
     */
    void shutdown();
}
