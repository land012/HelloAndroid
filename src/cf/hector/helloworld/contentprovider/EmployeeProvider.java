package cf.hector.helloworld.contentprovider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class EmployeeProvider extends ContentProvider {
	
	private DBHelper dBHelper;
	private static UriMatcher uriMatcher;
	private static final int EMPLOYEE = 1;
	private static final int EMPLOYEE_ID = 2;
	private static Map<String, String> projectionMap;
	
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(Employees.AUTHORITY, "employee", EMPLOYEE);
		uriMatcher.addURI(Employees.AUTHORITY, "employee/#", EMPLOYEE_ID);
		
		projectionMap = new HashMap<String, String>();
		projectionMap.put(Employees.Employee._ID, Employees.Employee._ID);
		projectionMap.put(Employees.Employee.NAME, Employees.Employee.NAME);
		projectionMap.put(Employees.Employee.GENDER, Employees.Employee.GENDER);
		projectionMap.put(Employees.Employee.AGE, Employees.Employee.AGE);
	}
	
	@Override
	public boolean onCreate() {
		dBHelper = new DBHelper(this.getContext());
		return true;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dBHelper.getWritableDatabase();
		long rowid = db.insert(DBHelper.TBL_EMPLOYEES, Employees.Employee.NAME, values);
		Log.i("tag", "" + rowid);
		if(rowid>0) {
			Uri tempUri = ContentUris.withAppendedId(Employees.Employee.CONTENNT_URI, rowid);
			this.getContext().getContentResolver().notifyChange(tempUri, null);
			Log.i("tag", tempUri.toString());
			return tempUri;
		}
		return null;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();
		switch(uriMatcher.match(uri)) {
		case EMPLOYEE:
			sqb.setTables(DBHelper.TBL_EMPLOYEES);
			sqb.setProjectionMap(projectionMap);
			break;
		case EMPLOYEE_ID:
			sqb.setTables(DBHelper.TBL_EMPLOYEES);
			sqb.setProjectionMap(projectionMap);
			List<String> templist = uri.getPathSegments();
			if(templist != null) {
				for(String s : templist) {
					Log.i("tag", s);
				}
			}
			sqb.appendWhere(Employees.Employee._ID + "=" + templist.get(1));
			break;
			default:
				throw new IllegalArgumentException("Uri ´íÎó£¡" + uri);
			
		}
		
		String sortBy;
		if(TextUtils.isEmpty(sortOrder)) {
			sortBy = Employees.Employee.DEFAULT_SORT_ORDER;
		} else {
			sortBy  = sortOrder;
		}
		
		Cursor c = sqb.query(dBHelper.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortBy);
		c.setNotificationUri(this.getContext().getContentResolver(), uri);
		
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db = dBHelper.getWritableDatabase();
		int count;
		
		switch(uriMatcher.match(uri)) {
		case EMPLOYEE:
			count = db.update(DBHelper.TBL_EMPLOYEES, values, selection, selectionArgs);
			break;
		case EMPLOYEE_ID:
			List<String> templist = uri.getPathSegments();
			for(String s : templist) {
				Log.i("tag", s);
			}
			count = db.update(DBHelper.TBL_EMPLOYEES,
					values,
					Employees.Employee._ID + "=" + templist.get(1) + (TextUtils.isEmpty(selection) ? "" : " AND " + selection),
					selectionArgs);
			break;
			default:
				throw new IllegalArgumentException("Uri ´íÎó£¡" + uri);
		}
		this.getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dBHelper.getWritableDatabase();
		int count;
		
		switch(uriMatcher.match(uri)) {
		case EMPLOYEE:
			count = db.delete(DBHelper.TBL_EMPLOYEES, selection, selectionArgs);
			break;
		case EMPLOYEE_ID:
			List<String> templist = uri.getPathSegments();
			for(String s : templist) {
				Log.i("tag", s);
			}
			count = db.delete(DBHelper.TBL_EMPLOYEES,
					Employees.Employee._ID + "=" + templist.get(1) + (TextUtils.isEmpty(selection) ? "" : " AND" + selection),
					selectionArgs);
			break;
			default:
				throw new IllegalArgumentException("Uri ´íÎó£¡" + uri);
		}
		this.getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

}
