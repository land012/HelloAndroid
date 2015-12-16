package com.umbrella.eventdemo.listener.impl;

import com.umbrella.eventdemo.event.FireEvent;
import com.umbrella.eventdemo.listener.Listener;


public class FireListener implements Listener {

	@Override
	public void listen(FireEvent e) {
		System.out.println("×Å»ğÁË£¡");
	}

}
