package com.devon1337.RPG.Utils.Dialog;

import lombok.Getter;

public class Response implements java.io.Serializable {

	private static final long serialVersionUID = 1111953368683528207L;
	
	@Getter
	Requirement req;
	
	@Getter
	String command, message;
	
	@Getter
	Dialog line;

	// No command
	public Response(String message) {
		this.message = message;
	}
	
	public Response(String message, Requirement req) {
		this.message = message;
		this.req = req;
	}
	
	public Response(String command, String message) {
		this.command = command;
		this.message = message;
	}
	
	public Response(String command, String message, Requirement req) {
		this.command = command;
		this.message = message;
		this.req = req;
	}
	
	// TODO: Insert translation of dialog code to getdialog command
	public Response(Dialog line, String message) {
		
	}
	
	public Response(Dialog line, String message, Requirement req) {
		
	}
}