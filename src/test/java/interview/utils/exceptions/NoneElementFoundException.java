package interview.utils.exceptions;

public class NoneElementFoundException extends Exception{
	
	public NoneElementFoundException() {
		
	}
	
	public NoneElementFoundException(Throwable innerException) {
		super("None element found.", innerException);
	}

}
