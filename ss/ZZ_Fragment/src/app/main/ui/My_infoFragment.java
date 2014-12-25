package app.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import app.main.jxt.R;
import app.main.util.JsonUtils;

public class My_infoFragment extends Fragment {
	ImageView lv_left;
	ImageView iv_right;
	TextView tv_mainpage;
	TextView p_loginname;//登录名
	TextView p_name;//真实名
	TextView p_sex;//性别
	TextView p_relation;//关系
	TextView p_job;//工作
	TextView p_address;//地址
	TextView p_tel;//电话


	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.my_info, null);
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
		tv_mainpage.setText("家长信息");
		iv_right.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				((MainActivity) getActivity()).showRight();
			}
		});	
	}


	
	
	
	
	/*
	 * 实现AsyncTask中定义的下面一个或几个方法 
　　   onPreExecute(), 该方法将在执行实际的后台操作前被UI thread调用。可以在该方法中做一些准备工作，如在界面上显示一个进度条。 
　　  doInBackground(Params...), 将在onPreExecute 方法执行后马上执行，该方法运行在后台线程中。这里将主要负责执行那些很耗时的后台计算工作。可以调用 publishProgress方法来更新实时的任务进度。该方法是抽象方法，子类必须实现。 
　　  onProgressUpdate(Progress...),在publishProgress方法被调用后，UI thread将调用这个方法从而在界面上展示任务的进展情况，例如通过一个进度条进行展示。 
　　  onPostExecute(Result), 在doInBackground 执行完成后，onPostExecute 方法将被UI thread调用，后台的计算结果将通过该方法传递到UI thread. 

	 */
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		menu.add(Menu.NONE,Menu.FIRST+1,1,R.string.callback).setIcon(android.R.drawable.ic_menu_revert);
		menu.add(Menu.NONE,Menu.FIRST+2,2,R.string.edit).setIcon(android.R.drawable.ic_menu_edit);
		menu.add(Menu.NONE,Menu.FIRST+3,3,R.string.updatePwd).setIcon(android.R.drawable.ic_menu_manage);
	
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		 switch (item.getItemId()) {
		   case Menu.FIRST+1:
			   Toast.makeText(getActivity(), "返回", Toast.LENGTH_LONG).show();
			   
		      break;
		   case Menu.FIRST+2:
			   Toast.makeText(getActivity(), "编辑", Toast.LENGTH_LONG).show();
			   
			      break;
		   case Menu.FIRST+3:
			   Toast.makeText(getActivity(), "修改密码", Toast.LENGTH_LONG).show();
			   
			      break;
		   }
		   return false;
		

	}




	
}
