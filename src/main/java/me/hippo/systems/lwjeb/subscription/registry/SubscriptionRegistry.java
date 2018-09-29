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
 * The {@link SubscriptionRegistry}, used as a very basic framework for {@link AbstractSubscriptionRegistry}.
 *
 * @author Hippo
 * @since 9/26/2018
 */
public interface SubscriptionRegistry {

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
     * Gets the {@link Collection} of {@link Subscription}s mapped to {@code parent}.
     * @param event  The event.
     * @return  The {@link Collection} of {@link Subscription}s.
     */
    Collection<Subscription> getSubscriptions(final Class<?> event);

    static SubscriptionRegistry standard(){
        return StandardSubscriptionRegistry.INSTANCE;
    }


}
