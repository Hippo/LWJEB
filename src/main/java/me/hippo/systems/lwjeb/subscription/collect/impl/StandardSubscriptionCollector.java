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

import me.hippo.systems.lwjeb.subscription.Subscription;
import me.hippo.systems.lwjeb.subscription.collect.SubscriptionCollector;
import me.hippo.systems.lwjeb.subscription.exception.SubscriptionCollectException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@link StandardSubscriptionCollector} this will collect {@link me.hippo.systems.lwjeb.subscription.impl.FieldSubscription}'s and {@link me.hippo.systems.lwjeb.subscription.impl.MethodSubscription}'s as long as they meet the requirements.'
 *
 *  <p>
 *     This will utilize both of the default collectors, {@link MethodBasedSubscriptionCollector} and {@link FieldBasedSubscriptionCollector}.
 * </p>
 */
public enum StandardSubscriptionCollector implements SubscriptionCollector {

    /**
     * An instance to {@link StandardSubscriptionCollector}.
     */
    INSTANCE;

    /**
     * Collects all the  {@link Subscription}s that meet the requirements.
     * @param parent  The parent to collect from.
     * @return  The {@link Collection} of {@link Subscription}s.
     * @throws IllegalAccessException  If the access to any of the fields/methods are illegal.
     * @throws SubscriptionCollectException  If any of the fields/methods don't meet the requirements.
     */
    @Override
    public Collection<Subscription> collectSubscriptions(final Object parent) throws IllegalAccessException, SubscriptionCollectException {
        Collection<Subscription> subscriptions = new ArrayList<>();
        subscriptions.addAll(SubscriptionCollector.method().collectSubscriptions(parent));
        subscriptions.addAll(SubscriptionCollector.field().collectSubscriptions(parent));
        return subscriptions;
    }
}
