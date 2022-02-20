package interview.utils.exceptions;

public class GherkinScenarioNotFoundException extends Exception {

	protected String scenarioName, featureName;
	
	public GherkinScenarioNotFoundException(String featureName, String scenarioName) {
		this(featureName, scenarioName, null);
	}
	
	public GherkinScenarioNotFoundException(String featureName, String scenarioName, Throwable innerException) {
		super(!featureName.equals("") ? "Expected scenario was not found [" + scenarioName + "] for feature [" + featureName + "]." :
									    "Expected scenario was not found [" + scenarioName + "].",
			  innerException);
		
		this.scenarioName = scenarioName;
	}
	
	public String getFeatureName() {
		return scenarioName;
	}
	
	public String getScenarioName() {
		return scenarioName;
	}
	
}
