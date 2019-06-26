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

package lambda;

import java.lang.invoke.*;
import java.lang.reflect.Method;

/**
 * @author Hippo
 * @since 06/19/2019
 */
public final class SubscriberInvocationFactory {

    private SubscriberInvocationFactory(){}

    public static SubscriberInvocation create(Method method) {
        try {
            MethodHandles.Lookup caller = MethodHandles.lookup();
            String invokedName = "invoke";
            MethodType invokedType = MethodType.methodType(SubscriberInvocation.class);
            MethodHandle implMethod = caller.unreflect(method);
            MethodType instantiatedMethodType = implMethod.type();
            MethodType samMethodType = instantiatedMethodType.changeParameterType(0, Object.class).changeParameterType(1, Object.class);

            CallSite callsite = LambdaMetafactory.metafactory(caller, invokedName, invokedType, samMethodType, implMethod, instantiatedMethodType);
            return (SubscriberInvocation) callsite.getTarget().invoke();
        }catch (Throwable t){
            t.printStackTrace();
        }
        return null;
    }
}
