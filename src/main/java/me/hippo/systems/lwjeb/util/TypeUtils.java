/*
 * MIT License
 *
 * Copyright (c) 2018 Hippo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.hippo.systems.lwjeb.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * The util for getting the type parameter in {@link me.hippo.systems.lwjeb.listener.Listener}s.
 *
 * <e>
 *     public Listener<String> onEvent = string{
 *       //code...
 *     };
 *
 *     The Type parameter would be String.
 * </e>
 * @author Hippo
 * @since 9/26/2018
 */
public final class TypeUtils {

    /**
     * Gets the type parameter for a specific field.
     * @param field  The field.
     * @return  The type parameter.
     */
    public static Class<?> getArguments(final Field field){
        return (Class<?>)((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0];
    }
}
