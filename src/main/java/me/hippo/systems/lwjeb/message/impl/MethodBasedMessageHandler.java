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

import me.hippo.systems.lwjeb.lambda.EventInvocation;
import me.hippo.systems.lwjeb.subscribe.Subscription;


/**
 * <h1>The Method Based Subscription</h1>
 * This is an implementation of {@link Subscription},
 * this is based around {@link java.lang.reflect.Method}s,
 * they are invoked using the {@link java.lang.invoke.LambdaMetafactory}.
 *
 * @author Hippo
 * @since 11/6/2018
 */
public final class MethodBasedSubscription extends Subscription {

    /**
     * The object that the method is associated with.
     */
    private final Object parent;

    /**
     * The {@link EventInvocation}, invokes the method.
     */
    private final EventInvocation invoker;

    /**
     * Creates a new {@link MethodBasedSubscription} with the desired parent, invoker, and event.
     *
     * @param parent  The parent.
     * @param invoker  The invoker.
     * @param event  The event.
     */
    public MethodBasedSubscription(final Object parent, final EventInvocation invoker, final Class<?> event) {
        super(event);
        this.parent = parent;
        this.invoker = invoker;
    }

    /**
     * Invokes the {@link MethodBasedSubscription} with the {@link #parent} as the instance and {@code event} as the parameter.
     *
     * @param event  The event.
     */
    @Override
    public void invoke(final Object event) {
        invoker.invoke(parent, event);
    }
}
