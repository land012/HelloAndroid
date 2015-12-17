package cf.hector.eventdemo.listener;

import java.util.EventListener;

import cf.hector.eventdemo.event.FireEvent;

public interface Listener extends EventListener {
	public void listen(FireEvent e);
}
