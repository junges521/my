package app.main.broad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import app.main.ui.LoginActivity;

public class StartupReceiver extends BroadcastReceiver {
    private static final String TAG = "StartupRec";

	@Override
    public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
                    System.out.println("开机启动了");
                    Intent sayHelloIntent=new Intent(context,LoginActivity.class);
                    sayHelloIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.startActivity(sayHelloIntent);
            } else {
                    Log.e(TAG, "Received unexpected intent " + intent.toString());
            }
    }
}
