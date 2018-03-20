package myunit;

import java.lang.reflect.Method;

class IgnoredMethod {
    private final Method method;
    
    IgnoredMethod(Method method) {
	this.method = method;
    }

    String getName() {
	return method.getName();
    }

    String getReasonForIgnoring() {
	Ignore annotation = method.getAnnotation(Ignore.class);
	return annotation.value();
    }
}
