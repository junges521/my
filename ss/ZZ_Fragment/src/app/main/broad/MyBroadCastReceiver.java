package app.main.broad;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import app.main.jxt.R;
import app.main.ui.MainActivity;

public class MyBroadCastReceiver extends BroadcastReceiver {

	NotificationManager mn=null;
    Notification notification=null;
    Context ct=null;
    MyBroadCastReceiver receiver;
    
 
    
    public MyBroadCastReceiver(Context ct) {
		super();
		this.ct = ct;

	}

	//×¢²á
    public void registerAction(String action){
        IntentFilter filter=new IntentFilter();
        filter.addAction(action);
        ct.registerReceiver(receiver, filter);
    }
    
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        String msg=intent.getStringExtra("msg");
        int id=intent.getIntExtra("who", 0);
            if(intent.getAction().equals("com.cbin.sendMsg")){
                mn=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
                notification=new Notification(R.drawable.ic_launcher, id+"·¢ËÍ¹ã²¥", System.currentTimeMillis());
                Intent it = new Intent(context,MainActivity.class);
                PendingIntent contentIntent=PendingIntent.getActivity(context,
                        0, it, 0);
                notification.setLatestEventInfo(context, 
                        "msg", msg, contentIntent);
                mn.notify(0, notification);
            }
    }

}
