package myunit;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@interface Ignore {
    String[] value();
}
