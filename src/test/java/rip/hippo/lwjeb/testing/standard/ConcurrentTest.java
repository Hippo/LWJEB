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

package rip.hippo.lwjeb.testing.standard;

import org.junit.jupiter.api.Test;
import rip.hippo.lwjeb.annotation.Handler;
import rip.hippo.lwjeb.bus.PubSub;
import rip.hippo.lwjeb.configuration.BusConfigurations;
import rip.hippo.lwjeb.configuration.config.impl.BusPubSubConfiguration;
import rip.hippo.lwjeb.subscribe.impl.StrongReferencedListenerSubscriber;


/**
 * @author Hippo
 * @version 5.0.0, 11/2/19
 * @since 5.0.0
 */
public final class ConcurrentTest {

  private static final PubSub<Event> PUB_SUB = new PubSub<>(new BusConfigurations.Builder().setConfiguration(BusPubSubConfiguration.class, () -> {
    BusPubSubConfiguration busPubSubConfiguration = BusPubSubConfiguration.getDefault();
    busPubSubConfiguration.setSubscriber(new StrongReferencedListenerSubscriber<>());
    return busPubSubConfiguration;
  }).build());

  @Test
  public void test() {
    PUB_SUB.subscribe(this);

    PUB_SUB.post(new MyEvent(this)).dispatch();
  }

  @Handler
  public void onMessage(MyEvent message) {
    message.test();
  }

  public static abstract class Event {
    protected ConcurrentTest concurrentTest;

    public Event(ConcurrentTest concurrentTest) {
      this.concurrentTest = concurrentTest;
    }


    public abstract void test();
  }

  public static final class MyEvent extends Event {
    public MyEvent(ConcurrentTest concurrentTest) {
      super(concurrentTest);
    }

    @Override
    public void test() {
      PUB_SUB.unsubscribe(concurrentTest);
    }
  }


}
