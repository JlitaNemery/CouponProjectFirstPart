package exceptions;

@SuppressWarnings("serial")
public class CustomerDoesntExistException extends Exception {
	public String getMessage() {
		return "customer doesnt exist";
	}
}
