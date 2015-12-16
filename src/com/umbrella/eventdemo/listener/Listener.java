package com.umbrella.eventdemo.listener;

import java.util.EventListener;

import com.umbrella.eventdemo.event.FireEvent;

public interface Listener extends EventListener {
	public void listen(FireEvent e);
}
