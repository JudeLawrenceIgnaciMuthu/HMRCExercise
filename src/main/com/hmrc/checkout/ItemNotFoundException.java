package main.com.hmrc.checkout;

public class ItemNotFoundException extends Exception {
	
	private String message;
	
	public ItemNotFoundException (String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
