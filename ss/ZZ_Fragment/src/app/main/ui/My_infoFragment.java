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
	TextView p_loginname;//��¼��
	TextView p_name;//��ʵ��
	TextView p_sex;//�Ա�
	TextView p_relation;//��ϵ
	TextView p_job;//����
	TextView p_address;//��ַ
	TextView p_tel;//�绰


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
		tv_mainpage.setText("�ҳ���Ϣ");
		iv_right.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				((MainActivity) getActivity()).showRight();
			}
		});	
	}


	
	
	
	
	/*
	 * ʵ��AsyncTask�ж��������һ���򼸸����� 
����   onPreExecute(), �÷�������ִ��ʵ�ʵĺ�̨����ǰ��UI thread���á������ڸ÷�������һЩ׼�����������ڽ�������ʾһ���������� 
����  doInBackground(Params...), ����onPreExecute ����ִ�к�����ִ�У��÷��������ں�̨�߳��С����ｫ��Ҫ����ִ����Щ�ܺ�ʱ�ĺ�̨���㹤�������Ե��� publishProgress����������ʵʱ��������ȡ��÷����ǳ��󷽷����������ʵ�֡� 
����  onProgressUpdate(Progress...),��publishProgress���������ú�UI thread��������������Ӷ��ڽ�����չʾ����Ľ�չ���������ͨ��һ������������չʾ�� 
����  onPostExecute(Result), ��doInBackground ִ����ɺ�onPostExecute ��������UI thread���ã���̨�ļ�������ͨ���÷������ݵ�UI thread. 

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
			   Toast.makeText(getActivity(), "����", Toast.LENGTH_LONG).show();
			   
		      break;
		   case Menu.FIRST+2:
			   Toast.makeText(getActivity(), "�༭", Toast.LENGTH_LONG).show();
			   
			      break;
		   case Menu.FIRST+3:
			   Toast.makeText(getActivity(), "�޸�����", Toast.LENGTH_LONG).show();
			   
			      break;
		   }
		   return false;
		

	}




	
}
