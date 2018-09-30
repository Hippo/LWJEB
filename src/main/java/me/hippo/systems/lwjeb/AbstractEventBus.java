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

package me.hippo.systems.lwjeb;

import me.hippo.systems.lwjeb.event.Cancelable;
import me.hippo.systems.lwjeb.subscription.Subscription;
import me.hippo.systems.lwjeb.subscription.registry.StandardSubscriptionRegistry;
import me.hippo.systems.lwjeb.subscription.registry.SubscriptionRegistry;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 * The {@link AbstractEventBus}, a basic framework if you wanted to implement you own {@link EventBus}.
 *
 * @author Hippo
 * @since 9/26/2018
 */
public abstract class AbstractEventBus implements EventBus{

    /**
     * The {@link SubscriptionRegistry}.
     */
    protected final SubscriptionRegistry registry;

    /**
     * Creates a new {@link AbstractEventBus} with the {@link StandardSubscriptionRegistry}.
     */
    public AbstractEventBus(){
        this(SubscriptionRegistry.standard());
    }

    /**
     * Creates a new {@link AbstractEventBus} with the desired {@link SubscriptionRegistry}.
     * @param registry  The subscription registry.
     */
    public AbstractEventBus(final SubscriptionRegistry registry) {
        this.registry = registry;
    }

    /**
     * Registers {@code parent} to the event bus.
     * @param parent  The parent to register.
     * @return  If the registration was successful.
     */
    @Override
    public boolean register(final Object parent) {
        return registry.register(parent);
    }

    /**
     * Unregisters {@code parent} from the event bus.
     * @param parent  The parent to unregister.
     * @return  If the unregistration was successful.
     */
    @Override
    public boolean unregister(final Object parent) {
        return registry.unregister(parent);
    }

    /**
     * Publishes an event.
     * <p>
     *     Gets all the subscriptions mapped to that event then invokes them.
     * </p>
     * @param event
     */
    @Override
    public void publish(final Object event) {
        if(event instanceof Cancelable){
            if(((Cancelable) event).isCancellled()){
                return;
            }
        }
        final Collection<Subscription> subscriptions = registry.getSubscriptions(event.getClass());
        if(subscriptions != null){
            for(final Subscription subscription : subscriptions){
                try {
                    subscription.invoke(event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
