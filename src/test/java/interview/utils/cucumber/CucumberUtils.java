package interview.utils.cucumber;

import interview.utils.exceptions.GherkinException;
import interview.utils.exceptions.GherkinScenarioNotFoundException;
import io.cucumber.core.api.Scenario;


public class CucumberUtils {
    public boolean getStatus(Scenario scenario){
        boolean scenarioStatus = false;
        try {
            if (!scenario.getStatus().name().equals("PASSED")) {
                scenarioStatus = false;
            }else{
                scenarioStatus = true;
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return scenarioStatus;
    }
}
