package com.devon1337.RPG.Quests;

import lombok.Getter;
import lombok.Setter;

public class Step {

	@Getter
	String Hint, Description;
	
	@Getter @Setter
	boolean Required;
	
	@Getter @Setter
	StepStatus status;
	
	@Getter @Setter
	EventFlags flag;
	
	public Step(String Hint, String Description, boolean Required, StepStatus status, EventFlags flag) {
		this.Hint = Hint;
		this.Description = Description;
		this.Required = Required;
		this.status = status;
		this.flag = flag;
	}
	
	
}
