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
import me.hippo.systems.lwjeb.subscription.exception.SubscriptionCollectException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * The abstract implementation of {@link SubscriptionPopulator}.
 *
 * <p>
 *     Provides a basic framework to make new {@link SubscriptionPopulator}s if you want.
 * </p>
 */
public abstract class AbstractSubscriptionPopulator implements SubscriptionPopulator{

    /**
     * The {@link SubscriptionCollector} used for collecting {@link Subscription}s.
     */
    private final SubscriptionCollector collector;

    /**
     * All the registered {@link Subscription}s mapped by the event type.
     */
    private final HashMap<Class<?>, Collection<Subscription>> EVENT_MAP;

    /**
     * All the {@link Subscription}s mapped to their parent class.
     */
    private final HashMap<Object, Collection<Subscription>> CACHE_MAP;

    /**
     * Creates a new {@link AbstractSubscriptionPopulator} with the desired {@link SubscriptionCollector}.
     * @param collector  The subscription collector.
     */
    public AbstractSubscriptionPopulator(final SubscriptionCollector collector){
        this.collector = collector;
        this.EVENT_MAP = new HashMap<>();
        this.CACHE_MAP = new HashMap<>();
    }

    /**
     * Populates the cache.
     * <p>
     *     If the {@link #CACHE_MAP} has nothing for {@code parent} it will use {@link #collector} to collect a {@link Collection} of {@link Subscription}s.
     * </p>
     * @param parent
     * @return
     */
    @Override
    public Collection<Subscription> populateCache(final Object parent) {
        return CACHE_MAP.computeIfAbsent(parent, ignored -> {
            try {
                return collector.collectSubscriptions(parent);
            } catch (IllegalAccessException | SubscriptionCollectException e) {
                e.printStackTrace();
            }
            return Collections.EMPTY_LIST;
        });
    }

    /**
     * Gets the registered subscriptions.
     * @return  The registered subscriptions.
     */
    @Override
    public HashMap<Class<?>, Collection<Subscription>> registeredSubscriptions() {
        return EVENT_MAP;
    }

    /**
     * Gets the subscription collector.
     * @return  The subscription collector.
     */
    @Override
    public SubscriptionCollector collector(){
        return collector;
    }
}
