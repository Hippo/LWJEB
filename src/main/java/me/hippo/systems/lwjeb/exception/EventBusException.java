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

package me.hippo.systems.lwjeb.exception;

/**
 * <h1>The EventBus Exception</h1>'
 * An {@link Exception} that is thrown when the user makes an error when building the {@link me.hippo.systems.lwjeb.EventBus}.
 *
 * @author Hippo
 * @since 1/5/2019
 */
public final class EventBusException extends RuntimeException {

    /**
     * Sets the message of the error.
     *
     * @param message  The message.
     */
    public EventBusException(String message) {
        super(message);
    }
}
