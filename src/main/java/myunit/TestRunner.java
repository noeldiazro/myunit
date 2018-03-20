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
	    if (isNotIgnoredTestMethod(method))
		testMethods.add(method);
    }

    private boolean isNotIgnoredTestMethod(Method method) {
	return isTestMethod(method) && !isIgnored(method);
    }
    
    private boolean isTestMethod(Method method) {
	return method.isAnnotationPresent(TestMethod.class);
    }

    private boolean isIgnored(Method method) {
	return method.isAnnotationPresent(Ignore.class);
    }

    Set<IgnoredMethod> getIgnoredMethods() {
	Set<IgnoredMethod> result = new HashSet<IgnoredMethod>();
	for (Method method: testClass.getMethods())
	    if (isIgnored(method))
		result.add(new IgnoredMethod(method));
	return result;
    }
}
