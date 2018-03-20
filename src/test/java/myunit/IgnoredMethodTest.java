package myunit;

import junit.framework.TestCase;

public class IgnoredMethodTest extends TestCase {
    public static final String REASON = "WIP";
    
    public void testCreation() throws NoSuchMethodException {
	final String methodName = "ignoredTestMethod";
	final IgnoredMethod ignoredMethod = new IgnoredMethod(TestClass.class.getMethod(methodName));
	assertEquals(methodName, ignoredMethod.getName());
	assertEquals(REASON, ignoredMethod.getReasonForIgnoring());
    }

    public void testCreation_NotTestMethod() throws NoSuchMethodException {

    }
}

class TestClass {
    @Ignore(IgnoredMethodTest.REASON)
    @TestMethod public void ignoredTestMethod() {}

    public void notATestMethod() {}
}

