/*
 * MIT License
 *
 * Copyright (c) 2018 Hippo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.hippo.systems.lwjeb.subscription.impl;

import me.hippo.systems.lwjeb.listener.Listener;
import me.hippo.systems.lwjeb.subscription.Subscription;


/**
 * A {@link Subscription} on a {@link Listener} with the event it is invoking.
 *
 * @author Hippo
 * @since 9/26/2018
 */
public final class FieldSubscription<T> implements Subscription<T> {

    /**
     * The {@link Listener} of the {@link Subscription}.
     */
    private final Listener<T> listener;

    /**
     * The event from the {@link Listener}.
     */
    private final Class<T> event;

    /**
     * Makes a new {@link FieldSubscription} with a {@link Listener} and event.
     * @param listener  The {@link Subscription}'s {@link Listener}.
     * @param event  The {@link Listener}'s event.
     */
    public FieldSubscription(final Listener<T> listener, final Class<T> event) {
        this.listener = listener;
        this.event = event;
    }

    /**
     * Invokes the event.
     * @param event  The event to invoke.
     */
    @Override
    public void invoke(T event) {
        listener.invoke(event);
    }

    /**
     * Gets the {@link Subscription}'s event.
     * @return  The event.
     */
    @Override
    public Class<T> getEvent() {
        return event;
    }
}
