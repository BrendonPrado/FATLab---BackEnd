package com.fatlab.resource.exception;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ValidationError extends StandardError {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Calendar l) {
		super(status, msg, l);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addErrors(FieldMessage error) {
		this.errors.add(error);
	}

}