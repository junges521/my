
package app.main.ui;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;
import app.main.jxt.R;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

/**
 * @author Administrator
 *
 */
public class SharedApplication extends Application {

	private PushAgent mPushAgent;
	private static  SharedApplication mInstance=new SharedApplication();
	private static final String TAG = SharedApplication.class.getSimpleName();
	private static final int SOCKET_DELAY = 1000 * 6;
	private static final int CONN_DELAY = 1000 * 5;
	public static final String HTTP_TEL_ADDR = "http://192.168.155.1:8080/dwzStruts/";//电信请求地址
	public static final String HTTP_CER_ADDR = "http://192.168.155.1:8080/dwzStruts/";//教育网请求地址
	public static final String HTTP_DEF_PAGE = "dwz/usersManagerAction!mobileLogin.action";

	public static final String INFO_PAGE = "dwz/usersManagerAction!mobileLogin.action";

	public static final String TEL_PAGE = HTTP_TEL_ADDR + HTTP_DEF_PAGE;
	public static final String CER_PAGE = HTTP_CER_ADDR + HTTP_DEF_PAGE;

	public static final String userPwd = "userPwd";
	public static final String USERNAME = "userName";
	public static final String IDENTITY = "identify";
	public static final String VIEWSTATE = "__VIEWSTATE";

	public static final String VIEWSTATE_KEY = "dDwxOTA0NTQ3NDgwO3Q8O2w8aTwxPjs+O2w8dDw7bDxpPDg+O2k8MTM+O2k8MTU+Oz47bDx0PHA8O3A8bDxvbmNsaWNrOz47bDx3aW5kb3cuY2xvc2UoKVw7Oz4+Pjs7Pjt0PHA8bDxWaXNpYmxlOz47bDxvPGY+Oz4+Ozs+O3Q8O2w8aTwwPjs+O2w8dDw7bDxpPDE+Oz47bDx0PHA8bDxpbm5lcmh0bWw7PjtsPFw8dGFibGUgd2lkdGg9JzEwMCUnIGJvcmRlcj0nMCcgY2VsbHNwYWNpbmc9JzAnIGNlbGxwYWRkaW5nPScwJ1w+XDwvdGFibGVcPlw8c2NyaXB0IHR5cGU9J3RleHQvamF2YXNjcmlwdCdcPnZhciBvTWFycXVlZSA9IGRvY3VtZW50LmdldEVsZW1lbnRCeUlkKCdtcScpXDt2YXIgaUxpbmVIZWlnaHQgPSAxNlw7dmFyIGlMaW5lQ291bnQgPSAwXDt2YXIgaVNjcm9sbEFtb3VudCA9IDFcOyBmdW5jdGlvbiBydW4oKXtvTWFycXVlZS5zY3JvbGxUb3AgKz0gaVNjcm9sbEFtb3VudFw7aWYgKCBvTWFycXVlZS5zY3JvbGxUb3AgPT0gaUxpbmVDb3VudCAqIGlMaW5lSGVpZ2h0ICl7b01hcnF1ZWUuc2Nyb2xsVG9wID0gMFw7fWlmICggb01hcnF1ZWUuc2Nyb2xsVG9wICUgaUxpbmVIZWlnaHQgPT0gMCApIHt3aW5kb3cuc2V0VGltZW91dCggJ3J1bigpJywgMjAwMCApXDt9IGVsc2Uge3dpbmRvdy5zZXRUaW1lb3V0KCAncnVuKCknLCA1MCApXDt9fW9NYXJxdWVlLmlubmVySFRNTCArPSBvTWFycXVlZS5pbm5lckhUTUxcO3dpbmRvdy5zZXRUaW1lb3V0KCAncnVuKCknLCAyMDAwIClcO1w8L3NjcmlwdFw+Oz4+Ozs+Oz4+Oz4+Oz4+Oz4+O2w8aW1nREw7aW1nVEM7aW1nUU1NOz4+hxD4/jdz1BUmc1xl7POcfROPk9U=";

	public static HttpClient client;
	 public boolean m_bKeyRight = true;
	 BMapManager mBMapManager = null;
	

	 public static final String strKey = "F5b98e644741f87b4d40b7a6ee3d3637";
		
	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
	
