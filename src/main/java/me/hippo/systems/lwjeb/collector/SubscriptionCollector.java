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

package me.hippo.systems.lwjeb.collector;

import me.hippo.systems.lwjeb.collector.impl.FieldBasedSubscriptionCollector;
import me.hippo.systems.lwjeb.collector.impl.MethodAndFieldBasedSubscriptionCollector;
import me.hippo.systems.lwjeb.collector.impl.MethodBasedSubscriptionCollector;
import me.hippo.systems.lwjeb.message.MessageHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>The MessageHandler Collector</h1>
 * Provides an abstract framework for producing {@link SubscriptionCollector}s.
 * This will collect {@link MessageHandler}s in an object.
 *
 * @author Hippo
 * @since 11/6/2018
 */
public interface SubscriptionCollector {

    /**
     * Gets all the {@link MessageHandler}s in {@code parent}.
     *
     * @param parent  The object to scan.
     * @return  The {@link List} of {@link MessageHandler}s.
     */
    List<MessageHandler> collect(Object parent);

    /**
     * Gets the {@link MethodBasedSubscriptionCollector}.
     *
     * @return  The {@link MethodBasedSubscriptionCollector}.
     */
    static SubscriptionCollector method(){
        return MethodBasedSubscriptionCollector.INSTANCE;
    }

    /**
     * Gets the {@link FieldBasedSubscriptionCollector}.
     *
     * @return  The {@link FieldBasedSubscriptionCollector}.
     */
    static SubscriptionCollector field(){
        return FieldBasedSubscriptionCollector.INSTANCE;
    }

    /**
     * Gets the {@link MethodAndFieldBasedSubscriptionCollector}.
     *
     * @return  The {@link MethodAndFieldBasedSubscriptionCollector}.
     */
    static SubscriptionCollector methodAndField(){
        return MethodAndFieldBasedSubscriptionCollector.INSTANCE;
    }
}
