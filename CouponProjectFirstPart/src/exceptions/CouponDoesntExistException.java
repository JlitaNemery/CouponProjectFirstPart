package exceptions;

@SuppressWarnings("serial")
public class CouponDoesntExistException extends Exception {
	public String getMessage() {
		return "coupon doesnt exist";
	}
}
