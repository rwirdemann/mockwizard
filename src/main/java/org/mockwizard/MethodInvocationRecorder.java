package org.mockwizard;

import org.mockito.internal.invocation.InvocationImpl;
import org.mockito.invocation.DescribedInvocation;
import org.mockito.invocation.StubInfo;
import org.mockito.listeners.InvocationListener;
import org.mockito.listeners.MethodInvocationReport;

public class MethodInvocationRecorder implements InvocationListener {
    
    private MockDetails mockDetails;

    public MethodInvocationRecorder(MockDetails mockDetails) {

        this.mockDetails = mockDetails;
    }

    @Override
    public void reportInvocation(MethodInvocationReport methodInvocationReport) {
        DescribedInvocation invocation = methodInvocationReport.getInvocation();
        InvocationImpl i = (InvocationImpl)invocation;
        
        if (mockDetails.isStubbed(i.getMethod().getName())) {
            System.out.println("STUBBED = " + i.getMethod().getName());
        } else {
            System.out.println("NOT STUBBED = " + i.getMethod().getName());
        }
        
        System.out.println("sequenceNumber = " + i.getSequenceNumber());
        System.out.println("method = " + i.getMethod().getName());

        mockDetails.record(i.getMethod());
    }
}
