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
		mEditText.setSelection(mEditText.length()); // ������ƶ����һ���ַ�����

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

			// ��ȥ������������������ջ���
			mEditText.removeTextChangedListener(mTextWatcher);

			// ע������ֻ��ÿ�ζ�������EditText�������󳤶ȣ����ܶ�ɾ���ĵ����ַ��󳤶�
			// ��Ϊ����Ӣ�Ļ�ϣ������ַ����ԣ�calculateLength�������᷵��1
			while (calculateLength(s.toString()) > MAX_COUNT) { // �������ַ������������ƵĴ�Сʱ�����нضϲ���
				s.delete(editStart - 1, editEnd);
				editStart--;
				editEnd--;
			}
			mEditText.setText(s);
			mEditText.setSelection(editStart);

			// �ָ�������
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
	 * ����������ݵ�������һ������=����Ӣ����ĸ��һ�����ı��=����Ӣ�ı�� ע�⣺�ú����Ĳ������ڶԵ����ַ����м��㣬��Ϊ�����ַ������������1
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
	 * ˢ��ʣ����������,���ֵ����΢����140���֣���������200����
	 */
	private void setLeftCount() {
		mTextView.setText(String.valueOf((MAX_COUNT - getInputCount())));
	}

	/**
	 * ��ȡ�û�����ķ�����������
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
					Toast.makeText(getActivity(), "��������Ϣ����", Toast.LENGTH_LONG).show();
				break;
				

			case R.id.cancel:// ����˳���ťʱ
			
				
				ChatEntity chatEnity1 = new ChatEntity();
				chatEnity1.setChatTime(nowStr);
				chatEnity1.setComeMsg(true);
				chatEnity1.setContent("me" + " �����ˣ�");
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
				Toast.makeText(getActivity(), "���˳���", Toast.LENGTH_SHORT)
						.show();
				
			}
					

		
	}
	public void onResume() {
	    super.onResume();
	    MobclickAgent.onPageStart("MainScreen"); //ͳ��ҳ��
	}
	public void onPause() {
	    super.onPause();
	    MobclickAgent.onPageEnd("MainScreen"); 
	}


}
	
	
