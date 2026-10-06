
package com.qa.unit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    UserServiceTest.class,
    ValidationUtilsTest.class
})
public class AllUnitTests {
    // This class remains empty.
    // It is used to run all unit tests together.
}