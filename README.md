# Mockwizard
Testing Dropwizard-Microservices in Isolation.

A microservice accesses a PartnerService via HTTP using a Gateway class. Goal: Blackboxtest the mircoservice in isolation without a running PartnerService.

```
Test ---HTTP--> Microservice --> Gateway ---HTTP--> PartnerService
```

Challenge: Mock the Gateway which is executed within a different process than the blackbox test. This is how Mockwizard adresses this challenge.

##### 1. Add a CollaboratorFactory to your service configuration class
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

##### 4. Write external tests against the service
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
$ cd mockwizard
$ mvn clean test
```

Alternatively you can add Mockwizard to you project by downloading it from the central maven repository:
```
<dependencies>
    <dependency>
        <groupId>com.github.rwirdemann</groupId>
        <artifactId>mockwizard</artifactId>
        <version>0.0.1</version>
    </dependency>
</dependencies>

```

Feedback is very welcome: ralf@ralfwirdemann.de