package rip.hippo.lwjeb.testing.standard;

import rip.hippo.lwjeb.annotation.Handler;
import rip.hippo.lwjeb.annotation.Wrapped;
import rip.hippo.lwjeb.bus.PubSub;
import rip.hippo.lwjeb.configuration.BusConfigurations;
import rip.hippo.lwjeb.configuration.config.impl.BusPubSubConfiguration;
import rip.hippo.lwjeb.message.scan.impl.MethodAndFieldBasedMessageScanner;
import rip.hippo.lwjeb.wrapped.WrappedType;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * @author Hippo
 * @version 1.0.0, 6/2/20
 * @since 1.0.0
 */
public final class WrappedTypeTest {

    @Test
    public void test() {
        PubSub<Object> pubSub = new PubSub<>(new BusConfigurations.Builder().setConfiguration(
                BusPubSubConfiguration.class, () -> {
                    BusPubSubConfiguration busPubSubConfiguration = BusPubSubConfiguration.getDefault();
                    busPubSubConfiguration.setScanner(new MethodAndFieldBasedMessageScanner<>());
                    return busPubSubConfiguration;
                }
        ).build());

        pubSub.subscribe(this);
        pubSub.post("string test").dispatch();
        pubSub.post(69).dispatch();
    }

    @Wrapped({String.class, Integer.class})
    @Handler
    public void onMessage(WrappedType wrappedType) {
        wrappedType.as(String.class).ifPresent(s -> System.out.printf("String (%s)%n", s));
        wrappedType.as(Integer.class).ifPresent(i -> System.out.printf("Integer (%d)%n", i));
    }

    @Wrapped({String.class, Integer.class})
    @Handler
    private final Consumer<WrappedType> onMessage = System.out::println;

}
