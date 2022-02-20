package interview.utils.exceptions;

public class DataTableCTNotFoundException extends Exception {

	protected int ct;
	
	public DataTableCTNotFoundException(int ct) {
		this(ct, null);
	}
	
	public DataTableCTNotFoundException(int ct, Throwable innerException) {
		super("CT [" + String.valueOf(ct) + "] was not found on datatable.", innerException);
		
		this.ct = ct;
	}
	
	public int getCT() {
		return ct;
	}
	
}
