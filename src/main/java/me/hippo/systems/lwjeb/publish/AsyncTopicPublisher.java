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

package me.hippo.systems.lwjeb.publish;

import me.hippo.systems.lwjeb.message.MessageHandler;
import me.hippo.systems.lwjeb.subscribe.ListenerSubscriber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * <h1>The Async MessageHandler Publish</h1>
 * Post messages to the {@link me.hippo.systems.lwjeb.EventBus}.
 *
 * @author Hippo
 * @since 06/23/2019
 */
public final class AsyncTopicPublisher {

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    private AsyncTopicPublisher(){
    }

    /**
     * Post's a message to the {@link me.hippo.systems.lwjeb.EventBus} saying to invoke all the {@link MessageHandler}s.
     *
     * @param topic  The message topic.
     */
    public static void post(Object topic, ListenerSubscriber<?, ?> subscriber) {
        try {
            executorService.execute(() -> SyncTopicPublisher.post(topic, subscriber));
        }catch (RejectedExecutionException e) {
            e.printStackTrace();
            System.err.println("Is the eventbus shutdown?");
        }
    }

    /**
     * Sets how many threads the {@link #executorService} will use.
     *
     * @param threads  The number of threads.
     */
    public static void setThreads(int threads) {
        if(threads <= 0) {
            throw new IllegalArgumentException("Thread count must be greater than 0.");
        }
        executorService = Executors.newFixedThreadPool(threads);
    }

    /**
     * Shuts down the {@link #executorService}.
     */
    public static void shutdown() {
        executorService.shutdown();
    }
}
