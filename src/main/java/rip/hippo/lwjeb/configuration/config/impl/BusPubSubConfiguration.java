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

package rip.hippo.lwjeb.configuration.config.impl;

import rip.hippo.lwjeb.configuration.config.Configuration;
import rip.hippo.lwjeb.message.publish.MessagePublisher;
import rip.hippo.lwjeb.message.publish.impl.StandardMessagePublisher;
import rip.hippo.lwjeb.message.scan.MessageScanner;
import rip.hippo.lwjeb.message.scan.impl.MethodBasedMessageScanner;
import rip.hippo.lwjeb.subscribe.ListenerSubscriber;
import rip.hippo.lwjeb.subscribe.impl.WeakReferencedListenerSubscriber;

/**
 * @author Hippo
 * @version 5.0.0, 10/27/19
 * @since 5.0.0
 * <p>
 * The pubsub configuration configures how all the handlers are handled. (subscribing, publishing, scanning)
 * </p>
 */
@SuppressWarnings("unchecked")
public final class BusPubSubConfiguration implements Configuration<BusPubSubConfiguration> {

  /**
   * The subscriber.
   */
  private ListenerSubscriber subscriber;

  /**
   * The publisher.
   */
  private MessagePublisher publisher;

  /**
   * The scanner.
   */
  private MessageScanner scanner;


  /**
   * Gets the default configuration.
   *
   * @return The default configuration.
   */
  public static BusPubSubConfiguration getDefault() {
    return new BusPubSubConfiguration().provideDefault();
  }

  /**
   * @inheritDoc
   */
  @Override
  public BusPubSubConfiguration provideDefault() {
    this.setSubscriber(new WeakReferencedListenerSubscriber<>());
    this.setPublisher(new StandardMessagePublisher<>());
    this.setScanner(new MethodBasedMessageScanner<>());
    return this;
  }

  /**
   * Gets the subscriber.
   *
   * @param <T> The topic.
   * @return The subscriber.
   */
  public <T> ListenerSubscriber<T> getSubscriber() {
    return subscriber;
  }

  /**
   * Sets the subscriber.
   *
   * @param subscriber The subscriber.
   * @param <T>        The topic.
   */
  public <T> void setSubscriber(ListenerSubscriber<T> subscriber) {
    this.subscriber = subscriber;
  }

  /**
   * Gets the publisher.
   *
   * @param <T> The topic.
   * @return The publisher.
   */
  public <T> MessagePublisher<T> getPublisher() {
    return publisher;
  }

  /**
   * Sets the publisher.
   *
   * @param publisher The publisher.
   * @param <T>       The topic.
   */
  public <T> void setPublisher(MessagePublisher<T> publisher) {
    this.publisher = publisher;
  }

  /**
   * Gets the scanner.
   *
   * @param <T> The topic.
   * @return The scanner.
   */
  public <T> MessageScanner<T> getScanner() {
    return scanner;
  }

  /**
   * Sets the scanner.
   *
   * @param scanner THe scanner.
   * @param <T>     The topic.
   */
  public <T> void setScanner(MessageScanner<T> scanner) {
    this.scanner = scanner;
  }
}
