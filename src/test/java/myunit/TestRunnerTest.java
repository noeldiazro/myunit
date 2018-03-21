package myunit;

import java.lang.reflect.Method;
import java.util.Set;
import junit.framework.TestCase;
import java.util.Iterator;

public class TestRunnerTest extends TestCase {
    private TestRunner runner;
    
    public void testRunnerCreation_FromClassName() throws ClassNotFoundException {
	runner = new TestRunner(NoTestMethodsTestClass.class.getName());
    }

    public void testGetTestMethods_NoMethods() {
	runner = new TestRunner(NoTestMethodsTestClass.class);
	assertMethods(runner.getTestMethods());
    }

    public void testGetTestMethods_OneMethod() {
	runner = new TestRunner(OneTestMethodTestClass.class);
	assertMethods(runner.getTestMethods(), "testMethod_A");
    }

    public void testGetTestMethods_MultipleMethods() {
	runner = new TestRunner(MultipleTestMethodsTestClass.class);
	assertMethods(runner.getTestMethods(),
		      "testMethod_A",
		      "testMethod_B",
		      "anotherTestMethod");
    }

    public void testGetTestMethods_IgnoreMethod() {
	runner = new TestRunner(IgnoreMethodTestClass.class);
	assertMethods(runner.getTestMethods(),
		      "testMethod_A",
		      "testMethod_B",
		      "anotherTestMethod");
    }
    
    private void assertMethods(Set<Method> retrievedMethods, String... expectedMethodNames) {
	assertEquals(expectedMethodNames.length, retrievedMethods.size());
    }

    public void testIgnoreReason() {
	runner = new TestRunner(IgnoreMethodTestClass.class);
	Set<IgnoredMethod> ignoredMethods = runner.getIgnoredMethods();
	IgnoredMethod ignoredMethod = getSetElement(ignoredMethods);
	assertEquals("testMethodToIgnore", ignoredMethod.getName());
	String[] reasons = ignoredMethod.getReasonsForIgnoring();
	
	assertEquals("WIP", ignoredMethod.getReasonsForIgnoring()[0]);
    }

    private <T> T getSetElement(Set<T> set) {
	Iterator<T> iterator = set.iterator();
	return iterator.next();
    }
}

class NoTestMethodsTestClass {
    
    public void notATestMethod() {
	System.out.println("I'm not a test method");
    }
    
}

class OneTestMethodTestClass extends NoTestMethodsTestClass {

    @TestMethod public void testMethod_A() {}
    
}

class MultipleTestMethodsTestClass extends OneTestMethodTestClass {

    @TestMethod public void testMethod_B() {}

    @TestMethod public void anotherTestMethod() {}
}

class IgnoreMethodTestClass extends MultipleTestMethodsTestClass {

    @Ignore(reasons="WIP", initials="ndr")
    @TestMethod public void testMethodToIgnore() {}
}
