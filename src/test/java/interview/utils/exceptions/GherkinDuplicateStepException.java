package interview.utils.exceptions;

public class GherkinDuplicateStepException extends Exception {

	protected String step;
	
	public GherkinDuplicateStepException(String step) {
		this(step, null);
	}
	
	public GherkinDuplicateStepException(String step, Throwable innerException) {
		super("Duplicated step [" + step + "] was found.", innerException);
		
		this.step = step;
	}
	
	public String getStep() {
		return step;
	}
	
}
