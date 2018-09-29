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

package me.hippo.testing.systems.lwjeb;

import me.hippo.systems.lwjeb.EventBus;
import me.hippo.systems.lwjeb.StandardEventBus;
import me.hippo.systems.lwjeb.annotation.Collect;
import me.hippo.systems.lwjeb.listener.Listener;

/**
 * @author Hippo
 * @since 9/26/2018
 */
public final class Main {

    /**
     * An instance to {@link Main}.
     */
    public static final Main INSTANCE = new Main();

    /**
     * Makes a new {@link StandardEventBus}.
     */
    public final EventBus EVENT_BUS = new StandardEventBus();

    /**
     * The main method.
     * @param args  Java arguments.
     */
    public static void main(String[] args){
        /**
         *  Registers {@link Main} to the event bus.
         */
        INSTANCE.EVENT_BUS.register(INSTANCE);

        /**
         * Initializes the test class.
         */
        new TestClass();
    }

    /**
     * An example of a field subscription.
     */
    @Collect
    public Listener<String> onEvent = string -> {
        System.out.println(string);
    };

    /**
     * An example of a method subscription.
     * @param testEvent  The event.
     */
    @Collect
    public void onEvent(final TestEvent testEvent){
        System.out.println(testEvent.cancel);
    }
}
