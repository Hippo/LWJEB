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
import rip.hippo.lwjeb.annotation.Filter;
import rip.hippo.lwjeb.annotation.Handler;
import rip.hippo.lwjeb.bus.PubSub;
import rip.hippo.lwjeb.testing.filter.StringCasingFilter;


/**
 * @author Hippo
 * @version 5.0.0, 1/16/2020
 * @since 5.0.0
 */
public final class StandardFiltrationTest {

  @Test
  public void test() {

    PubSub<String> pubSub = new PubSub<>();
    pubSub.subscribe(this);

    pubSub.post("you should see this").dispatch();
    pubSub.post("You shouldn't see this").dispatch();
  }

  @Handler
  @Filter(StringCasingFilter.class) // passes if message is lowercase
  public void onMessage(String message) {
    System.out.println(message);
  }


}
