package com.umbrella.eventdemo.eventsource;

import java.util.ArrayList;
import java.util.List;

import com.umbrella.eventdemo.event.FireEvent;
import com.umbrella.eventdemo.listener.Listener;

public class House {
	private List<Listener> listeners = new ArrayList<Listener>(); // 监听器列表
	
	private Listener listener; // 监听器
	
	private String name;
	
	public House() {
		
	}
	
	public House(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void addListeners(Listener l) {
		this.listeners.add(l);
	}
	
	public void setListener(Listener l) {
		this.listener = l;
	}
	
	/**
	 * 着火方法
	 */
	public void firing() {
		for(Listener l : this.listeners) {
			l.listen(new FireEvent(this));
		}
	}
	
	public void riseFire() {
		this.listener.listen(new FireEvent(this));
	}
}
