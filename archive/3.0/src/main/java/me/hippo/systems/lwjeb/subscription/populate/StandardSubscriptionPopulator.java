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

import java.util.ArrayList;
import java.util.Collection;

/**
 * A standard {@link SubscriptionPopulator}.
 *
 * @author Hippo
 * @since 9/26/2018
 */
public class StandardSubscriptionPopulator extends AbstractSubscriptionPopulator {

    /**
     * An instance to {@link StandardSubscriptionPopulator}.
     */
    public static final StandardSubscriptionPopulator INSTANCE = new StandardSubscriptionPopulator();

    /**
     * Initializes the {@link StandardSubscriptionPopulator} with a {@link me.hippo.systems.lwjeb.subscription.collect.impl.StandardSubscriptionCollector}.
     */
    public StandardSubscriptionPopulator() {
        super(SubscriptionCollector.standard());
    }

    /**
     * Creates a new {@link StandardSubscriptionPopulator} with the desired {@link SubscriptionCollector}.
     * @param collector  The collector.
     */
    public StandardSubscriptionPopulator(final SubscriptionCollector collector){
        super(collector);
    }

    /**
     * Registers {@code parent} to the event bus.
     * <p>
     *     Finds the cached {@link Subscription}s for {@code parent} the registers them to {@link #registeredSubscriptions()}.
     * </p>
     * @param parent  The parent to register.
     * @return  If the registration was successful.
     */
    @Override
    public boolean register(final Object parent) {
        boolean passed = false;
        for(final Subscription subscription : populateCache(parent)){
            passed |= registeredSubscriptions().computeIfAbsent(subscription.getEvent(), ignored -> new ArrayList<>()).add(subscription);
        }
        return passed;
    }


    /**
     * Unregisters {@code parent} from the event bus.
     * <p>
     *     Finds the {@link Subscription}s in the cache then removes its events from the {@link #registeredSubscriptions()}.
     * </p>
     * @param parent
     * @return
     */
    @Override
    public boolean unregister(final Object parent) {
        boolean passed = false;
        for(final Subscription subscription : populateCache(parent)){
            final Collection<Subscription> subscriptions = registeredSubscriptions().get(subscription.getEvent());
            if(subscriptions != null){
                passed |= subscriptions.remove(subscription);
            }
        }
        return passed;
    }

}
