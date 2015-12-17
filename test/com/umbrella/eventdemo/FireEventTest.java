package com.umbrella.eventdemo;

import org.junit.Test;

import cf.hector.eventdemo.event.FireEvent;
import cf.hector.eventdemo.eventsource.House;
import cf.hector.eventdemo.listener.Listener;
import cf.hector.eventdemo.listener.impl.FireListener;

/**
 * ����¼�
 * @author asdf
 *
 */
public class FireEventTest {
	
	@Test
	public void test1() {
		House h1 = new House();
		
		// ��Ӽ����¼�
		h1.addListeners(new FireListener());
		// �����ڲ���
		h1.addListeners(new Listener(){
			public void listen(FireEvent e) {
				System.out.println("��һ�����������");
			}
		});
		
		// �����Ż��¼�
		h1.firing();
	}
	
	@Test
	public void test2() {
		House h = new House("tom's house");
		// ���ü����¼�
		h.setListener(new Listener() {
			@Override
			public void listen(FireEvent e) {
				House t = (House)e.getEventSource();
				System.out.println(t.getName() + " ʧ���ˣ�");
			}
		});
		// �����¼�
		h.riseFire();
	}
}
