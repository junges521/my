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
 * @author 谢俊良2013-10-22编写SchoolChatFragment.java
 * 
 */
public class SchoolChatFragment extends Fragment {

	// private EditText usernameEdit;
	// private EditText ipEdit;
	// private TextView historyEdit;
	private EditText messageEdit;
	private EditText serverIp;
	private EditText serverPort;

	// private Button loginButton;//登陆按钮
	private TextView sendButton;// 发送按钮
	// private Button leaveButton;//离开按钮

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
	// 每一页记载多少数据
	private int number = 20;
	// 最多有几页
	private int maxpage = 5;
	// 用来判断是否加载完成
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
			// 获取SD卡的目录
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
			// 以指定文件创建
			try {
				if (targetFile.length() <= 0) {
					RandomAccessFile raf = new RandomAccessFile(targetFile,
							"rw");
					// 将文件记录指针移动到最后
					raf.seek(targetFile.length());
					// 输出文件内容

					raf.write("who,2013-04-15 22:36,消息记录6\n".getBytes());
					raf.write("she,2013-04-15 22:34,消息记录4\n".getBytes());
					raf.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// RandomAccessFile对象,第一个参数是文件名称，第二个参数是读写模式

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
		// 进行输入设置进入下一个输入界面
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

				// 得到listview最后一项的id
				int lastItemId = historylistView.getLastVisiblePosition();
				// 判断用户是否滑动到最后一项，因为索引值从零开始所以要加上1
				if ((lastItemId + 1) == totalItemCount) {
					/**
					 * 计算当前页，因为每一页只加载十条数据，所以总共加载的数据除以每一页的数据的个数
					 * 如果余数为零则当前页为currentPage=totalItemCount/number；
					 * 如果不能整除则当前页为(int)(totalItemCount/number)+1; 下一页则是当前页加1
					 */
					int currentPage = totalItemCount % number;
					if (currentPage == 0) {
						currentPage = totalItemCount / number;
					} else {
						currentPage = (int) (totalItemCount / number) + 1;
					}
					System.out.println("当前页为：" + currentPage);
					nextpage = currentPage + 1;
					// 当总共的数据大于0是才加载数据
					if (totalItemCount > 0) {
						// 判断当前页是否超过最大页，以及上一页的数据是否加载完成
						if (nextpage <= maxpage && loadfinish) {
							// 添加页脚视图
							historylistView.addFooterView(v);

							loadfinish = false;

							AsyncTaskLoadData asynctask = new AsyncTaskLoadData(
									totalItemCount);
							asynctask.execute();
						}
					}

				}
				// 判断加载的数据的页数有没有超过最大页，并且是否已经记载完成

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
			 * if(msg.what==0) { //通知listview中的数据已经改动
			 * chatAdapter.notifyDataSetChanged(); loadfinish=true; }
			 * super.handleMessage(msg); //判断listview中的页脚视图是否存在，如果存在在删除页脚视图
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
			// 在Handler发消息刷新ListView之前去重置ListView数据源
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
	    MobclickAgent.onPageStart("MainScreen"); //统计页面
	}
	public void onPause() {
	    super.onPause();
	    MobclickAgent.onPageEnd("MainScreen"); 
	}

}
