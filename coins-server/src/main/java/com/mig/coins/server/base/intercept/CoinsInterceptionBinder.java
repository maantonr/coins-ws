package com.mig.coins.server.base.intercept;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

// PDTE Documentar
/**
 * Register our custom {@code InterceptionService} into HK2.
 *
 * @author jjimenezg
 */
public class CoinsInterceptionBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(CoinsInterceptionService.class)
                .to(org.glassfish.hk2.api.InterceptionService.class)
                .in(Singleton.class);
    }
}