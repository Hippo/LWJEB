package me.hippo.api.lwjeb.configuration.config.impl;

import me.hippo.api.lwjeb.configuration.config.Configuration;
import me.hippo.api.lwjeb.listener.ListenerClassLoader;

import static java.lang.ClassLoader.getSystemClassLoader;

/**
 * @author Shyrogan
 * @version 5.1.0, 11/08/20
 * @since 5.1.0
 *
 * The class loader configuration allows you to modify the {@link ClassLoader} used to compile
 * {@link me.hippo.api.lwjeb.listener.Listener}.
 */
public final class ClassLoaderConfiguration implements Configuration<ClassLoaderConfiguration> {

    /** The parent class loader */
    private ListenerClassLoader listenerClassLoader;

    /**
     * Gets the default config.
     *
     * @return  The default config.
     */
    public static ClassLoaderConfiguration getDefault() {
        return new ClassLoaderConfiguration().provideDefault();
    }

    /**
     * @inheritDoc
     */
    @Override
    public ClassLoaderConfiguration provideDefault() {
        ClassLoaderConfiguration configuration = new ClassLoaderConfiguration();
        configuration.setListenerClassLoader(new ListenerClassLoader(getSystemClassLoader()));
        return configuration;
    }

    /**
     * Gets the listener class loader
     *
     * @return The listener class loader
     */
    public ListenerClassLoader getListenerClassLoader() {
        return listenerClassLoader;
    }

    /**
     * Sets the listener class loader
     *
     * @param listenerClassLoader Listener class loader
     */
    public ClassLoaderConfiguration setListenerClassLoader(ListenerClassLoader listenerClassLoader) {
        this.listenerClassLoader = listenerClassLoader;
        return this;
    }
}
