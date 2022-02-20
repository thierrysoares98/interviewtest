package interview.utils.exceptions;

public class GherkinFeatureNotFoundException extends Exception {

	protected String featureName;
	
	public GherkinFeatureNotFoundException(String featureName) {
		this(featureName, null);
	}
	
	public GherkinFeatureNotFoundException(String featureName, Throwable innerException) {
		super("Expected feature was not found [" + featureName + "].", innerException);
		
		this.featureName = featureName;
	}
	
	public String getFeatureName() {
		return featureName;
	}
	
}
