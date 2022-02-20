package interview.utils.exceptions;

public class GenericException extends Exception {

	public GenericException(Throwable innerException) {
		super("An generic exception occured.", innerException);
	}
	
}
