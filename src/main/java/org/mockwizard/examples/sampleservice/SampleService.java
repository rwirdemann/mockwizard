package org.mockwizard.examples.sampleservice;

public class SampleService {
    
    private PartnerService partnerService;
    
    public int foo() {
        return partnerService.foo();
    }
}
