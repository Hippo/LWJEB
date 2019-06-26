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

package me.hippo.systems.lwjeb.subscriber.impl;

import me.hippo.systems.lwjeb.lambda.SubscriberInvocation;
import me.hippo.systems.lwjeb.lambda.SubscriberInvocationFactory;
import me.hippo.systems.lwjeb.subscriber.TopicSubscriber;

import java.lang.reflect.Method;

/**
 * @author Hippo
 * @since 06/19/2019
 */
public final class MethodBasedTopicSubscriber extends TopicSubscriber {

    private final Object parent;
    private final SubscriberInvocation invoker;

    public MethodBasedTopicSubscriber(Object parent, Class<?> topic, Method method) {
        super(topic);
        this.parent = parent;
        this.invoker = SubscriberInvocationFactory.create(method);
    }

    @Override
    public void invoke(Object event) {
        invoker.invoke(parent, event);
    }
}
