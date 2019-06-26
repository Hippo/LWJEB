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

package collect.impl;

import annotation.Collect;
import collect.TopicSubscriberCollector;
import subscriber.TopicSubscriber;
import subscriber.impl.MethodBasedTopicSubscriber;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Hippo
 * @since 06/19/2019
 */
public enum MethodBasedTopicSubscriberCollector implements TopicSubscriberCollector {
    INSTANCE;

    @Override
    public Set<TopicSubscriber> collect(Object parent) {
        Set<TopicSubscriber> topicSubscribers = new HashSet<>();
        for (Method method : parent.getClass().getDeclaredMethods()) {
            if(method.isAnnotationPresent(Collect.class) && method.getParameterCount() == 1) {
                Class<?> topic = method.getParameterTypes()[0];
                topicSubscribers.add(new MethodBasedTopicSubscriber(parent, topic, method));
            }
        }
        return topicSubscribers;
    }
}
