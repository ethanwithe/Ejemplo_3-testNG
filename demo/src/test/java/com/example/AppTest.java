package com.example;

import com.example.service.ThreadsafeService;
import org.testng.annotations.*;
import org.testng.Assert;

public class AppTest {
    
    ThreadsafeService threadService = new ThreadsafeService();
    public final static int INVOCATION_COUNT = 10;

    @Test(threadPoolSize = 2, invocationCount = INVOCATION_COUNT)
    public void testMultithreading(){
        threadService.increment();
        listIntegrityNotCompromised(threadService.getArray()); 
    }

    private void listIntegrityNotCompromised(int[] intArray){
        Assert.assertTrue(intArray.length > 0);
        for (int i = 0; i < intArray.length; i++){
            Assert.assertEquals(intArray[i], i);
        }
    }
    
    @Test(alwaysRun = true, dependsOnMethods = "testMultithreading")
    private void listIntegrityNotCompromisedAfterThreadExecution() {
        Assert.assertEquals(threadService.getArray().length, INVOCATION_COUNT);
        listIntegrityNotCompromised(threadService.getArray());
    }
}
