# Mockwizard
Testing Dropwizard-Microservices in Isolation.

A microservice accesses a PartnerService via HTTP using a Gateway class. Goal: Blackboxtest the mircoservice in isolation without a running PartnerService.

```
Test ---HTTP--> Microservice --> Gateway ---HTTP--> PartnerService
```

Challenge: Mock the Gateway which is executed within a different process than the blackbox test. This is how Mockwizard adresses this challenge.

##### 1. Add a CollaborationFactory to your service configuration class
```
public class SampleServiceConfiguration extends Configuration {

  @JsonProperty("gateway")
  public CollaboratorFactory<Gateway> gatewayFactory;
}
```

##### 2. Mockout the gateway in your test configuration file
```
gateway:
  type: MOCK
```

##### 3. Init Mockwizard in your application class
```
public class SampleServiceApplication extends Application<SampleServiceConfiguration> 


  @Override
  public void run(SampleServiceConfiguration c, Environment e) throws Exception {
      Mockwizard.init(environment);
  }
}
```

#### 4. Write external tests against the service
```
public class SampleServiceTest {

  @Test
  public void foo() throws Exception {
      Mockwizard.when("gateway.foo").thenReturn(1);
      assertEquals(1, sampleClient.foo());
  }
}
```

Pleases clone this repository and study the examples in org/mockwizard/examples:
```
$ git clone https://github.com/rwirdemann/mockwizard
$ mvn clean test
```

Feedback is very welcome: ralf@ralfwirdemann.de