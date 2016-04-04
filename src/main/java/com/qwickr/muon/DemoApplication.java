package com.qwickr.muon;

import com.qwickr.muon.resources.HelloWorldResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.qwickr.muon.auth.AuthenticatorFactory;
import com.qwickr.muon.auth.AuthenticatedUser;
import io.dropwizard.auth.*;
import io.dropwizard.auth.basic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qwickr.dropwizard.muon.MuonFactory;

public class DemoApplication extends Application<DemoConfiguration> {

    private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);

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
        
        java.util.Map muon = MuonFactory.build(configuration.getSingleTransportMuonFactory());

        environment.jersey().register(AuthFactory.binder(new BasicAuthFactory<>(
            AuthenticatorFactory.build(muon), "AUTHENTICATION", AuthenticatedUser.class)));
        
        final HelloWorldResource resource = new HelloWorldResource();
        environment.jersey().register(resource);

    }

}
