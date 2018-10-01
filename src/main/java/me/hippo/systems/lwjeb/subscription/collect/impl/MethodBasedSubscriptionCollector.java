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

package me.hippo.systems.lwjeb.subscription.collect.impl;

import me.hippo.systems.lwjeb.annotation.Collect;
import me.hippo.systems.lwjeb.subscription.Subscription;
import me.hippo.systems.lwjeb.subscription.collect.SubscriptionCollector;
import me.hippo.systems.lwjeb.subscription.exception.SubscriptionCollectException;
import me.hippo.systems.lwjeb.subscription.impl.MethodSubscription;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@link SubscriptionCollector} that collects methods.
 *
 * <p>
 *     The method must meet the requirements, @see {@link Collect} for the requirements.
 * </p>
 * @author Hippo
 * @since 9/26/2018
 */
public enum MethodBasedSubscriptionCollector implements SubscriptionCollector {

    /**
     * An instance to {@link MethodBasedSubscriptionCollector}.
     */
    INSTANCE;

    /**
     * Collects the {@link MethodSubscription}s that meet the requirements.
     * @param parent  The parent to collect from.
     * @return  The {@link Collection} of {@link MethodSubscription}s.
     * @throws SubscriptionCollectException  If the method does not meet the requirements.
     */
    @Override
    public Collection<Subscription> collectSubscriptions(final Object parent) throws SubscriptionCollectException {
        final Collection<Subscription> subscriptions = new ArrayList<>();

        for(final Method method : parent.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(Collect.class)){
                if(method.getParameters().length != 1){
                    throw new SubscriptionCollectException("Error method '"+method.getName()+"', class '"+method.getDeclaringClass().getName()+"' does not meet the requirements for collection!");
                }
                method.setAccessible(true);
                final MethodSubscription subscription = new MethodSubscription(parent, method, method.getParameters()[0].getType());
                subscriptions.add(subscription);
            }
        }
        return subscriptions;
    }
}
