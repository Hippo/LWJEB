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

package me.hippo.systems.lwjeb.message.impl;

import me.hippo.systems.lwjeb.lambda.MessageHandlerInvocation;
import me.hippo.systems.lwjeb.lambda.MessageHandlerInvocationFactory;
import me.hippo.systems.lwjeb.message.MessageHandler;

import java.lang.reflect.Method;


/**
 * <h1>The Method Based MessageHandler</h1>
 * This is an implementation of {@link MessageHandler},
 * this is based around {@link java.lang.reflect.Method}s,
 * they are invoked using the {@link java.lang.invoke.LambdaMetafactory}.
 *
 * @author Hippo
 * @since 11/6/2018
 */
public final class MethodBasedMessageHandler extends MessageHandler {

    /**
     * The object that the method is associated with.
     */
    private final Object parent;

    /**
     * The {@link MessageHandlerInvocation}, invokes the method.
     */
    private final MessageHandlerInvocation invoker;

    /**
     * Creates a new {@link MethodBasedMessageHandler} with the desired parent, topic and method.
     *
     * @param parent  The parent.
     * @param topic  The topic.
     * @param method  The method.
     */
    public MethodBasedMessageHandler(Object parent, Class<?> topic, Method method) {
        super(topic);
        this.parent = parent;
        this.invoker = MessageHandlerInvocationFactory.create(method);
    }

    /**
     * @InheritDoc
     */
    @Override
    public void invoke(Object topic) {
        invoker.invoke(parent, topic);
    }
}
