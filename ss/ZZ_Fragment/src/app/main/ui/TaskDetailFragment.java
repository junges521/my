package app.main.ui;

import com.umeng.analytics.MobclickAgent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import app.main.jxt.R;

public class TaskDetailFragment extends Fragment{
	ImageView lv_left;
	ImageView iv_right;
	TextView tv_mainpage;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.task_detail, null);
		lv_left = (ImageView) mView.findViewById(R.id.iv_left);
		iv_right = (ImageView) mView.findViewById(R.id.iv_right);
		tv_mainpage=(TextView) mView.findViewById(R.id.tv_mainpage);
		return mView;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		lv_left.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				((MainActivity) getActivity()).showLeft();
			}
		});
		tv_mainpage.setText("作业详情信息");
		iv_right.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				((MainActivity) getActivity()).showRight();
			}
		});	
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
