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

import me.hippo.systems.lwjeb.listener.Listener;
import me.hippo.systems.lwjeb.subscribe.Subscription;

/**
 * <h1>The Field Based Subscription</h1>
 * This is an implementation of {@link Subscription},
 * this is based around {@link java.lang.reflect.Field}s,
 * which are wrapped by {@link Listener}.
 *
 * @author Hippo
 * @since 1/5/2019
 */
public final class FieldBasedSubscription extends Subscription {

    /**
     * The lister that is associated with the {@link FieldBasedSubscription}.
     */
    private final Listener listener;

    /**
     * Creates a new {@link FieldBasedSubscription} with the desired listener and event.
     *
     * @param listener  The listener.
     * @param event  The event.
     */
    public FieldBasedSubscription(final Listener<?> listener, final Class<?> event) {
        super(event);
        this.listener = listener;
    }

    /**
     * Invoked the {@link FieldBasedSubscription}.
     *
     * @param event  The event.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void invoke(final Object event) {
        listener.invoke(event);
    }
}
