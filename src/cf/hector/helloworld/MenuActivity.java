package cf.hector.helloworld;


import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends Activity {
	
	private static final int ITEM1 = Menu.FIRST;
	private static final int ITEM2 = Menu.FIRST + 1;
	private static final int ITEM3 = Menu.FIRST + 2;
	private static final int ITEM4 = Menu.FIRST + 3;
	private static final int ITEM5 = Menu.FIRST + 4;
	private static final int ITEM6 = Menu.FIRST + 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		TextView helloworld = (TextView)this.findViewById(R.id.helloworld);
		this.registerForContextMenu(helloworld);
		
	}
	
	/**
	 * 选项菜单
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
		menu.add(0, ITEM1, 0, "开始");
		menu.add(0, ITEM2, 0, "退出");
		SubMenu file = menu.addSubMenu("文件");
		file.add(0, ITEM5, 0, "新建");
		file.add(0, ITEM6, 0, "打开");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case ITEM1:
			this.setTitle("开始游戏！");
			break;
		case ITEM2:
			this.setTitle("退出！");
			break;
		case ITEM5:
			this.setTitle("新建文件！");
			break;
		case ITEM6:
			this.setTitle("打开文件！");
			break;
			default:
				break;
		}
		return true;
	}
	
	/**
	 * 上下文菜单
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add(0, ITEM3, 0, "红色背景");
		menu.add(0, ITEM4, 0, "蓝色背景");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case ITEM3:
			this.getWindow().setBackgroundDrawableResource(R.color.red);
			break;
		case ITEM4:
			this.getWindow().setBackgroundDrawableResource(R.color.blue);
			break;
			default:
				break;
		}
		return true;
	}
	
}
