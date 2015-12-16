package com.example.helloworld.filedemo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class FileService {
	
	private Context context;
	
	public FileService() {
		
	}
	
	public FileService(Context context) {
		this.context = context;
	}
	
	public void save(String filename, String msg) throws Exception {
		FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
		fos.write(msg.getBytes());
		fos.close();
	}
	
	/**
	 * 读取文件
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public String read(String filename) throws Exception {
		FileInputStream fis = context.openFileInput(filename);
		
		
//		byte[] buffer = new byte[1024];
//		int len = 0;
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		while((len=fis.read(buffer)) != -1) {
//			baos.write(buffer, 0, len);
//		}
//		byte[] data = baos.toByteArray();
//		baos.close();
		
		// 第二种读取 FileInputStream 的方式
		byte[] data = new byte[fis.available()];
		fis.read(data);
		
		fis.close();
		return new String(data);
	}
	
	/**
	 * 保存到 SD卡
	 * @param filename
	 * @param content
	 * @return
	 * @throws Exception 
	 */
	public boolean saveToSD(String filename, String content) throws Exception {
		Log.i("tag", Environment.getExternalStorageState());
		
		if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			Log.i("tag", Environment.getExternalStorageDirectory().toString());
			
			File file = new File(Environment.getExternalStorageDirectory(), filename);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(content.getBytes());
			fos.close();
			return true;
		}
		return false;
	}
}
