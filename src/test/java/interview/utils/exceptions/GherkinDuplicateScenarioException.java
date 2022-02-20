package interview.utils.exceptions;

public class GherkinDuplicateScenarioException extends Exception {

	protected String featureName;
	protected String scenarioName;
	
	public GherkinDuplicateScenarioException(String featureName, String scenarioName) {
		this(featureName, scenarioName, null);
	}
	
	public GherkinDuplicateScenarioException(String featureName, String scenarioName, Throwable innerException) {
		super("Duplication scenario [" + scenarioName + "] was not found in feature [" + featureName + "].", innerException);
		
		this.featureName = featureName;
		this.scenarioName = scenarioName;
	}
	
	public String getFeatureName() {
		return featureName;
	}
	
	public String getScenarioName() {
		return scenarioName;
	}

}
