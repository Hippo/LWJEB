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

import me.hippo.systems.lwjeb.annotation.Collect;
import me.hippo.systems.lwjeb.collector.SubscriptionCollector;
import me.hippo.systems.lwjeb.listener.Listener;
import me.hippo.systems.lwjeb.message.MessageHandler;
import me.hippo.systems.lwjeb.message.impl.FieldBasedMessageHandler;
import me.hippo.systems.lwjeb.util.TypeUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>The Field Based MessageHandler Collector.</h1>
 * An implementation of {@link SubscriptionCollector} that collects {@link FieldBasedMessageHandler}s.
 *
 * @author Hippo
 * @since 1/5/2019
 */
public enum FieldBasedSubscriptionCollector implements SubscriptionCollector{
    /**
     * An instance to {@link FieldBasedMessageHandler}.
     */
    INSTANCE;

    /**
     * @InheritDoc
     */
    @Override
    public List<MessageHandler> collect(Object parent) {
        List<MessageHandler> messageHandlers = new ArrayList<>();
        for(Field field : parent.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(Collect.class)){
                try {
                    Listener listener = (Listener) field.get(parent);
                    Class<?> event = TypeUtil.getTypeArgument(field);
                    messageHandlers.add(new FieldBasedMessageHandler(listener, event));
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        return messageHandlers;
    }
}
