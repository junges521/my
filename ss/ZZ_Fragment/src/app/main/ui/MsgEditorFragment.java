package app.main.ui;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.umeng.analytics.MobclickAgent;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import app.main.jxt.R;
import app.main.pojo.ChatEntity;

public class MsgEditorFragment extends Fragment {

	private EditText mEditText = null;
	private TextView mTextView = null;
	
	private TextView tv_cancel =null;
	private TextView tv_send = null;

	private ImageView lv_left;
	private ImageView iv_right;

	private static final int MAX_COUNT = 100;
	
	public interface chatFragmentcallback{
		public void leftCallback(int position);
	}
	private chatFragmentcallback chatcallback;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mEditText = (EditText) findViewById(R.id.content);
		
		mEditText.addTextChangedListener(mTextWatcher);
		mEditText.setSelection(mEditText.length()); // 将光标移动最后一个字符后面

		mTextView = (TextView) findViewById(R.id.count);
		setLeftCount();
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
		tv_cancel.setOnClickListener(new OnclikListenImpl());
		
		tv_send.setOnClickListener(new OnclikListenImpl());
		
	}

	private View findViewById(int count) {
		// TODO Auto-generated method stub
		return getActivity().findViewById(count);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.msg_main, null);
		lv_left = (ImageView) view.findViewById(R.id.iv_left);
		iv_right = (ImageView) view.findViewById(R.id.iv_right);
		tv_cancel=(TextView) view.findViewById(R.id.cancel);
		tv_send=(TextView) view.findViewById(R.id.send);
		return view;

	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
	
		if(newConfig.keyboardHidden==newConfig.KEYBOARDHIDDEN_NO){
			newConfig.keyboardHidden=newConfig.KEYBOARDHIDDEN_YES;
		}
		super.onConfigurationChanged(newConfig);
	}

	private TextWatcher mTextWatcher = new TextWatcher() {

		private int editStart;

		private int editEnd;

		public void afterTextChanged(Editable s) {
			editStart = mEditText.getSelectionStart();
			editEnd = mEditText.getSelectionEnd();

			// 先去掉监听器，否则会出现栈溢出
			mEditText.removeTextChangedListener(mTextWatcher);

			// 注意这里只能每次都对整个EditText的内容求长度，不能对删除的单个字符求长度
			// 因为是中英文混合，单个字符而言，calculateLength函数都会返回1
			while (calculateLength(s.toString()) > MAX_COUNT) { // 当输入字符个数超过限制的大小时，进行截断操作
				s.delete(editStart - 1, editEnd);
				editStart--;
				editEnd--;
			}
			mEditText.setText(s);
			mEditText.setSelection(editStart);

			// 恢复监听器
			mEditText.addTextChangedListener(mTextWatcher);

			setLeftCount();
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

		}

	};

	/**
	 * 计算分享内容的字数，一个汉字=两个英文字母，一个中文标点=两个英文标点 注意：该函数的不适用于对单个字符进行计算，因为单个字符四舍五入后都是1
	 * 
	 * @param c
	 * @return
	 */
	private long calculateLength(CharSequence c) {
		double len = 0;
		for (int i = 0; i < c.length(); i++) {
			int tmp = (int) c.charAt(i);
			if (tmp > 0 && tmp < 127) {
				len += 0.5;
			} else {
				len++;
			}
		}
		return Math.round(len);
	}

	/**
	 * 刷新剩余输入字数,最大值新浪微博是140个字，人人网是200个字
	 */
	private void setLeftCount() {
		mTextView.setText(String.valueOf((MAX_COUNT - getInputCount())));
	}

	/**
	 * 获取用户输入的分享内容字数
	 * 
	 * @return
	 */
	private long getInputCount() {
		return calculateLength(mEditText.getText().toString());
	}
	
	private class OnclikListenImpl implements View.OnClickListener{

	

		@SuppressLint("SimpleDateFormat")
		public void onClick(View v) {
			SchoolChatFragment schatFragment=new SchoolChatFragment();
			MainActivity mainActivity=(MainActivity) getActivity();
			Bundle bundle=new Bundle();
			// TODO Auto-generated method stub
			Date now = new Date(System.currentTimeMillis());
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			String nowStr = format.format(now);
	
			switch (v.getId()) {
			case R.id.send:
				//chatcallback.leftCallback(R.id.send);
				if(mEditText.toString()!=null){
		
				ChatEntity chatEnity=new ChatEntity();
				chatEnity.setChatTime(nowStr);
				chatEnity.setComeMsg(true);
				chatEnity.setContent(mEditText.getText().toString());
				chatEnity.setChatParentsName("me");
				bundle.putString("chatEnity", chatEnity.toString());
				schatFragment.setArguments(bundle);
				mainActivity.changeFragment(schatFragment);
				}
				else
					Toast.makeText(getActivity(), "请输入消息内容", Toast.LENGTH_LONG).show();
				break;
				

			case R.id.cancel:// 点击退出按钮时
			
				
				ChatEntity chatEnity1 = new ChatEntity();
				chatEnity1.setChatTime(nowStr);
				chatEnity1.setComeMsg(true);
				chatEnity1.setContent("me" + " 下线了！");
				chatEnity1.setChatParentsName("me");
/*		chatList.add(chatEnity);
				out.writeUTF(chatEnity.toString());
				out.close();
				in.close();
				socket.close();*/
		
				bundle.putString("chatEnity", chatEnity1.toString());
				schatFragment.setArguments(bundle);
				mainActivity.changeFragment(schatFragment);
				}
				//flag = false;
				Toast.makeText(getActivity(), "已退出！", Toast.LENGTH_SHORT)
						.show();
				
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
	
	
