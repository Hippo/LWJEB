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

package me.hippo.systems.lwjeb.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * <h1>The Type Util.</h1>
 * Contains a method to get the class type parameter of a field.
 *
 * @author Hippo
 * @since 9/26/2018
 */
public final class TypeUtil {

    /**
     * Gets the class type parameter of a field.
     *
     * @param field  The field to get the parameter from.
     * @return  The parameter.
     */
    public static Class<?> getTypeArgument(Field field){
        return (Class<?>)((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0];
    }
}
