package myunit;

import java.lang.reflect.Method;
import java.util.Set;
import junit.framework.TestCase;

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
    
    private void assertMethods(Set<Method> retrievedMethods, String... expectedMethodNames) {
	assertEquals(expectedMethodNames.length, retrievedMethods.size());
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
