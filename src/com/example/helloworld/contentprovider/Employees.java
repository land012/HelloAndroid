package com.example.helloworld.contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Employees {
	public static final String AUTHORITY = "com.umbrella.sg14contentprovidertest.provider.Employees";
	private Employees() {
		
	}
	
	public static final class Employee implements BaseColumns {
		private Employee() {
			
		}
		
		public static final Uri CONTENNT_URI = Uri.parse("content://" + Employees.AUTHORITY + "/employee");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.umbrella.sg14contentprovidertest.provider.employees";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.umbrella.sg14contentprovidertest.provider.employees";
		
		public static final String NAME = "name";
		public static final String GENDER = "gender";
		public static final String AGE = "age";
		
		public static final String DEFAULT_SORT_ORDER = NAME + " ASC";
	}
}
