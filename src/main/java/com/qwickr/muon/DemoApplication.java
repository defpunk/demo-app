package com.qwickr.muon;

import com.qwickr.muon.resources.HelloWorldResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.qwickr.muon.auth.AuthenticatorFactory;
import com.qwickr.muon.auth.AuthenticatedUser;
import io.dropwizard.auth.*;
import io.dropwizard.auth.basic.*;

public class DemoApplication extends Application<DemoConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DemoApplication().run(args);
    }

    @Override
    public String getName() {
        return "demo";
    }

    @Override
    public void initialize(final Bootstrap<DemoConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DemoConfiguration configuration,
                    final Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource();
        environment.jersey().register(resource);

        environment.jersey().register(AuthFactory.binder(new BasicAuthFactory<>(
            AuthenticatorFactory.build(), "AUTHENTICATION", AuthenticatedUser.class)));
    }

}
