package app.main.broad;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;

/**
 * @author л����2013-11-3��дMyservice.java
 * ���в���ý������
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
	                	//Toast.makeText(getApplicationContext(), "���������", duration)
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
	                // �ڵ���stop�������Ҫ�ٴ�ͨ��start���в���,��Ҫ֮ǰ����prepare����
	                mediaPlayer.prepare();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }*/

}
