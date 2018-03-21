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

    String[] getReasonsForIgnoring() {
	return  method.getAnnotation(Ignore.class).reasons();
    }

    String getInitials() {
	return method.getAnnotation(Ignore.class).initials();
    }
}
