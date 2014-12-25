package app.main.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import app.main.jxt.R;

public class Teachercontact extends ListFragment{
	

	ImageView lv_left;
	ImageView iv_right;
	TextView tv_mainpage;
	ImageButton iv_detail;

	/*
	 * (non-Javadoc)
	 * 进行对list每一项进行监听
	 * @see android.support.v4.app.ListFragment#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void onListItemClick(ListView listView, View v, int position, long id) {
		super.onListItemClick(listView, v, position, id);
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), "sdf", Toast.LENGTH_LONG).show();  
		HashMap<String, Object> map=(HashMap<String, Object>) listView.getItemAtPosition(position);
	        String list_title=map.get("list_coursetitle").toString();
	        Bundle b=new Bundle();
	        b.putString("list_coursetitle", list_title);
	        AddTeacherFragment addTeacherContact=new AddTeacherFragment();
			addTeacherContact.setArguments(b);
			MainActivity mainActivity=(MainActivity) getActivity();
			mainActivity.changeFragment(addTeacherContact);
	}

	
@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.child_teacher_main, null);
		lv_left = (ImageView) mView.findViewById(R.id.iv_left);
		iv_right = (ImageView) mView.findViewById(R.id.iv_right);
		tv_mainpage=(TextView) mView.findViewById(R.id.tv_mainpage);
		
		return mView;
	}



	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		tv_mainpage.setText(getResources().getString(R.string.main_page));
		lv_left.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				((MainActivity) getActivity()).showLeft();
			}
		});

		iv_right.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				((MainActivity) getActivity()).showRight();
			}
		});	
		
		
		String from[]={"list_coursetitle","list_teacherImage","list_teachername","list_teacherTel","list_teacherSelf"};
		int to[]={R.id.list_coursetitle,R.id.list_teacherImage,R.id.list_teachername,R.id.list_teacherTel,R.id.list_teacherSelf};
		ListAdapter listTeacher=new SimpleAdapter(getActivity(), getTeacherContack(), R.layout.teachet_item_contact, from, to);

		setListAdapter(listTeacher);
		/*	LayoutInflater flater = LayoutInflater.from(getActivity());
	View view = flater.inflate(R.layout.teachet_item_contact, null);
		iv_detail=(ImageButton) view.findViewById(R.id.iv_detail);
		iv_detail.setOnClickListener(onclickImpl);*/
	}

	@SuppressWarnings("null")
	public List<Map<String,Object>> getTeacherContack(){
		List<Map<String,Object>> teacherList=new ArrayList<Map<String,Object>>();
		Map<String, Object> map=new HashMap<String, Object>();
	
		map.put("list_coursetitle","语文老师");
		map.put("list_teacherImage", R.drawable.mypic);
		map.put("list_teachername", "姓名："+"于果");
		map.put("list_teacherTel", "手机号："+"18607029216");
		map.put("list_teacherSelf", "简介："+"获奖土豪");

		Map<String, Object> map1=new HashMap<String, Object>();
		
		map1.put("list_coursetitle","计算机老师");
		map1.put("list_teacherImage", R.drawable.mypic);
		map1.put("list_teachername", "姓名："+"谭旭杰");
		map1.put("list_teacherTel", "手机号："+"18607029216");
		map1.put("list_teacherSelf", "简介："+"获奖土豪，是九江市优秀教师");

		teacherList.add(map);
		teacherList.add(map1);
		return teacherList;
		
	}
}
