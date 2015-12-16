package com.umbrella.eventdemo;

import org.junit.Test;

import com.umbrella.eventdemo.event.FireEvent;
import com.umbrella.eventdemo.eventsource.House;
import com.umbrella.eventdemo.listener.Listener;
import com.umbrella.eventdemo.listener.impl.FireListener;

/**
 * 添加事件
 * @author asdf
 *
 */
public class FireEventTest {
	
	@Test
	public void test1() {
		House h1 = new House();
		
		// 添加监听事件
		h1.addListeners(new FireListener());
		// 匿名内部类
		h1.addListeners(new Listener(){
			public void listen(FireEvent e) {
				System.out.println("又一处起火啦！！");
			}
		});
		
		// 触发着火事件
		h1.firing();
	}
	
	@Test
	public void test2() {
		House h = new House("tom's house");
		// 设置监听事件
		h.setListener(new Listener() {
			@Override
			public void listen(FireEvent e) {
				House t = (House)e.getEventSource();
				System.out.println(t.getName() + " 失火了！");
			}
		});
		// 触发事件
		h.riseFire();
	}
}
