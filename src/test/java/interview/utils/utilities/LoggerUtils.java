package interview.utils.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerUtils {
	
	private static String getHoraAtual() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
	
	public static String getMessage(String message) {
		return getHoraAtual() + " - " + message;
	}
	
}
