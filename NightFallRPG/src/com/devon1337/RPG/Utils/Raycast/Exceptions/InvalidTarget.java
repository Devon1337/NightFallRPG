package com.devon1337.RPG.Utils.Raycast.Exceptions;

public class InvalidTarget extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 199143149042365944L;
	public InvalidTarget() { super(); }
	public InvalidTarget(String message) { super(message); }
	public InvalidTarget(String message, Throwable cause) { super(message,cause); }
	public InvalidTarget(Throwable cause) { super(cause); }
	
}