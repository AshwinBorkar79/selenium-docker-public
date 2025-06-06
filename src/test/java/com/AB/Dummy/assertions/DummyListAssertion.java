package com.AB.Dummy.assertions;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class DummyListAssertion {

    @Test(dataProvider = "getData")
    public void ListTest(List<String> input)
    {
        List<String> expectedList = Arrays.asList("apple", "ball");
        SoftAssertions.assertSoftly(softAssertions ->
        {
            softAssertions.assertThat(input)
                    .hasSize(3)
                    .contains("apple")
                    .doesNotContain("cat")
                    .containsOnlyOnce("card")
                    .containsAll(expectedList)
                    .allSatisfy(element -> {softAssertions.assertThat(element.length()).isGreaterThanOrEqualTo(4);});
        }
        );
    }

    @DataProvider
    public Object[] getData()
    {
        return new Object[] {
                Arrays.asList("apple", "ball", "card")
        };
    }
}
