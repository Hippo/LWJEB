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

package me.hippo.systems.lwjeb.testing;

import me.hippo.systems.lwjeb.EventBus;
import me.hippo.systems.lwjeb.annotation.Collect;
import me.hippo.systems.lwjeb.listener.Listener;
import me.hippo.systems.lwjeb.publish.AsyncTopicPublisher;

import java.io.*;


/**
 * @author Hippo
 * @since 06/21/2019
 */
public final class Main {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus.Builder().method().field().build();
        eventBus.subscribe(new Main());
        eventBus.postSync("My message\n");
        eventBus.postAsync(new File("MyCoolFile"));
        eventBus.shutdown(); //note we only needed to do this because of the statement above.
    }

    @Collect
    public void methodMessageHandler(String message) {
        System.out.println(message);
    }

    @Collect
    public final Listener<File> fieldMessageHandler = file -> {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while(line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

}
