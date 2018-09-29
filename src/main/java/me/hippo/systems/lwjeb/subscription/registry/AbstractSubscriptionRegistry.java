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

package me.hippo.systems.lwjeb.subscription.registry;

import me.hippo.systems.lwjeb.subscription.Subscription;
import me.hippo.systems.lwjeb.subscription.populate.SubscriptionPopulator;

import java.util.Collection;

/**
 * The {@link AbstractSubscriptionRegistry}, a useful framework if you wanted to implement your own {@link SubscriptionRegistry}.
 *
 * @author Hippo
 * @since 9/28/2018
 */
public abstract class AbstractSubscriptionRegistry implements SubscriptionRegistry {

    /**
     * The {@link SubscriptionPopulator} that will populate the maps.
     */
    protected final SubscriptionPopulator populator;

    /**
     * Makes a new {@link AbstractSubscriptionRegistry} with the {@link me.hippo.systems.lwjeb.subscription.populate.StandardSubscriptionPopulator}.
     */
    public AbstractSubscriptionRegistry(){
        this(SubscriptionPopulator.standard());
    }

    /**
     * Makes a new {@link AbstractSubscriptionRegistry} with the desired {@link SubscriptionPopulator}.
     * @param populator  The populator.
     */
    public AbstractSubscriptionRegistry(final SubscriptionPopulator populator){
        this.populator = populator;
    }

    /**
     * Gets the {@link Collection} of {@link Subscription}s mapped to {@code parent}.
     * @param event  The event.
     * @return  The {@link Collection} of {@link Subscription}s.
     */
    @Override
    public Collection<Subscription> getSubscriptions(final Class<?> event) {
        return populator.registeredSubscriptions().get(event);
    }


}
