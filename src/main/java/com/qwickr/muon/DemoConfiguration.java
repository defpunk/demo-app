package com.qwickr.muon;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.qwickr.dropwizard.muon.MultiTransportMuonFactory;

public class DemoConfiguration extends Configuration {
    
    private MultiTransportMuonFactory muon = new MultiTransportMuonFactory();

    @JsonProperty("muon")
    public void setSingleTransportMuonFactory(MultiTransportMuonFactory factory) {
        this.muon = factory;
    }

    @JsonProperty("muon")
    public MultiTransportMuonFactory getMultiTransportMuonFactory() {
        return muon;
    }
}
