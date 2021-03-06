# Mockwizard
#### Testing Dropwizard-Microservices in Isolation
Mockwizard library is highly inspired by Mockito. It adapts the Mockito ideas 
from the client to the server by allowing server side mock creation, 
verification and stubbing. Lets start with an example:

An orderservice asks a quoteservice for real time quotes right before an order is placed. 

![Alt text](https://cloud.githubusercontent.com/assets/28768/6596319/5149bf2c-c7f3-11e4-9732-87355c5fa472.png)

Like within traditional unit tests we want to test the orderservice (SUT = Service under Test) 
in isolation, i.e. without any dependencies on external systems like a running quoteservice. 
Mockwizards enforces a blackbox testing approach: The test acts as a normal client and accesses the microservice only via its HTTP interface. All external dependencies of the SUT are mocked by Mockwizard. Here is what you have to do:

On the server:

```
Mockwizard.mock(QuoteService.class); 
```

The clientside test:
```
Mockwizard.when("quoteservice.getPrice").with("TSLA").thenReturn(200.0);
Order o = serviceclient.createOrder(new Order("TSLA"));
assertThat(o.getPrice(), is(200.0));
```

And what about verification? 
```
Order o = serviceclient.createOrder(new Order("TSLA"));
Mockwizard.verify("clearingservice.clear");
```

Is it really that simple? Please follow the step by step guide to add Mockwizard to your Dropwizard services.

#### Mockwizard Step by Step
__Step 1.__ Add a CollaboratorFactory to your service configuration class
```
public class OrderServiceConfiguration extends Configuration {

  @JsonProperty("quoteservice")
  public CollaboratorFactory<QuoteService> quoteService;
}
```

__Step 2.__ Mock the external service in your test configuration file
```
quoteservice:
  type: MOCK
```

__Step 3.__ Init Mockwizard in your application class
```
public class OrderServiceApplication extends Application<OrderServiceConfiguration>
 
  @Override
  public void run(SampleServiceConfiguration c, Environment e) throws Exception {
    Mockwizard.init(environment);
  }
}
```

__Step 4.__ Write external tests against the service
```
public class OrderServiceTest {

  @Test
  public void shouldCreateOrder() throws Exception {
    Mockwizard.setup(LOCAL_SERVICE_PORT);
    Mockwizard.when("quoteservice.getPrice").with("TSLA").thenReturn(200.0);
    Order o = serviceclient.createOrder(new Order("TSLA"));
    assertThat(o.getPrice(), is(200.0));
  }
}
```

Please clone this repository and study the examples in org/mockwizard/examples:
```
$ git clone https://github.com/rwirdemann/mockwizard
$ cd mockwizard
$ mvn clean test
```

Alternatively you can add Mockwizard to your project by downloading it from maven central:
```
<dependencies>
    <dependency>
        <groupId>com.github.rwirdemann</groupId>
        <artifactId>mockwizard</artifactId>
        <version>0.0.2</version>
    </dependency>
</dependencies>

```

Feedback is very welcome: ralf@ralfwirdemann.de