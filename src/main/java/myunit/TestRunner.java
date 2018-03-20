package myunit;

import java.util.HashSet;
import java.lang.reflect.Method;
import java.util.Set;

class TestRunner {

    private final Class testClass;
    private Set<Method> testMethods = null;
    
    TestRunner(Class testClass) {
	this.testClass = testClass;
    }

    TestRunner(String className) throws ClassNotFoundException {
	this(Class.forName(className));
    }

    Set<Method> getTestMethods() {
	if (testMethods == null)
	    loadTestMethods();

	return testMethods;
    }

    private void loadTestMethods() {
	testMethods = new HashSet<Method>();
	for (Method method: testClass.getMethods())
	    if (isTestMethod(method))
		testMethods.add(method);
    }

    private boolean isTestMethod(Method method) {
	return method.isAnnotationPresent(TestMethod.class);
    }

}
