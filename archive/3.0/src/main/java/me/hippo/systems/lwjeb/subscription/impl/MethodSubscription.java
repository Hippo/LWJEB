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

import me.hippo.systems.lwjeb.subscription.Subscription;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A {@link Subscription} on a method with the event it is invoking.
 *
 * @author Hippo
 * @since 9/26/2018
 */
public final class MethodSubscription<T> implements Subscription<T> {

    /**
     * The method's parent.
     */
    private final Object parent;

    /**
     * The {@link Subscription}'s method.
     */
    private final Method method;

    /**
     * The method's event.
     */
    private final Class<T> event;

    /**
     * Creates a new MethodSubscription with a parent and its method and its event.
     * @param parent  The parent.
     * @param method  The method.
     * @param event  The event.
     */
    public MethodSubscription(final Object parent, final Method method, final Class<T> event) {
        this.parent = parent;
        this.method = method;
        this.event = event;
    }

    /**
     * Invokes the event.
     * @param event  The event to invoke.
     * @throws IllegalAccessException  If the access is illegal.
     * @throws InvocationTargetException  If the invoke fails.
     */
    @Override
    public void invoke(final T event) throws IllegalAccessException, InvocationTargetException {
        method.invoke(parent, event);
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
