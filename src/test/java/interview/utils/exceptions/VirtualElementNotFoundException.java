package interview.utils.exceptions;

public class VirtualElementNotFoundException extends Error {

    public VirtualElementNotFoundException(String message,Throwable innerException){ super(message, innerException.fillInStackTrace()); }

}
