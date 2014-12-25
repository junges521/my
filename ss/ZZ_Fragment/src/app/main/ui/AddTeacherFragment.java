package app.main.ui;

import com.umeng.analytics.MobclickAgent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import app.main.jxt.R;

public class AddTeacherFragment extends Fragment{
	private ImageView lv_left;
	private ImageView iv_right;
	private ImageView teacher_image;
	private TextView teacher_name;
	private TextView teacher_sex;
	private TextView teacher_tel;
	private TextView teacher_address;
	private TextView teacher_time;
	private TextView teacher_position;
	private TextView teacher_self;
	private  TextView tv_send;
	private  TextView tv_cancel;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		this.setHasOptionsMenu(true);
		lv_left.setOnClickListener(new ViewOnclickImpl());
		iv_right.setOnClickListener(new ViewOnclickImpl());
		tv_send.setOnClickListener(new ViewOnclickImpl());
		tv_cancel.setOnClickListener(new ViewOnclickImpl());
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View mView = inflater.inflate(R.layout.teacherdetaill, null);
		
		lv_left = (ImageView) mView.findViewById(R.id.iv_left);
		iv_right = (ImageView) mView.findViewById(R.id.iv_right);
		teacher_image=(ImageView) mView.findViewById(R.id.teacher_image);
		teacher_name=(TextView) mView.findViewById(R.id.teacher_name);
		teacher_sex=(TextView) mView.findViewById(R.id.teacher_sex);
		teacher_address=(TextView) mView.findViewById(R.id.teacher_address);
		teacher_tel=(TextView) mView.findViewById(R.id.teacher_tel);
		teacher_time=(TextView) mView.findViewById(R.id.teacher_time);
		teacher_position=(TextView) mView.findViewById(R.id.teacher_position);
		teacher_self=(TextView) mView.findViewById(R.id.teacher_self);
		tv_cancel=(TextView) mView.findViewById(R.id.contact_cancel);
		tv_send=(TextView) mView.findViewById(R.id.contact_send);
		return mView;
	}
	private class ViewOnclickImpl implements OnClickListener{

		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.iv_left:
				((MainActivity) getActivity()).showLeft();
				break;

			case R.id.iv_right:
				((MainActivity) getActivity()).showRight();
			
				break;
			case R.id.contact_cancel:
			onResume();//返回前一个fragment
				break;
				
			case R.id.contact_send:
				Intent intent=new Intent(getActivity(),ContactAdder.class);
				MainActivity mainActivity=(MainActivity) getActivity();
				mainActivity.startActivity(intent);
				break;
			default:
				break;
			}
		}
		
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
