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
 * A functional interface used to create a {@link java.lang.invoke.CallSite}.
 *
 * @author Hippo
 * @since 1/3/2019
 */
@FunctionalInterface
public interface MessageHandlerInvocation {

    /**
     * Invokes the {@link MessageHandlerInvocation}.
     *
     * @param parent  The parent.
     * @param topic  The topic.
     */
    void invoke(Object parent, Object topic);
}
