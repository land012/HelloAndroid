package com.example.helloworld.servicedemo;

import android.os.RemoteException;

import com.example.helloworld.servicedemo.IPerson;

public class IPersonImpl extends IPerson.Stub {
	
	private String name;
	private int age;

	@Override
	public void setName(String name) throws RemoteException {
		this.name = name;
	}

	@Override
	public void setAge(int age) throws RemoteException {
		this.age = age;
	}

	@Override
	public String display() throws RemoteException {
		return name + "|" + age;
	}

}
