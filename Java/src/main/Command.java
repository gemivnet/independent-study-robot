package main;

public class Command {

	String type;
	int amount;
	
	public Command(String type) {
		this.type = type;
	}
	
	public Command(String type, int amount) {
		this.type = type;
		this.amount = amount;
	}
	
	public String getType() {
		return type;
	}
	
	public int getAmount() {
		return amount;
	}
	
}
