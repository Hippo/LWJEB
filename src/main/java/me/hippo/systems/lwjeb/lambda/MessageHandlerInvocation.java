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

package me.hippo.systems.lwjeb.lambda;


/**
 * <h1>Event Invocation</h1>
 * A functional interface to invocation, with an instance (the parent) and a parameter (the event).
 *
 * @author Hippo
 * @since 1/3/2019
 */
@FunctionalInterface
public interface EventInvocation {
    /**
     * Invokes the {@link EventInvocation}.
     *
     * @param parent  The instance.
     * @param event  The parameter.
     */
    void invoke(final Object parent, final Object event);
}
