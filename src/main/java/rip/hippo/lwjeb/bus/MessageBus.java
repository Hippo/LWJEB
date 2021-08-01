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

package rip.hippo.lwjeb.bus;

import rip.hippo.lwjeb.configuration.BusConfigurations;

/**
 * @author Hippo
 * @version 5.0.0, 11/1/19
 * @since 5.0.0
 * <p>
 * A message bus is a configurable bus.
 * </p>
 */
@FunctionalInterface
public interface MessageBus {

  /**
   * Gets the current configuration.
   *
   * @return
   */
  BusConfigurations getConfigurations();
}
