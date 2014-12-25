/**
 * 
 */
package app.main.ui;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import app.main.jxt.R;
import app.main.pojo.ChatEntity;
import app.main.util.Constant;

/**
 * @author л����2013-10-22��дSchoolChatFragment.java
 * 
 */
public class SchoolChatFragment extends Fragment {

	// private EditText usernameEdit;
	// private EditText ipEdit;
	// private TextView historyEdit;
	private EditText messageEdit;
	private EditText serverIp;
	private EditText serverPort;

	// private Button loginButton;//��½��ť
	private TextView sendButton;// ���Ͱ�ť
	// private Button leaveButton;//�뿪��ť

	// private TextView t_username;
	// private TextView t_ip;

	private ListView historylistView;

	private List<ChatEntity> chatList = null;
	private List<ChatEntity> chatListFenye = null;

	private String username = "who", chat_in;

	private static final String FILENAME = "jxtChat.txt";

	private ChatAdapt chatAdapter = null;

	private Socket socket;
	int nextpage = 0;
	// ÿһҳ���ض�������
	private int number = 20;
	// ����м�ҳ
	private int maxpage = 5;
	// �����ж��Ƿ�������
	private boolean loadfinish = true;

	public DataInputStream in;
	public DataOutputStream out;

	boolean flag = false;
	private View v;
	private File targetFile = null;
	File sdCardDir = Environment.getExternalStorageDirectory();
	private ImageView lv_left;
	private ImageView iv_right;

	private String chatStr;

