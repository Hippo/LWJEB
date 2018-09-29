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

package me.hippo.systems.lwjeb.subscription.collect;

import me.hippo.systems.lwjeb.subscription.Subscription;
import me.hippo.systems.lwjeb.subscription.collect.impl.FieldBasedSubscriptionCollector;
import me.hippo.systems.lwjeb.subscription.collect.impl.MethodBasedSubscriptionCollector;
import me.hippo.systems.lwjeb.subscription.collect.impl.StandardSubscriptionCollector;
import me.hippo.systems.lwjeb.subscription.exception.SubscriptionCollectException;

import java.util.Collection;

/**
 * The {@link SubscriptionCollector} used as a framework for all the collectors.
 *
 * <p>
 *     <h>Field Based</h>
 *         <d>Collects {@link me.hippo.systems.lwjeb.subscription.impl.FieldSubscription}s.</d>
 *         <r>The field must meet the requirements found at {@link me.hippo.systems.lwjeb.annotation.Collect}.</r>
 *
 *     <h>Method Based</h>
 *         <d>Collects {@link me.hippo.systems.lwjeb.subscription.impl.MethodSubscription}s</d>
 *         <r>The method must meet the requirements found at {@link me.hippo.systems.lwjeb.annotation.Collect}.</r>
 *
 *     <h>Standard</h>
 *         <d>Collects both {@link Subscription}s, {@link me.hippo.systems.lwjeb.subscription.impl.MethodSubscription}s and {@link me.hippo.systems.lwjeb.subscription.impl.FieldSubscription}s.</d>
 *         <r>Everything must meet the requirements found at {@link me.hippo.systems.lwjeb.annotation.Collect}.</r>
 * </p>
 * @author Hippo
 * @since 9/26/2018
 */
public interface SubscriptionCollector {

    /**
     * Collects {@link Subscription}s.
     * @param parent  The parent to collect from.
     * @return  The {@link Collection} of {@link Subscription}s.
     * @throws IllegalAccessException  If the access to the {@link Subscription}'s method/field is illegal.
     * @throws SubscriptionCollectException  If the {@link Subscription} does not meet the requirements.
     */
    Collection<Subscription> collectSubscriptions(final Object parent) throws IllegalAccessException, SubscriptionCollectException;

    /**
     * Gets the instance of {@link MethodBasedSubscriptionCollector}.
     * @return  The instance.
     */
    static SubscriptionCollector method(){
        return MethodBasedSubscriptionCollector.INSTANCE;
    }

    /**
     * Gets the instance of {@link FieldBasedSubscriptionCollector}.
     * @return  The instance.
     */
    static SubscriptionCollector field(){
        return FieldBasedSubscriptionCollector.INSTANCE;
    }

    /**
     * Gets the instance of {@link StandardSubscriptionCollector}.
     * @return  The instance.
     */
    static SubscriptionCollector standard(){
        return StandardSubscriptionCollector.INSTANCE;
    }
}
