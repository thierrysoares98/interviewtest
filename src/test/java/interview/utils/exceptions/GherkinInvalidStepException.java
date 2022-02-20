package interview.utils.exceptions;

public class GherkinInvalidStepException  extends Exception {

	protected String methodName;
	
	public GherkinInvalidStepException(String methodName) {
		this(methodName, null);
	}
	
	public GherkinInvalidStepException(String methodName, Throwable innerException) {
		super("Method is invalid [" + methodName + "], no annotation step found.", innerException);
		
		this.methodName = methodName;
	}
	
	public String getStepName() {
		return methodName;
	}
	
}
