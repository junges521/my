package app.main.broad;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;

/**
 * @author 谢俊良2013-11-3编写Myservice.java
 * 进行播放媒体音乐
 *
 */
public class Myservice extends Service {
	   private MediaPlayer mediaPlayer;
	 @Override
	    public IBinder onBind(Intent arg0) {
	        return null;
	    }
	 
	    @Override
	    public void onCreate() {
	       // Log.v(TAG, "onCreate");
/*	        if (mediaPlayer == null) {
	            mediaPlayer = MediaPlayer.create(this, R.raw.tmp);
	            mediaPlayer.setLooping(false);
	        }*/
	    }
	 
	    @Override
	    public void onDestroy() {
	     //   Log.v(TAG, "onDestroy");
	        if (mediaPlayer != null) {
	            mediaPlayer.stop();
	            mediaPlayer.release();
	        }
	    }
	 
	    @Override
	    public void onStart(Intent intent, int startId) {
	    	if (intent != null) {
	            Bundle bundle = intent.getExtras();
	            if (bundle != null) {
	 
	                int op = bundle.getInt("op");
	                switch (op) {
	                case 1:
	                	//Toast.makeText(getApplicationContext(), "隧道发生的", duration)
	                   // play();
	                    break;
	                case 2:
	                //    stop();
	                    break;
	                case 3:
	                   // pause();
	                    break;
	                default:break;  
	                }
	 
	            }
	        }
	 
	 
	    }
/*	    public void play() {
	        if (!mediaPlayer.isPlaying()) {
	            mediaPlayer.start();
	        }
	    }
	 
	    public void pause() {
	        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
	            mediaPlayer.pause();
	        }
	    }
	 
	    public void stop() {
	        if (mediaPlayer != null) {
	            mediaPlayer.stop();
	            try {
	                // 在调用stop后如果需要再次通过start进行播放,需要之前调用prepare函数
	                mediaPlayer.prepare();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }*/

}
