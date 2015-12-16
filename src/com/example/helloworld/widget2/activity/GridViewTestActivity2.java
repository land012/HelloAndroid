package com.example.helloworld.widget2.activity;


import com.example.helloworld.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
/**
 * 
 * @author asdf
 * 不明白为什么这么实现？？？？？？？？？？？？？？
 *
 */
public class GridViewTestActivity2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_gridview);
		
		int[] imgs = {
				R.drawable.ic_launcher,
				R.drawable.ic_launcher,
				R.drawable.ic_launcher,
				R.drawable.ic_launcher,
				R.drawable.ic_launcher,
				R.drawable.ic_launcher
		};
		
		GridView gvTest = (GridView)this.findViewById(R.id.gv_fruit);
		
		gvTest.setAdapter(new MyAdapter(this, imgs));
		
	}
	
	private final class MyAdapter extends BaseAdapter {
		
		Context context;
		int[] resources;
		
		public MyAdapter(Context context, int[] resources) {
			this.context = context;
			this.resources = resources;
		}

		@Override
		public int getCount() {
			return resources.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = null;
			if(convertView == null) {
				imageView = new ImageView(this.context);
				imageView.setLayoutParams(new GridView.LayoutParams(45,45));
				imageView.setAdjustViewBounds(false);
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(8, 8, 8, 8);
			} else {
				imageView = (ImageView)convertView;
			}
			imageView.setImageResource(resources[position]);
			return imageView;
		}
		
	}

}
