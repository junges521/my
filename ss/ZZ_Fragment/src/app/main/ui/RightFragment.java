package app.main.ui;

import com.umeng.analytics.MobclickAgent;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import app.main.jxt.R;
import app.main.url.SlidingMenu;

public class RightFragment extends Fragment {
	
	private TextView mybaby_info;//基本信息
	private TextView school_msg;//学校通知
	private TextView school_tasks;//作业查看
	private TextView school_news;//新闻查看
	private TextView school_day;//今日表现
	private TextView parents_chat;//家校互动
	private TextView school_score;//成绩查看
	private TextView teacherContact;
	
	private TextView rout_school;
	private Button quickly_cut;//快捷方式
	
	SlidingMenu mSlidingMenu;
	

	private Mybaby_infoFragment mybabyInfoFragment;
	private SchoolNewesFragment centerFragment;
	private SchoolMsgFragment schoolmsgFragment;
	private SchoolDayFragment schoolDayFragment;
	private SchoolScoreFragment schoolscoreFragment;
	private SchoolTaskFrament schooltaskFragment;
	private MainActivity mainActivity;
	private Teachercontact teacherContactFrag;
	private SchoolChatFragment schoolchatFragment;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.right, null);
		
		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mybaby_info=(TextView) getActivity().findViewById(R.id.mybaby_info);
		school_msg=(TextView) getActivity().findViewById(R.id.school_msg);
		school_tasks=(TextView) getActivity().findViewById(R.id.school_tasks);
		school_news=(TextView) getActivity().findViewById(R.id.school_news);
		school_day=(TextView) getActivity().findViewById(R.id.school_day);
		parents_chat=(TextView) getActivity().findViewById(R.id.parents_chat);
		school_score=(TextView) getActivity().findViewById(R.id.school_score);
		teacherContact=(TextView)getActivity().findViewById(R.id.teacher_contact);
		rout_school=(TextView) getActivity().findViewById(R.id.rount_school);
		quickly_cut=(Button) getActivity().findViewById(R.id.quickly_cut);
		//初始化碎片
		 mainActivity=(MainActivity)getActivity();
		centerFragment = new SchoolNewesFragment();
	   
		mybabyInfoFragment=new Mybaby_infoFragment();
		schoolmsgFragment=new SchoolMsgFragment();
		schoolDayFragment=new SchoolDayFragment();
		schoolscoreFragment=new SchoolScoreFragment();
		schooltaskFragment=new SchoolTaskFrament();
		schoolchatFragment=new SchoolChatFragment();
		teacherContactFrag=new Teachercontact();
		
		//设置监听 
		mybaby_info.setOnClickListener(new OnclickListenerImpl());
		school_day.setOnClickListener(new OnclickListenerImpl());
		school_score.setOnClickListener(new OnclickListenerImpl());
		school_msg.setOnClickListener(new OnclickListenerImpl());
		school_tasks.setOnClickListener(new OnclickListenerImpl());
		parents_chat.setOnClickListener(new OnclickListenerImpl());
		school_news.setOnClickListener(new OnclickListenerImpl());
		
		quickly_cut.setOnClickListener(new OnclickListenerImpl());
		teacherContact.setOnClickListener(new OnclickListenerImpl());
		rout_school.setOnClickListener(new OnclickListenerImpl());
	}
	
	private class OnclickListenerImpl implements OnClickListener{

		public void onClick(View v) {
			
			if(mainActivity.verifyLogin()){

			// TODO Auto-generated method stub
			switch(v.getId()){
		
			case R.id.school_score: 
				
				mainActivity.changeFragment(schoolscoreFragment);
				mainActivity.showRight();
				break;
			case R.id.school_day: 	
			
			mainActivity.changeFragment(schoolDayFragment);
			mainActivity.showRight();
			break;
			case R.id.school_tasks:
	            mainActivity.changeFragment(schooltaskFragment);
	            mainActivity.showRight();
			break;
			case R.id.mybaby_info: 
				mainActivity.changeFragment(mybabyInfoFragment);
				mainActivity.showRight();
				break;
			
			case R.id.school_news: 
			mainActivity.changeFragment(centerFragment);
			mainActivity.showRight();
				break;
			

			case R.id.school_msg: 
					mainActivity.changeFragment(schoolmsgFragment);
					mainActivity.showRight();
				break;
			case R.id.parents_chat: 
				mainActivity.changeFragment(schoolchatFragment);
				mainActivity.showRight();
				break;//待定聊天出现异常
				
			case R.id.teacher_contact:
				mainActivity.changeFragment(teacherContactFrag);
				mainActivity.showRight();
				break;
			case R.id.rount_school:
				Intent intent=new Intent(mainActivity, RoutePlanDemo.class);
				startActivity(intent);
			case R.id.quickly_cut://创建快捷方式
				if(IfaddShortCut()){
				Intent addIntent=new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
	            Parcelable icon=Intent.ShortcutIconResource.fromContext(getActivity(), R.drawable.ic_launcher); //获取快捷键的图标
	            Intent myIntent=new Intent(getActivity(), LoginActivity.class);
	            
	            addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "家校通（家长端）");//快捷方式的标题
	            addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);//快捷方式的图标
	            addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, myIntent);//快捷方式的动作
	            getActivity().sendBroadcast(addIntent);//发送广播
				}
	            break;
		default:break;
		}
			}
			else{
				new AlertDialog.Builder(getActivity()).setTitle("登录信息").setMessage("是否立即登录查看").setIcon(R.drawable.ic_launchernew).setPositiveButton("登录", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						//Intent intent=new Intent();
						Intent intent=new Intent(mainActivity, LoginActivity.class);
						startActivity(intent);
					}
				}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				}).show();
			}
	}
		

	}
	public  boolean  IfaddShortCut(){  
	      boolean isInstallShortcut = false ;   
	      MainActivity mainActivity=(MainActivity) getActivity();
	        final ContentResolver cr = mainActivity.getContentResolver();    
	        //本人的2.2系统是”com.android.launcher2.settings”,网上见其他的为"com.android.launcher.settings"  
	        final String AUTHORITY = "com.android.launcher2.settings";    
	        final Uri CONTENT_URI = Uri.parse("content://" +    
	                         AUTHORITY + "/favorites?notify=true");  
	        Cursor c = cr.query(CONTENT_URI,    
	        new String[] {"title","iconResource" },    
	        "title=?",    
	        new String[] {getString(R.string.app_name ) }, null);//XXX表示应用名称。    
	                if(c!=null && c.getCount()>0){    
	            isInstallShortcut = true ;    
	            System.out.println("已创建");  
	        }    
	        return isInstallShortcut ;    
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
