package com.jiahaoliuliu.robolectricsample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

/**
 * Created by jiahao on 2/9/15.
 * A simple test with mockito
 */
@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18, manifest = "../app/src/main/AndroidManifest.xml")
public class SimpleMockitoTest {

    @Test
    public void testSimpleMockito() {
        // Mock creation
        List mockedList = mock(List.class);

        // Using mock object
        mockedList.add("one");
        mockedList.clear();

        // verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void testStubbing() {
        // You can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        // Stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenReturn("second");

        // Following prints "first"
        System.out.println(mockedList.get(0));

        // Following throws runtime exception
        System.out.println(mockedList.get(1));

        // Following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));
        verify(mockedList).get(0);
    }

    @Test
    public void testArgumentMatches() {
        // You can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        // Stubbing using built-in anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("element");

        // Following prints "element"
        System.out.println(mockedList.get(999));

        // you can also verify using an argument matcher
        verify(mockedList).get(anyInt());
    }

    @Test
    public void testNumberOfInvocations() {
        // using mock
        LinkedList mockedList = mock(LinkedList.class);

        // using mock
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        // following two verifications work exactly the same - times(1) is used by default
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        // exact number of invocations verifications
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        // Verification using never. Never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");

        // Verification using atLeast()/atMost()
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("twice");
        verify(mockedList, atMost(5)).add("three times");

        // times(1) is default. Therefore using times(1) explicitly can be omitted
    }

    @Test
    public void testStubbingVoidMethodsWithException() {
        LinkedList mockedList = mock(LinkedList.class);

        // Code commented to continue with the tests
        //doThrow(new RuntimeException()).when(mockedList).clear();

        // following throws RuntimeException
        mockedList.clear();
    }

    @Test
    public void testVerificationInOrder() {
        // A. Single mock whose methods must be invoked in a particular order
        List singleMock = mock(List.class);

        // Using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");

        // create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(singleMock);

        // following will make sure that add is first called with "was added" first, then with "was addede second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        // B. Multiple mocks that must be used in a particular order
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        // using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        // Create inOrder object passing any mocks that need to be verified in order
        InOrder inOrder2 = inOrder(firstMock, secondMock);

        // Following will make sure that firstMock was called before secondMock
        inOrder2.verify(firstMock).add("was called first");
        inOrder2.verify(secondMock).add("was called second");

        // Oh, and A + B can be mixed together at will
    }

    @Test
    public void testCheckingInteractions() {
        List mockOne = mock(List.class);
        List mockTwo = mock(List.class);
        List mockThree = mock(List.class);

        // Using mocks - only mockOne is interacted
        mockOne.add("one");

        // Ordinary verification
        verify(mockOne).add("one");

        // Verify that method was never called on a mock
        verify(mockOne, never()).add("two");

        // Verify that other mocks were not interacted
        verifyZeroInteractions(mockTwo, mockThree);
    }

    @Test
    public void testFindingRedundantInvocations() {
        List mockedList = mock(List.class);

        // Using mocks
        mockedList.add("one");
        mockedList.add("two");

        verify(mockedList).add("one");

        // Following verification will fail
        // Commented to not fail the test
        //verifyNoMoreInteractions(mockedList);
    }

    @Test
    public void testStubbingConsecutiveCalls() {
        List mockList = mock(List.class);

        when(mockList.add(anyString()))
                .thenThrow(new RuntimeException())
                .thenReturn(false);

        try {
            // First call: throws runtime exception
            mockList.add("Some arg");
        } catch (RuntimeException ex) {
            System.out.println("Runtime exceptions captured");
        }

        // Second call: prints "foo"
        System.out.println(mockList.add("Second call"));

        // Any consecutive call: prints "foo" as ell (last stubbing wins).
        System.out.println(mockList.add("Third call"));
    }

    @Test
    public void testSubbingWithCallbacks() {
        List mockList = mock(List.class);

        when(mockList.get(anyInt())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                return "called with arguments: " + args;
            }
        });

        // Following prints "Called with arguments: foo"
        System.out.println(mockList.get(anyInt()));
    }

    @Test
    public void testDoSomething() {
        List mockedList = mock(List.class);

        doThrow(new RuntimeException()).when(mockedList).clear();

        // This will throw a runtime exception
        try {
            mockedList.clear();
        } catch (RuntimeException ex) {
            System.out.println("Runtime exception while call clear");
        }
    }

    @Test
    public void testSpyingRealObjects() {
        List list = new LinkedList();
        List spy = spy(list);

        // Optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);

        // Using the spy calls "real" methods
        spy.add("one");
        spy.add("two");

        // Prints "one" - the first element of a list
        System.out.println(spy.get(0));

        // Size() method was stubbed - 100 is printed
        System.out.println(spy.size());

        //Optionally you can verify
        verify(spy).add("one");
        verify(spy).add("two");

        // Using doReturn|doAnswer|doThrow instead of when

        List list2 = new LinkedList();
        List spy2 = spy(list2);

        // This is not possible. It will throw IndexOutOfBoundsException because the list is yet
        // empty
        when(spy.get(0)).thenReturn("foo");

        System.out.println(spy.get(0));

        doReturn("foo").when(spy2).get(0);

        System.out.println(spy.get(0));
    }

    @Test
    public void testResetMock() {
        List mock = mock(List.class);
        when(mock.size()).thenReturn(10);
        mock.add(1);

        reset(mock);
        // At this point the mock forgot any interactions & stubbing
        System.out.println(mock.size());
    }

    @Test
    public void testVerificationWithTimeout() {
        List mockedList = mock(List.class);
        mockedList.get(0);

        // Passes when someMethod() is called within given time span
        verify(mockedList, timeout(100)).get(anyInt());
        // Above is an alias to:
        verify(mockedList, timeout(100).times(1)).get(anyInt());

        mockedList.get(0);
        // Passes when someMethod() is called *exactly* 2 times within given time span
        verify(mockedList, timeout(100).times(2)).get(anyInt());

        // Passes when someMethod() is called *at least* 2 times within given time span
        verify(mockedList, timeout(100).atLeast(2)).get(anyInt());

    }

}
