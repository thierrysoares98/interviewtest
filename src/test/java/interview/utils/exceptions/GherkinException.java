package interview.utils.exceptions;

public class GherkinException extends Exception {

	public GherkinException(Throwable innerException) {
		super("An execution exception occured.", innerException);
	}
	
}
