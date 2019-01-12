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

package me.hippo.systems.lwjeb.subscription.populate;

import me.hippo.systems.lwjeb.subscription.Subscription;
import me.hippo.systems.lwjeb.subscription.collect.SubscriptionCollector;

import java.util.Collection;
import java.util.HashMap;

/**
 * The {@link SubscriptionPopulator}, used as a framework for {@link AbstractSubscriptionPopulator}.
 *
 * @author Hippo
 * @since 9/26/2018
 */
public interface SubscriptionPopulator {

    /**
     * Populates the cache with {@link Subscription}s collected from {@code parent}.
     * @param parent  The parent to collect from.
     * @return  The collected {@link Subscription}s.
     */
    Collection<Subscription> populateCache(final Object parent);

    /**
     * Gets the registered {@link Subscription}s.
     * @return  The registered {@link Subscription}s.
     */
    HashMap<Class<?>, Collection<Subscription>> registeredSubscriptions();

    /**
     * Gets the {@link SubscriptionCollector}.
     * @return  The {@link SubscriptionCollector}.
     */
    SubscriptionCollector collector();

    /**
     * Registers {@code parent} to the event bus.
     * @param parent  The parent to register.
     * @return  If the registration was successful.
     */
    boolean register(final Object parent);

    /**
     * Unregisters {@code parent} from the event bus.
     * @param parent  The parent to unregister.
     * @return  If the unregistration was successful.
     */
    boolean unregister(final Object parent);

    /**
     * Gets {@link StandardSubscriptionPopulator}.
     * @return  The {@link StandardSubscriptionPopulator}.
     */
    static SubscriptionPopulator standard(){
        return StandardSubscriptionPopulator.INSTANCE;
    }

    static SubscriptionPopulator method(){
        return MethodBasedSubscriptionPopulator.INSTANCE;
    }

    static SubscriptionPopulator field(){
        return FieldBasedSubscriptionPopulator.INSTANCE;
    }
}
