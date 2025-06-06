package com.AB.Dummy.assertions;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DummyStringAssertion {

    @Test(dataProvider = "getData")
    public void StringTest(String input)
    {
        SoftAssertions.assertSoftly(softAssertions ->
        {
            softAssertions.assertThat(input)
                    .hasSize(8)
                    .startsWith("se")
                    .doesNotContain("api")
                    .doesNotContainAnyWhitespaces()
                    .containsOnlyOnce("i");
        }
        );
    }

    @DataProvider
    public Object[] getData()
    {
                return new String[]
                        {
                        "selenium",
                        "selenide",
                        "restapi"
                        };
    }
}
