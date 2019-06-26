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

import java.lang.invoke.*;
import java.lang.reflect.Method;

/**
 * <h1>The Event Lambda Factory</h1>
 * Produces {@link MessageHandlerInvocation}s, used for invocation via {@link LambdaMetafactory}.
 *
 * @author Hippo
 * @since 1/4/2019
 */
public final class MessageHandlerInvocationFactory {

    /**
     * Creates a {@link MessageHandlerInvocation} from a {@link Method}.
     *
     * @param method  The {@link Method}.
     * @return  The {@link MessageHandlerInvocation}.
     */
    public static MessageHandlerInvocation create(Method method){
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            MethodType invokedType = MethodType.methodType(MessageHandlerInvocation.class);
            MethodHandle implMethod = lookup.unreflect(method);
            MethodType instantiatedMethodType = implMethod.type();
            MethodType samMethodType = instantiatedMethodType.changeParameterType(0, Object.class).changeParameterType(1, Object.class);

            CallSite callSite = LambdaMetafactory.metafactory(
                    lookup,
                    "invoke",
                    invokedType,
                    samMethodType,
                    implMethod,
                    instantiatedMethodType
            );

            return (MessageHandlerInvocation)callSite.getTarget().invoke();
        }catch (Throwable e){
            e.printStackTrace();
        }
        return null;
    }
}
