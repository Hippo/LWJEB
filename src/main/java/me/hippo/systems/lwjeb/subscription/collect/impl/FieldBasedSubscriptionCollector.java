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
import me.hippo.systems.lwjeb.listener.Listener;
import me.hippo.systems.lwjeb.subscription.Subscription;
import me.hippo.systems.lwjeb.subscription.collect.SubscriptionCollector;
import me.hippo.systems.lwjeb.subscription.exception.SubscriptionCollectException;
import me.hippo.systems.lwjeb.subscription.impl.FieldSubscription;
import me.hippo.systems.lwjeb.util.TypeUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@link SubscriptionCollector} that collects fields.
 *
 * <p>
 *     The field must follow the requirements, @see {@link Collect} for the requirements.
 * </p>
 * @author Hippo
 * @since 9/26/2018
 */
public enum FieldBasedSubscriptionCollector implements SubscriptionCollector {

    /**
     * An instance to {@link FieldBasedSubscriptionCollector}.
     */
    INSTANCE;

    /**
     * Collects the {@link FieldSubscription}s that meet the requirements.
     * @param parent  The parent to collect from.
     * @return  The {@link Collection} of {@link FieldSubscription}s.
     * @throws IllegalAccessException  If the access to the field is illegal.
     * @throws SubscriptionCollectException  If the field does not meet the proper requirements.
     */
    @Override
    public Collection<Subscription> collectSubscriptions(final Object parent) throws IllegalAccessException, SubscriptionCollectException{
        final Collection<Subscription> subscriptions = new ArrayList<>();

        for(final Field field : parent.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(Collect.class)){
                if(!Listener.class.isAssignableFrom(field.getType())){
                    throw new SubscriptionCollectException("Error field '"+field.getName()+"', class '"+field.getDeclaringClass()+"' is not a listener!");
                }
                final Listener listener = (Listener) field.get(parent);
                final Class<?> event = TypeUtils.getArguments(field);
                final FieldSubscription subscription = new FieldSubscription(listener, event);
                subscriptions.add(subscription);
            }
        }
        return subscriptions;
    }
}
