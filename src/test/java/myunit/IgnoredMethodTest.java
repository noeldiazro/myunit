package myunit;

import junit.framework.TestCase;

public class IgnoredMethodTest extends TestCase {
    public static final String REASON1 = "WIP";
    public static final String REASON2 = "Burnout";
    
    public void testCreation() throws NoSuchMethodException {
	final String methodName = "ignoredTestMethod";
	final IgnoredMethod ignoredMethod = new IgnoredMethod(TestClass.class.getMethod(methodName));
	assertEquals(methodName, ignoredMethod.getName());
	String[] reasons = ignoredMethod.getReasonsForIgnoring();
	assertEquals(REASON1, reasons[0]);
	assertEquals(REASON2, reasons[1]);
	assertEquals("ndr", ignoredMethod.getInitials());
    }

    public void testDefaultReason() throws NoSuchMethodException {
	final String methodName = "defaultReasonForIgnoring";
	final IgnoredMethod ignoredMethod = new IgnoredMethod(TestClass.class.getMethod(methodName));
	assertEquals(Ignore.DEFAULT_REASON, ignoredMethod.getReasonsForIgnoring()[0]);
    }
}

class TestClass {
    @Ignore(reasons={IgnoredMethodTest.REASON1, IgnoredMethodTest.REASON2}, initials="ndr")
    @TestMethod public void ignoredTestMethod() {}

    public void notATestMethod() {}

    @Ignore(initials="ndr")
    public void defaultReasonForIgnoring() {}
}
