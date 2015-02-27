package org.mockwizard;

import org.junit.Test;
import org.mockito.Mockito;
import org.orderservice.quoteservice.QuoteService;

import java.lang.reflect.Method;

import static junit.framework.TestCase.assertEquals;

public class StubingResourceTest {

    @Test
    public void testName() throws Exception {
        QuoteService quoteService = Mockito.mock(QuoteService.class);

        Method getPrice = quoteService.getClass().getMethod("getPrice", String.class);
        Object tsla = getPrice.invoke(quoteService, "TSLA");
        
        Mockito.when(tsla).thenReturn(20.0);
        assertEquals(20.0, quoteService.getPrice("TSLA"));
    }
}
