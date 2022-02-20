package interview.utils.exceptions;

public class VirtualElementScrollException extends Error {

    public VirtualElementScrollException(String message,Throwable innerException){ super(message, innerException.fillInStackTrace()); }

}
