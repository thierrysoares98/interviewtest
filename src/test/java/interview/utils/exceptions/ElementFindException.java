package interview.utils.exceptions;

public class ElementFindException extends Exception {
	
	public ElementFindException(Throwable innerException) {
		super("An exception occured during element finding.", innerException);
	}

}
