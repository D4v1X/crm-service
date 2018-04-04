package utils;

import com.google.inject.AbstractModule;

/**
 * Guice module used to eagerly load our custom Jackson mapper
 */
public class CustomJsonObjectMapperModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CustomJacksonMapper.class).asEagerSingleton();
    }

}
