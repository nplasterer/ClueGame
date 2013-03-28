package clueBoard;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;

public class BadConfigFormatException extends Exception{

	private String msg;
	
	public BadConfigFormatException(String message) {
		super();
		outputLog(message);
		msg = message;
	}
	
	//Random comment to change file
	public String getMessage() {
		return msg;
	}
	
	public String toString() {
		return "Config contains incorrect format.";
	}
	
	public void outputLog(String message) {
		try {
			PrintWriter out = new PrintWriter("log.txt"); //rofl
			out.println(message);
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
