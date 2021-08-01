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

package rip.hippo.lwjeb.listener;


/**
 * @author Hippo
 * @version 5.0.1, 12/2/19
 * @since 5.0.0
 * <p>
 * A listener is used to invoke handlers.
 * </p>
 */
@FunctionalInterface
public interface Listener {

  /**
   * Invokes the handler.
   *
   * @param parent The parent.
   * @param topic  The topic.
   * @throws ReflectiveOperationException If there is an error invoking the handler.
   */
  void invoke(Object parent, Object topic);
}
