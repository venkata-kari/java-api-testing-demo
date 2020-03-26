package example.utils;

import cucumber.api.Scenario;
import org.junit.Before;

public class CucumberHooks {
    private static Scenario scenario;

    public static Scenario getScenario() {
        return scenario;
    }

    @Before()
    public void prepare(Scenario scenario) {
        CucumberHooks.scenario = scenario;
    }
}

