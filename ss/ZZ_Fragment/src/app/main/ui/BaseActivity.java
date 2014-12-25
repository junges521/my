package app.main.ui;

import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import app.main.jxt.R;
import app.main.url.SlidingMenu;

public class BaseActivity extends FragmentActivity{
	SlidingMenu mSlidingMenu;
	LeftFragment leftFragment;
	RightFragment rightFragment;
	SchoolNewesFragment centerFragment;
	My_infoFragment myInfoFragment;
	
	SchoolMsgFragment schoolmsgFragment;
	SchoolScoreFragment schoolscoreFragment;
	SchoolDayFragment schoolDayFragment;
	SchoolTaskFrament schooltaskFragment;
	
	
	

	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		PushAgent.getInstance(this).onAppStart();
		mSlidingMenu = (SlidingMenu) findViewById(R.id.slidingMenu);
		mSlidingMenu.setLeftView(getLayoutInflater().inflate(
				R.layout.left_frame, null));
		mSlidingMenu.setRightView(getLayoutInflater().inflate(
				R.layout.right_frame, null));
		mSlidingMenu.setCenterView(getLayoutInflater().inflate(
				R.layout.center_frame, null));
		FragmentTransaction t;
		t = this.getSupportFragmentManager().beginTransaction();
		
		
		leftFragment = new LeftFragment();
		rightFragment = new RightFragment();
		t.replace(R.id.left_frame, leftFragment);
		t.replace(R.id.right_frame, rightFragment);

		centerFragment = new SchoolNewesFragment();
		myInfoFragment=new My_infoFragment();
		schoolmsgFragment=new SchoolMsgFragment();
		schoolDayFragment=new SchoolDayFragment();
		schoolscoreFragment=new SchoolScoreFragment();
		schooltaskFragment=new SchoolTaskFrament();
		
		t.replace(R.id.center_frame, centerFragment);
		Intent intent=this.getIntent();
	
		
		if(intent.getStringExtra("action")!=null){
			
			if(intent.getStringExtra("action").equals("schoolnews")){
				t.replace(R.id.center_frame, centerFragment);
			}
			if(intent.getStringExtra("action").equals("schooltasks")){
				t.replace(R.id.center_frame, schooltaskFragment);
			}
			if(intent.getStringExtra("action").equals("schoolday")){
				t.replace(R.id.center_frame, schoolDayFragment);
			}
			if(intent.getStringExtra("action").equals("schoolscore")){
				t.replace(R.id.center_frame, schoolscoreFragment);
			}
			if(intent.getStringExtra("action").equals("myInfo")){
				t.replace(R.id.center_frame, myInfoFragment);
			}
		}
		
	
		t.commit();
	}

	public void llronclick(View v) {
		Intent intent=new Intent();
		switch (v.getId()) {
		case R.id.mybaby_info:
			 intent= new Intent(this, MainActivity.class);
				
			 intent.putExtra("action", "myInfo");
			 this.finish();
			startActivity(intent);
	/*	myInfoFragment=new My_infoFragment();	
	 * Toast.makeText(getApplicationContext(), "jiben", Toast.LENGTH_LONG).show();
			t.replace(R.id.center_frame,centerFragment);
			t.commit();
			break;*/
		case R.id.school_day:
			 intent= new Intent(this, MainActivity.class);
			
			 intent.putExtra("action", "schoolday");
			 this.finish();
			startActivity(intent);
			
			
		case R.id.school_msg:
			 intent= new Intent(this, MainActivity.class);
			
			 intent.putExtra("action", "schoolmsg");
			startActivity(intent);
			
		case R.id.school_news:
			 intent= new Intent(this, MainActivity.class);
			
			 intent.putExtra("action", "schoolnews");
			startActivity(intent);	
			

			
		case R.id.school_tasks:
			 intent= new Intent(this, MainActivity.class);
			
			 intent.putExtra("action", "schooltasks");
			startActivity(intent);
			
		case R.id.parents_chat:
			 intent= new Intent(this, MainActivity.class);
			
			 intent.putExtra("action", "schoolparents");
			 this.finish();
			startActivity(intent);
		default:
			break;
		}
	}

	public void showLeft() {
		mSlidingMenu.showLeftView();
	}

	public void showRight() {
		mSlidingMenu.showRightView();
	}
	

	public  void changeFragment(Fragment f){  //改变右边
		FragmentTransaction ft=this.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.center_frame, f);  
    
            ft.addToBackStack(null);  
            
        ft.commit();  
    }  
	public  void changeLeftFragment(Fragment f){  //改变左边
		FragmentTransaction ft=this.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.center_frame, f);  
    
            ft.addToBackStack(null);  
            
        ft.commit();  
    }  
	public void onResume() {
	    super.onResume();
	    MobclickAgent.onPageStart("MainScreen"); //统计页面
	    MobclickAgent.onResume(this);
	}
	public void onPause() {
	    super.onPause();
	    MobclickAgent.onPageEnd("MainScreen"); 
	    MobclickAgent.onPause(this);
	}

}
