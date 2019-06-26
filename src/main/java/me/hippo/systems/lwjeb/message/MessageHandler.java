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

/**
 * <h1>The Subscription</h1>
 * * An abstract framework to make {@link Subscription}s.
 * * Each {@link Subscription} must implement {@link #invoke(Object)},
 * * they all have an event that they are subscribed to.
 *
 * @author Hippo
 * @since 11/6/2018
 */
public abstract class Subscription {

    /**
     * The event that the {@link Subscription} is subscribed to.
     */
    private final Class<?> event;

    /**
     * Creates a new {@link Subscription} with the desired event.
     *
     * @param event  The event.
     */
    public Subscription(final Class<?> event) {
        this.event = event;
    }

    /**
     * Invokes the subscription.
     *
     * @param event  The event.
     */
    public abstract void invoke(final Object event);

    /**
     * Gets get event.
     *
     * @return  The event.
     */
    public final Class<?> getEvent(){
        return event;
    }
}
