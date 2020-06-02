package me.hippo.api.testing.lwjeb.standard;

import me.hippo.api.lwjeb.annotation.Handler;
import me.hippo.api.lwjeb.annotation.Wrapped;
import me.hippo.api.lwjeb.bus.PubSub;
import me.hippo.api.lwjeb.wrapped.WrappedType;

/**
 * @author Hippo
 * @version 1.0.0, 6/2/20
 * @since 1.0.0
 */
public enum WrappedTypeTest {
    INSTANCE;

    public static void main(String[] args) {
        PubSub<Object> pubSub = new PubSub<>();
        pubSub.subscribe(INSTANCE);
        pubSub.post("string test").dispatch();
        pubSub.post(69).dispatch();
    }

    @Wrapped({String.class, Integer.class})
    @Handler
    public void onMessage(WrappedType wrappedType) {
        wrappedType.as(String.class).ifPresent(System.out::println);
        wrappedType.as(Integer.class).ifPresent(System.out::println);
    }
}