	private void createFile() {
		targetFile = new File(FILENAME);
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			// ��ȡSD����Ŀ¼
			File sdCardDir = Environment.getExternalStorageDirectory();
			try {
				targetFile = new File(sdCardDir.getCanonicalPath()
						+ File.separator + FILENAME);
				if (!targetFile.getParentFile().exists()) {
					targetFile.getParentFile().mkdirs();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ��ָ���ļ�����
			try {
				if (targetFile.length() <= 0) {
					RandomAccessFile raf = new RandomAccessFile(targetFile,
							"rw");
					// ���ļ���¼ָ���ƶ������
					raf.seek(targetFile.length());
					// ����ļ�����

					raf.write("who,2013-04-15 22:36,��Ϣ��¼6\n".getBytes());
					raf.write("she,2013-04-15 22:34,��Ϣ��¼4\n".getBytes());
					raf.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// RandomAccessFile����,��һ���������ļ����ƣ��ڶ��������Ƕ�дģʽ

		}

	}

	public void readFile() {

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		this.setHasOptionsMenu(true);
		this.createFile();

		FileReader fileReader = null;
		BufferedReader br = null;

		try {
			fileReader = new FileReader(targetFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		br = new BufferedReader(fileReader);
		v = getActivity().getLayoutInflater().inflate(R.layout.progress, null);
		chatList = new ArrayList<ChatEntity>();
		/*
		 * while (scan.hasNext()) { this.historyEdit.append(scan.next() + "\n");
		 * }
		 */

		try {
			while (br.ready()) {
				String history = br.readLine();
				if (history != null && !"".equals(history)) {
					String chatHistory[] = history.split(",");
					ChatEntity chatEnity = new ChatEntity();
					if (chatHistory.length > 2) {
						if ("who".equals(chatHistory[0])) {
							chatEnity.setComeMsg(true);
						} else
							chatEnity.setComeMsg(false);
						chatEnity.setChatTime(chatHistory[1]);
						chatEnity.setContent(chatHistory[2]);
						chatEnity.setChatParentsName(chatHistory[0]);

						chatList.add(chatEnity);
					}
				}
				// historyEdit.append(history+"\n");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			fileReader.close();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MyThread thread = new MyThread();

		thread.start();
		Bundle bundle = this.getArguments();
		try {
			if (bundle != null && bundle.getString("chatEnity") != null) {

				chatStr = bundle.getString("chatEnity");
				ChatEntity chatEnity = new ChatEntity(chatStr);
				chatList.add(chatEnity);

				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					out.writeUTF(chatEnity.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// sendButton=(TextView) getActivity().findViewById(R.id.send);
				// sendButton.setOnClickListener(btmOnclikImpl);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		v = getActivity().getLayoutInflater().inflate(R.layout.progress, null);
		historylistView = (ListView) getActivity().findViewById(
				R.id.listhistoryview);
		historylistView.addFooterView(v);
		messageEdit = (EditText) getActivity().findViewById(R.id.message);
		// �����������ý�����һ���������
		messageEdit.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				MsgEditorFragment msgFragment = new MsgEditorFragment();
				MainActivity mainActivity = (MainActivity) getActivity();

				mainActivity.changeFragment(msgFragment);
				return true;
			}
		});

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
		if (chatList.size() > 20) {
			chatListFenye = chatList.subList(0, number);
		} else
			chatListFenye = chatList.subList(0, chatList.size());

		chatAdapter = new ChatAdapt(getActivity(), chatListFenye);
		historylistView.setAdapter(chatAdapter);
		historylistView.setOnScrollListener(new OnScrollListener() {
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
			}

			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, final int totalItemCount) {

				// �õ�listview���һ���id
				int lastItemId = historylistView.getLastVisiblePosition();
				// �ж��û��Ƿ񻬶������һ���Ϊ����ֵ���㿪ʼ����Ҫ����1
				if ((lastItemId + 1) == totalItemCount) {
					/**
					 * ���㵱ǰҳ����Ϊÿһҳֻ����ʮ�����ݣ������ܹ����ص����ݳ���ÿһҳ�����ݵĸ���
					 * �������Ϊ����ǰҳΪcurrentPage=totalItemCount/number��
					 * �������������ǰҳΪ(int)(totalItemCount/number)+1; ��һҳ���ǵ�ǰҳ��1
					 */
					int currentPage = totalItemCount % number;
					if (currentPage == 0) {
						currentPage = totalItemCount / number;
					} else {
						currentPage = (int) (totalItemCount / number) + 1;
					}
					System.out.println("��ǰҳΪ��" + currentPage);
					nextpage = currentPage + 1;
					// ���ܹ������ݴ���0�ǲż�������
					if (totalItemCount > 0) {
						// �жϵ�ǰҳ�Ƿ񳬹����ҳ���Լ���һҳ�������Ƿ�������
						if (nextpage <= maxpage && loadfinish) {
							// ���ҳ����ͼ
							historylistView.addFooterView(v);

							loadfinish = false;

							AsyncTaskLoadData asynctask = new AsyncTaskLoadData(
									totalItemCount);
							asynctask.execute();
						}
					}

				}
				// �жϼ��ص����ݵ�ҳ����û�г������ҳ�������Ƿ��Ѿ��������

			}
		});

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// TODO Auto-generated method stub

		View view = inflater.inflate(R.layout.chat_main, null);
		lv_left = (ImageView) view.findViewById(R.id.iv_left);
		iv_right = (ImageView) view.findViewById(R.id.iv_right);
		serverIp = (EditText) view.findViewById(R.id.server_ip);
		serverPort = (EditText) view.findViewById(R.id.server_port);
		return view;
	}

	View.OnClickListener btmOnclikImpl = new OnClickListener() {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.send:
				EditText ed_chatContent = (EditText) getActivity()
						.findViewById(R.id.content);

				String chatcontent = ed_chatContent.toString();
				Date now = new Date(System.currentTimeMillis());
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				String nowStr = format.format(now);
				ChatEntity chatEnity = new ChatEntity();
				chatEnity.setChatTime(nowStr);
				chatEnity.setComeMsg(true);
				chatEnity.setContent(chatcontent);
				chatEnity.setChatParentsName(username);
				chatList.add(chatEnity);

				historylistView.setAdapter(chatAdapter);
				break;

			default:
				break;
			}

		}
	};

	class MyThread extends Thread {
		@Override
		public void run() {

			try {
				if (!TextUtils.isEmpty(serverIp.getText())
						&& !TextUtils.isEmpty(serverPort.getText())) {
					socket = new Socket(serverIp.getText().toString(),
							Integer.parseInt(serverPort.getText().toString()));
				} else {
					socket = new Socket(Constant.SERVER_IP, Constant.PORT);
				}

				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());


				while (true) {

					try {
						chat_in = in.readUTF();

						chat_in = chat_in + "\n";

						mHandler.sendMessage(mHandler.obtainMessage());
					} catch (IOException e) {
					}
				}
			} catch (IOException e) {
				System.out.println("can not connect!");
			}

		}

	}

	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			/*
			 * if(msg.what==0) { //֪ͨlistview�е������Ѿ��Ķ�
			 * chatAdapter.notifyDataSetChanged(); loadfinish=true; }
			 * super.handleMessage(msg); //�ж�listview�е�ҳ����ͼ�Ƿ���ڣ����������ɾ��ҳ����ͼ
			 * if(historylistView.getFooterViewsCount()!=0) {
			 * historylistView.removeFooterView(v); }
			 */
			FileOutputStream fos = null;

			try {
				/*
				 * if (Environment.MEDIA_MOUNTED.equals(Environment.
				 * getExternalStorageState())) { fos = new
				 * FileOutputStream(targetFile); } else{
				 * fos=getActivity().openFileOutput(FILENAME,
				 * Activity.MODE_PRIVATE); }
				 */
				fos = Environment.MEDIA_MOUNTED.equals(Environment
						.getExternalStorageState()) ? new FileOutputStream(
						targetFile) : getActivity().openFileOutput(FILENAME,
						Activity.MODE_PRIVATE);
				//
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String chat[] = chat_in.split("\n");
			/*
			 * for (int i = 0; i < chat.length; i++) {
			 * historyEdit.append(chat[i] + "\n\n"); }
			 */
			if (chat.length >= 3) {
				ChatEntity chatEnity = new ChatEntity();
				chatEnity.setComeMsg(true);

				chatEnity.setChatTime(chat[1]);
				chatEnity.setChatParentsName(chat[0]);
				chatEnity.setContent(chat[2]);
				chatList.add(chatEnity);
				historylistView.setAdapter(chatAdapter);
			}

			try {

				Iterator<ChatEntity> iter = chatList.iterator();
				while (iter.hasNext()) {
					ChatEntity chate = iter.next();
					fos.write(chate.toString().getBytes());
				}
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// historyEdit.setGravity(Gravity.CENTER);
			super.handleMessage(msg);
		}

	};

	@SuppressWarnings("unused")
	private final class AsyncTaskLoadData extends
			AsyncTask<Object, Object, Object> {
		private int count;
		private List<ChatEntity> list;

		public AsyncTaskLoadData(int totalItemCount) {
			this.count = totalItemCount;
			list = new ArrayList<ChatEntity>();
			if (chatList.size() > count + number) {
				list = chatList.subList(count, number);
			} else if (chatList.size() > count)
				list = chatList.subList(count, chatList.size());
			else if (chatList.size() > 0) {
				list = chatList.subList(0, chatList.size());
			}
			// ��Handler����Ϣˢ��ListView֮ǰȥ����ListView����Դ
		}

		protected Object doInBackground(Object... params) {

			return null;
		}

		@Override
		protected void onPostExecute(Object result) {
			try {
				chatListFenye = list;

				chatAdapter.notifyDataSetChanged();
				Thread.sleep(2000);
				loadfinish = true;
				if (historylistView.getFooterViewsCount() != 0) {
					historylistView.removeFooterView(v);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.onPostExecute(result);
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
