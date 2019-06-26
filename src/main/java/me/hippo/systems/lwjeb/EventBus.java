/*
 * Copyright 2019 Hippo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package me.hippo.systems.lwjeb;

import me.hippo.systems.lwjeb.collector.SubscriptionCollector;
import me.hippo.systems.lwjeb.exception.EventBusException;
import me.hippo.systems.lwjeb.message.impl.*;
import me.hippo.systems.lwjeb.publish.AsyncTopicPublisher;
import me.hippo.systems.lwjeb.publish.SyncTopicPublisher;
import me.hippo.systems.lwjeb.message.MessageHandler;
import me.hippo.systems.lwjeb.subscribe.ListenerSubscriber;
import me.hippo.systems.lwjeb.subscribe.impl.ConcurrentListenerSubscriber;
import me.hippo.systems.lwjeb.subscribe.impl.ImmediateListenerSubscriber;

/**
 * <h1>The EventBus</h1>
 * The {@link EventBus} is used for registration, and publishing events.
 * Easily configurable for multi-threaded applications.
 *
 * @author Hippo
 * @since 11/6/2018
 */
public final class EventBus {

    /**
     * The {@link ListenerSubscriber}, used to subscribe objects for collection.
     */
    private ListenerSubscriber subscriber;

    private EventBus(Builder builder) {
        this.subscriber = builder.registry;
    }

    /**
     * Registers {@code parent} to the {@link ListenerSubscriber}.
     *
     * @param parent  The object to subscribe.
     */
    public void subscribe(Object parent){
        if(subscriber == null){
            throw new EventBusException("You must call EventBus#build before subscribing an object.");
        }
        subscriber.subscribe(parent);
    }

    /**
     * Post {@code topic}.
     *
     * @param topic  The topic to message about.
     */
    public void postSync(Object topic) {
        SyncTopicPublisher.post(topic, subscriber);
    }

    /**
     * Post {@code topic}.
     *
     * @param topic  The topic to message about.
     */
    public void postAsync(Object topic) {
        AsyncTopicPublisher.post(topic, subscriber);
    }

    /**
     * Unsubscribes {@code parent} from the {@link ListenerSubscriber}.
     *
     * @param parent  The object to unsubscribe.
     */
    public void unsubscribe(Object parent){
        if(subscriber == null){
            throw new EventBusException("You must call EventBus#build before unsubscribing an object.");
        }
        subscriber.unsubscribe(parent);
    }

    /**
     * This is only needed if you post asynchronously.
     * <p>
     *     Once this is executed you may not use {@link #postAsync(Object)}.
     * </p>
     *
     * @see AsyncTopicPublisher#shutdown()
     */
    public void shutdown() {
        AsyncTopicPublisher.shutdown();
    }

    /**
     * <h2>The Builder</h2>
     * Helps build and configure the {@link EventBus}.
     *
     * @author Hippo
     * @since 3/22/2019
     */
    public static final class Builder {

        /**
         * The {@link ListenerSubscriber}, used to subscribe objects for collection.
         */
        private ListenerSubscriber registry;

        /**
         * The {@link SubscriptionCollector}, used to me.hippo.systems.lwjeb.collect {@link MessageHandler}s in objects.
         */
        private SubscriptionCollector collector;


        /**
         * Simple {@link Boolean}s to determine if the {@link EventBus} should me.hippo.systems.lwjeb.collect
         * {@link MethodBasedMessageHandler} and/or {@link FieldBasedMessageHandler}.
         * Also if the {@link EventBus} should be concurrent or not.
         * <p>
         *     In short terms, this is a marker.
         * </p>
         */
        private boolean method, field, concurrent;

        /**
         * Sets {@link #concurrent} to true.
         *
         * @return  {@code this.}
         */
        public Builder concurrent(){
            concurrent = true;
            return this;
        }

        /**
         * Sets {@link #concurrent} to false.
         *
         * @return  {@code this}.
         */
        public Builder immediate(){
            concurrent = false;
            return this;
        }

        /**
         * Sets how many threads the {@link AsyncTopicPublisher} will use.
         *
         * @param threads  The number of threads.
         * @return  {@code this}.
         */
        public Builder threads(int threads) {
            AsyncTopicPublisher.setThreads(threads);
            return this;
        }

        /**
         * Sets the {@link #collector} to {@code collector}.
         *
         * @param collector  The collector.
         * @return  {@code this}.
         */
        public Builder collectWith(SubscriptionCollector collector){
            this.collector = collector;
            return this;
        }

        /**
         * Sets {@link #method} to true.
         * <p>
         *     This means the {@link SubscriptionCollector} will me.hippo.systems.lwjeb.collect {@link java.lang.reflect.Method}s.
         * </p>
         * @return  {@code this}.
         */
        public Builder method(){
            method = true;
            return this;
        }

        /**
         * Sets {@link #field} to true.
         * <p>
         *     This means the {@link SubscriptionCollector} will me.hippo.systems.lwjeb.collect {@link java.lang.reflect.Field}s.
         * </p>
         * @return  {@code this}.
         */
        public Builder field(){
            field = true;
            return this;
        }

        /**
         * Builds the {@link EventBus} with the current configuration.
         * <p>
         *      If nothing is configured it will resort to a default configuration.
         * </p>
         *
         * @return  {@code this}.
         */
        public EventBus build(){
            if(collector == null) {
                if (!method && !field) {
                    method = true;
                }
                if (method && field) {
                    collector = SubscriptionCollector.methodAndField();
                } else if (method) {
                    collector = SubscriptionCollector.method();
                } else {
                    collector = SubscriptionCollector.field();
                }
            }
            this.registry = concurrent ? new ConcurrentListenerSubscriber(collector) : new ImmediateListenerSubscriber(collector);
            return new EventBus(this);
        }
    }
}
