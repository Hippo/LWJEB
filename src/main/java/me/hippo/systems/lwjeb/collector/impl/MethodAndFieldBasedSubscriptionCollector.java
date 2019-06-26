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

package me.hippo.systems.lwjeb.collector.impl;

import me.hippo.systems.lwjeb.collector.SubscriptionCollector;
import me.hippo.systems.lwjeb.message.MessageHandler;
import me.hippo.systems.lwjeb.message.impl.FieldBasedMessageHandler;
import me.hippo.systems.lwjeb.message.impl.MethodBasedMessageHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>The Method And Field Based MessageHandler Collector</h1>
 * An implementation of {@link SubscriptionCollector} that collects {@link MethodBasedMessageHandler}s and {@link FieldBasedMessageHandler}s.
 *
 * @author Hippo
 * @since 1/5/2019
 */
public enum MethodAndFieldBasedSubscriptionCollector implements SubscriptionCollector {
    /**
     * An instance to {@link MethodAndFieldBasedSubscriptionCollector}.
     */
    INSTANCE;

    /**
     * @InheritDoc
     */
    @Override
    public List<MessageHandler> collect(Object parent) {
        List<MessageHandler> messageHandlers = new ArrayList<>();
        messageHandlers.addAll(SubscriptionCollector.field().collect(parent));
        messageHandlers.addAll(SubscriptionCollector.method().collect(parent));
        return messageHandlers;
    }
}
