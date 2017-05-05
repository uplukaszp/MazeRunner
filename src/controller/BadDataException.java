package controller;

public class BadDataException extends Exception {
	public BadDataException(Double value) {
		super("Entered data: "+value+" is incorrect");
	}
	public BadDataException(int value)
	{
		super("Entered data: "+value+" is incorrect");
	}
}
