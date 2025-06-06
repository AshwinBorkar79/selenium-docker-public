package com.AB.Dummy.assertions;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DummyFileComparisonTest {

    @Test
    public void fileTest()
    {
        Path expectedFile = Paths.get("src/test/java/com/AB/Dummy/assertions/Expected.txt");
        Path actualFile = Paths.get("src/test/java/com/AB/Dummy/assertions/Actual.txt");
        Assertions.assertThat(actualFile).hasSameTextualContentAs(expectedFile);
    }
}
