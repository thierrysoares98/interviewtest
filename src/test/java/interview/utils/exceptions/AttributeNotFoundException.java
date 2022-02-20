package interview.utils.exceptions;

public class AttributeNotFoundException extends Error {

    public AttributeNotFoundException(String message, Throwable innerException){ super(message, innerException.fillInStackTrace()); }

}
