package interview.utils.exceptions;

public class AjaxException extends Error {

    public AjaxException(String message, Throwable innerException){ super(message, innerException.fillInStackTrace()); }

}
