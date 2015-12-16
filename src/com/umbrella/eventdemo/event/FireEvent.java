package com.umbrella.eventdemo.event;

import java.util.EventObject;

public class FireEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5908639186477221122L;
	private Object eventSource;
	
	public FireEvent(Object obj) {
		super(obj);
		this.eventSource = obj;
	}
	
	public Object getEventSource() {
		return this.eventSource;
	}
}
