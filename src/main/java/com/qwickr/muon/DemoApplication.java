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
import io.dropwizard.configuration.*;

import io.muoncore.MultiTransportMuon;

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
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                                                   new EnvironmentVariableSubstitutor()
                )
        );
    }

    @Override
    public void run(final DemoConfiguration configuration,
                    final Environment environment) {
        
        muon_clojure.server.Microservice muon = MuonFactory.build(configuration.getMultiTransportMuonFactory());

        environment.jersey().register(AuthFactory.binder(new BasicAuthFactory<>(
            AuthenticatorFactory.build(muon), "AUTHENTICATION", AuthenticatedUser.class)));
        
        final HelloWorldResource resource = new HelloWorldResource();
        environment.jersey().register(resource);

    }

}
