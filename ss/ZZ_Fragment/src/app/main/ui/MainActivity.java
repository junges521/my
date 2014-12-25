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
 * @author л����2013-10-27��дMainActivity.java
 * 
 */
public class MainActivity extends BaseActivity implements MsgEditorFragment.chatFragmentcallback{

	/**�����ļ�
	 * �Լ�Уͨ�����Ļ����ļ���ͼƬ����Ϣ�˳�ʱ����ɾ��
	 * */
	public File cache;
	
	

	/**
	 * 
	 */
	Intent intent = new Intent("app.jxt.myservice");//intent service����



	private long currentTimeMillis=0L;
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		UmengUpdateAgent.setUpdateOnlyWifi(false);
		UmengUpdateAgent.update(this);

		cache = new File(Environment.getExternalStorageDirectory(), "cache");
		/**���Ŀ¼�����ھ��½�һ��*/
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
	 * ���з��ذ�ťʱ����Ϣ����
	 * @see android.support.v4.app.FragmentActivity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		  if (keyCode == KeyEvent.KEYCODE_BACK  
	                && event.getAction() == KeyEvent.ACTION_DOWN) {  
	            if ((System.currentTimeMillis() - currentTimeMillis) > 4000) {  
	                Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�����",  
	                        Toast.LENGTH_SHORT).show();  
	                currentTimeMillis = System.currentTimeMillis();  
	            	this.showNotify();
	            } else {  

	        		stopService(intent);//ֹͣ����
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
		notice.tickerText = "����һ���µ���Ϣ";
		notice.defaults = Notification.DEFAULT_SOUND;
		//����������
		notice.when = 10L;
		// 100 �����ӳٺ��� 250 ���룬��ͣ 100 ��������� 500 ����
		notice.vibrate = new long[] { 100, 250, 100, 500 };// ����
		notice.flags |= Notification.FLAG_AUTO_CANCEL;//���֮���Զ����
		Intent intent = new Intent(this, LoginActivity.class);
		notice.setLatestEventInfo(this, "ѧУ֪ͨ", "��Уͨ���µ���Ϣ������",
				PendingIntent.getActivity(this, 0, intent, 0));// ������תҳ�棬��û��ת
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
		startService(intent);//��������
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		/**��������ļ�*/
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
	 * �ӿڇ��{(non-Javadoc)
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
	 * �����û��Ƿ������Լ��Ƿ��¼��
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
	    MobclickAgent.onPageStart("MainScreen"); //ͳ��ҳ��
	}
	public void onPause() {
	    super.onPause();
	    MobclickAgent.onPageEnd("MainScreen"); 
	}

}
