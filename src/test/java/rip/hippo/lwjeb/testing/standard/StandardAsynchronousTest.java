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

package rip.hippo.lwjeb.testing.standard;

import rip.hippo.lwjeb.annotation.Handler;
import rip.hippo.lwjeb.bus.PubSub;
import rip.hippo.lwjeb.configuration.BusConfigurations;
import rip.hippo.lwjeb.configuration.config.impl.BusPubSubConfiguration;
import rip.hippo.lwjeb.subscribe.impl.StrongReferencedListenerSubscriber;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * @author Hippo
 * @version 5.0.0, 1/16/2020
 * @since 5.0.0
 */
public final class StandardAsynchronousTest {

    @Test
    public void test() {

        PubSub<File> pubSub = new PubSub<>(new BusConfigurations.Builder()
            .setConfiguration(BusPubSubConfiguration.class, () -> {
                BusPubSubConfiguration configuration = BusPubSubConfiguration.getDefault();
                configuration.setSubscriber(new StrongReferencedListenerSubscriber<>());
                return configuration;
            }).build()
        );

        pubSub.setupDispatchers();
        pubSub.subscribe(this);

        File file = new File("MyCoolFile");
        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("this is my super duper cool file");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        pubSub.post(file).async();


        pubSub.shutdown();

    }

    @Handler
    public void onMessage(File message) {
        try {

            Scanner scanner = new Scanner(message);
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        message.delete();
    }


}
