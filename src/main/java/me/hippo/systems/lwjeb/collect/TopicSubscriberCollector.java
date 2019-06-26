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

package collect;

import collect.impl.MethodBasedTopicSubscriberCollector;
import subscriber.TopicSubscriber;

import java.util.Set;

/**
 * @author Hippo
 * @since 06/19/2019
 */
@FunctionalInterface
public interface TopicSubscriberCollector {
    Set<TopicSubscriber> collect(Object parent);

    static TopicSubscriberCollector method() {
        return MethodBasedTopicSubscriberCollector.INSTANCE;
    }
}