		initHttpClient();
		Log.i(TAG, "ONCREATE");
		initEngineManager(this);
		MobclickAgent.updateOnlineConfig( this );
		MobclickAgent.setDebugMode( true );
		mPushAgent = PushAgent.getInstance(this);
		mPushAgent.setDebugMode(true);
		
		
		/**
		 * 该Handler是在IntentService中被调用，故
		 * 1. 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
		 * 2. IntentService里的onHandleIntent方法是并不处于主线程中，因此，如果需调用到主线程，需如下所示;
		 * 	      或者可以直接启动Service
		 * */
		UmengMessageHandler messageHandler = new UmengMessageHandler(){
			@Override
			public void dealWithCustomMessage(final Context context, final UMessage msg) {
				new Handler(getMainLooper()).post(new Runnable() {
					
					public void run() {
						// TODO Auto-generated method stub
						UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
						Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
					}
				});
			
			}
			@Override
			public Notification getNotification(Context context,
					UMessage msg) {
				switch (msg.builder_id) {
				case 1:
					NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
					RemoteViews myNotificationView = new RemoteViews(context.getPackageName(), R.layout.notification_view);
					myNotificationView.setTextViewText(R.id.notification_title, msg.title);
					myNotificationView.setTextViewText(R.id.notification_text, msg.text);
					myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
					myNotificationView.setImageViewResource(R.id.notification_small_icon, getSmallIconId(context, msg));
					builder.setContent(myNotificationView);
					Notification mNotification = builder.build();
					//由于Android v4包的bug，在2.3及以下系统，Builder创建出来的Notification，并没有设置RemoteView，故需要添加此代码
					mNotification.contentView = myNotificationView;
					return mNotification;
				default:
					//默认为0，若填写的builder_id并不存在，也使用默认。
					return super.getNotification(context, msg);
				}
			}
		};
		mPushAgent.setMessageHandler(messageHandler);

		/**
		 * 该Handler是在BroadcastReceiver中被调用，故
		 * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
		 * */
		UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler(){
			@Override
			public void dealWithCustomAction(Context context, UMessage msg) {
				Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
			}
		};
		mPushAgent.setNotificationClickHandler(notificationClickHandler);

	}
	
	



	public void initEngineManager(Context context) {
        if (mBMapManager == null) {
            mBMapManager = new BMapManager(context);
        }

        if (!mBMapManager.init(strKey,new MyGeneralListener())) {
            Toast.makeText(SharedApplication.getInstance().getApplicationContext(), 
                    "BMapManager  初始化错误", Toast.LENGTH_LONG).show();
        }
	}
	
	public static  SharedApplication getInstance() {
		return mInstance;
	}
	@Override
	public void onTerminate() {
		super.onTerminate();
	}


	public static boolean networkIsAvailable(Context context) {
		ConnectivityManager cManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cManager.getActiveNetworkInfo();
		if (info == null) {
			return false;
		}
		if (info.isConnected()) {
			return true;
		}
		return false;
	}


	private void initHttpClient() {
		SharedApplication.client = new DefaultHttpClient();
		SharedApplication.client.getParams().setIntParameter(
				HttpConnectionParams.SO_TIMEOUT, SOCKET_DELAY);
		SharedApplication.client.getParams().setIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, CONN_DELAY);
	}
	
	 static class MyGeneralListener implements MKGeneralListener {
	        
	        public void onGetNetworkState(int iError) {
	            if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
	                Toast.makeText(SharedApplication.getInstance().getApplicationContext(), "您的网络出错啦！",
	                    Toast.LENGTH_LONG).show();
	            }
	            else if (iError == MKEvent.ERROR_NETWORK_DATA) {
	                Toast.makeText(SharedApplication.getInstance().getApplicationContext(), "输入正确的检索条件！",
	                        Toast.LENGTH_LONG).show();
	            }
	            // ...
	        }

	        public void onGetPermissionState(int iError) {
	            if (iError ==  MKEvent.ERROR_PERMISSION_DENIED) {
	                //授权Key错误�?
	              //  Toast.makeText(SharedApplication.getInstance().getApplicationContext(), 
	               //         "请在 "+this.getClass().getName()+"文件输入正确的授权Key", Toast.LENGTH_LONG).show();
	                SharedApplication.getInstance().m_bKeyRight = false;
	            }
	        }
	    }
}
