package myunit;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Ignore {
    static final String DEFAULT_REASON = "temporarily commenting out";
    String[] reasons() default DEFAULT_REASON;
    String initials();
}
