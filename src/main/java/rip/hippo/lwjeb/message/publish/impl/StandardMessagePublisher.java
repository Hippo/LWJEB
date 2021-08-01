/*
 * Copyright 2020 Hippo
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

package rip.hippo.lwjeb.message.publish.impl;

import rip.hippo.lwjeb.bus.AbstractAsynchronousPubSubMessageBus;
import rip.hippo.lwjeb.message.handler.MessageHandler;
import rip.hippo.lwjeb.message.publish.MessagePublisher;
import rip.hippo.lwjeb.message.result.MessagePublicationResult;
import rip.hippo.lwjeb.message.result.impl.DeadMessagePublicationResult;
import rip.hippo.lwjeb.message.result.impl.StandardMessagePublicationResult;

import java.util.List;

/**
 * @author Hippo
 * @version 5.0.0, 11/2/19
 * @since 5.0.0
 * <p>
 * This is the standard implementation if a message publisher, it returns standard results.
 * </p>
 */
public final class StandardMessagePublisher<T> implements MessagePublisher<T> {

  /**
   * @inheritDoc
   */
  @Override
  public MessagePublicationResult<T> publish(T topic, AbstractAsynchronousPubSubMessageBus<T> messageBus) {
    List<MessageHandler<T>> messageHandlers = messageBus.getSubscriber().subscriberMap().get(topic.getClass());
    return messageHandlers == null ? new DeadMessagePublicationResult<>() : new StandardMessagePublicationResult<>(messageBus, messageHandlers, topic);
  }
}
