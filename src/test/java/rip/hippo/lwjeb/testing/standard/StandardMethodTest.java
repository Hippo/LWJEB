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


/**
 * @author Hippo
 * @version 5.0.0, 11/2/19
 * @since 5.0.0
 */
public final class StandardMethodTest {

  @Test
  public void test() {
    PubSub<Event> pubSub = new PubSub<>();
    pubSub.subscribe(this);

    pubSub.post(new MyEvent()).dispatch();

  }

  @Handler
  public void onMessage(MyEvent message) {
    message.test();
  }

  public static abstract class Event {
    public abstract void test();
  }

  public static final class MyEvent extends Event {
    @Override
    public void test() {
      System.out.println("Invoked");
    }
  }


}
