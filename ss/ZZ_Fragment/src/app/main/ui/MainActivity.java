package app.main.ui;

import java.io.File;

import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;
import app.main.jxt.R;
import app.main.url.SlidingMenu;

/**
 * @author 谢俊良2013-10-27编写MainActivity.java
 * 
 */
public class MainActivity extends BaseActivity implements MsgEditorFragment.chatFragmentcallback{

	/**缓存文件
	 * 对家校通产生的缓存文件（图片）信息退出时进行删除
	 * */
	public File cache;
	
	

	/**
	 * 
	 */
	Intent intent = new Intent("app.jxt.myservice");//intent service服务



	private long currentTimeMillis=0L;
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		UmengUpdateAgent.setUpdateOnlyWifi(false);
		UmengUpdateAgent.update(this);

		cache = new File(Environment.getExternalStorageDirectory(), "cache");
		/**如果目录不存在就新建一个*/
		if (!cache.exists())  cache.mkdir();
		
		
		mSlidingMenu = (SlidingMenu) findViewById(R.id.slidingMenu);
		mSlidingMenu.setLeftView(getLayoutInflater().inflate(
				R.layout.left_frame, null));
		mSlidingMenu.setRightView(getLayoutInflater().inflate(
				R.layout.right_frame, null));
		mSlidingMenu.setCenterView(getLayoutInflater().inflate(
				R.layout.center_frame, null));

		FragmentTransaction t = this.getSupportFragmentManager()
				.beginTransaction();

		leftFragment = new LeftFragment();
		rightFragment = new RightFragment();
		t.replace(R.id.left_frame, leftFragment);
		t.replace(R.id.right_frame, rightFragment);

		centerFragment = new SchoolNewesFragment();

		t.replace(R.id.center_frame, centerFragment);

		t.commit();
	}

	/* (non-Javadoc) 
	 * 进行返回按钮时的消息提醒
	 * @see android.support.v4.app.FragmentActivity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		  if (keyCode == KeyEvent.KEYCODE_BACK  
	                && event.getAction() == KeyEvent.ACTION_DOWN) {  
	            if ((System.currentTimeMillis() - currentTimeMillis) > 4000) {  
	                Toast.makeText(getApplicationContext(), "再按一次退出程序",  
	                        Toast.LENGTH_SHORT).show();  
	                currentTimeMillis = System.currentTimeMillis();  
	            	this.showNotify();
	            } else {  

	        		stopService(intent);//停止服务
	                finish();  
	                System.exit(0);  
	            }  
	  
	            return true;  
	        }  
	        return super.onKeyDown(keyCode, event); 
	
	}

	private void showNotify() {
		Notification notice = new Notification();
		notice.icon = R.drawable.ic_launcher;
		notice.tickerText = "您有一条新的信息";
		notice.defaults = Notification.DEFAULT_SOUND;
		//声音提醒设
		notice.when = 10L;
		// 100 毫秒延迟后，震动 250 毫秒，暂停 100 毫秒后，再震动 500 毫秒
		notice.vibrate = new long[] { 100, 250, 100, 500 };// 出错？
		notice.flags |= Notification.FLAG_AUTO_CANCEL;//点击之后自动清除
		Intent intent = new Intent(this, LoginActivity.class);
		notice.setLatestEventInfo(this, "学校通知", "家校通有新的消息提醒您",
				PendingIntent.getActivity(this, 0, intent, 0));// 即将跳转页面，还没跳转
		NotificationManager manager = (NotificationManager) this
				.getSystemService(this.NOTIFICATION_SERVICE);
		manager.cancel(0);
		manager.notify(0, notice);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Bundle bundle=new Bundle();
		bundle.putInt("op", 1);
		startService(intent);//启动服务
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		/**清除缓存文件*/
		for (File file:cache.listFiles()) {
			file.delete();
		}
		System.exit(1);
		cache.delete();
		super.onDestroy();
	}

	public File getCache() {
		return cache;
	}

	public void setCache(File cache) {
		this.cache = cache;
	}

	/*
	 * 接口囘調(non-Javadoc)
	 * @see app.main.ui.MsgEditorFragment.chatFragmentcallback#leftCallback(int)
	 */
	public void leftCallback(int position) {
		// TODO Auto-generated method stub
		/*FragmentManager manager = getFragmentManager();
         SchoolChatFragment listRight = (SchoolChatFragment) manager.findFragmentById(R.id.center_frame);
         if(listRight != null){
             listRight.setListAdapter(new ArrayAdapter<String>(MyFragmentActivity.this,
             android.R.layout.simple_list_item_1,TestData.getInstance().getData((position+1))));
         }else{
             
         }*/
	}	
	/*
	 * 测试用户是否在线以及是否登录过
	 */
	public boolean verifyLogin(){
		boolean isLogin=false;
		SharedPreferences share=super.getSharedPreferences("LOGIN", MODE_PRIVATE);
		String userName=share.getString("user", "");
		String pwd=share.getString("pwd", "");
		
		if(!"".equals(userName)&&userName!=null){
			isLogin=true;
		}
		return isLogin;
	}
	
	public void onResume() {
	    super.onResume();
	    MobclickAgent.onPageStart("MainScreen"); //统计页面
	}
	public void onPause() {
	    super.onPause();
	    MobclickAgent.onPageEnd("MainScreen"); 
	}

}
