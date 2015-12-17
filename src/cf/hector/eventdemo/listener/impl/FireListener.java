package cf.hector.eventdemo.listener.impl;

import cf.hector.eventdemo.event.FireEvent;
import cf.hector.eventdemo.listener.Listener;


public class FireListener implements Listener {

	@Override
	public void listen(FireEvent e) {
		System.out.println("×Å»ğÁË£¡");
	}

}
