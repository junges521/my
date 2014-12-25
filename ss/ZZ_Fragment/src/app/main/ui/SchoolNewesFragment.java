package app.main.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.umeng.analytics.MobclickAgent;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import app.main.broad.RefreshLayout;
import app.main.broad.RefreshLayout.onRefreshListener;
import app.main.jxt.R;

/**
 * @author 谢俊良2013-11-4编写SchoolNewesFragment.java
 * 新闻异步刷新效果还未完全实现
 *
 */
public class SchoolNewesFragment extends ListFragment {
	ImageView lv_left;
	ImageView iv_right;
	TextView tv_mainpage;
	RefreshLayout refreshableView;

	/*
	Handler handler = new Handler() {
		// 接受数据
		MainActivity mainActivity=(MainActivity) getActivity();
		@SuppressWarnings("unchecked")
		public void handleMessage(android.os.Message msg) {
			//设置适配器，将获取的数据使用适配器更新View
			SchoolNewesFragment.this.setListAdapter(new ContactAdapter(getActivity(),
					(List<News>) msg.obj, R.layout.textlist, mainActivity.getCache()));
		};
	};*/

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.list, null);
		lv_left = (ImageView) mView.findViewById(R.id.iv_left);
		iv_right = (ImageView) mView.findViewById(R.id.iv_right);
		tv_mainpage=(TextView) mView.findViewById(R.id.tv_mainpage);
		return mView;
	}

	public List<Map<String, Object>> getListNews(){
		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("list_title", getString(R.string.title1));
		item1.put("list_image", R.drawable.p1);
		item1.put("list_contect", getString(R.string.test));
		Map<String, Object> item2 = new HashMap<String, Object>();
		item2.put("list_title", getString(R.string.title2));
		item2.put("list_image", R.drawable.p2);
		item2.put("list_contect", getString(R.string.test));
		Map<String, Object> item3 = new HashMap<String, Object>();
		item3.put("list_title", getString(R.string.title3));
		item3.put("list_image", R.drawable.p3);
		item3.put("list_contect", getString(R.string.test));
		Map<String, Object> item4 = new HashMap<String, Object>();
		item4.put("list_title", getString(R.string.title4));
		item4.put("list_image", R.drawable.p4);
		item4.put("list_contect", getString(R.string.test));
		Map<String, Object> item5 = new HashMap<String, Object>();
		item5.put("list_title", getString(R.string.title5));
		item5.put("list_image", R.drawable.p5);
		item5.put("list_contect", getString(R.string.test));
		Map<String, Object> item6 = new HashMap<String, Object>();
		item6.put("list_title", getString(R.string.title5));
		item6.put("list_image", R.drawable.p6);
		item6.put("list_contect", getString(R.string.test));
		Map<String, Object> item7 = new HashMap<String, Object>();
		item7.put("list_title", getString(R.string.title1));
		item7.put("list_image", R.drawable.p7);
		item7.put("list_contect", getString(R.string.test));
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		data.add(item1);
		data.add(item2);
		data.add(item3);
		data.add(item4);
		data.add(item5);
		data.add(item6);
		data.add(item1);
		return data;
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.setHasOptionsMenu(true);
		refreshableView = (app.main.broad.RefreshLayout)getActivity(). findViewById(R.id.refreshable_view);
		
		String[] from = new String[] { "list_title", "list_image",
				"list_contect" };
		int[] to = new int[] { R.id.list_title, R.id.list_image,
				R.id.list_contect };
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), getListNews(),
				R.layout.textlist, from, to);
		setListAdapter(adapter);
		
		refreshableView.setOnRefreshListener(new onRefreshListener() {
			public void onRefresh() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				refreshableView.finishRefreshing();
			}
		}, 0);

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
		/*
		 * 进行新闻的加载
		 */
/*		new Thread(new Runnable() {
			public void run() {
				try {
					//获取联系人数据
					List<Map<String, Object>> data= ContactService.getContacts();
					// 向Handler发送消息,更新UI
					handler.sendMessage(handler.obtainMessage(8, data));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();*/
	}

	@Override
	public void onListItemClick(ListView listView, View v, int position, long id) {
		super.onListItemClick(listView, v, position, id);
	/*	Log.d("-----", position + "");
		Intent intent = new Intent(getActivity(), NewsFragment.class);
		startActivity(intent);*/
        HashMap<String, Object> map=(HashMap<String, Object>) listView.getItemAtPosition(position);
        String list_title=map.get("list_title").toString();
        Bundle b=new Bundle();
        b.putString("list_title", list_title);
		NewsFragment newsFragment=NewsFragment.newInstance(b);
		MainActivity mainActivity=(MainActivity) getActivity();
		mainActivity.changeFragment(newsFragment);
	}
	/*
	 * 实现AsyncTask中定义的下面一个或几个方法 
　　   onPreExecute(), 该方法将在执行实际的后台操作前被UI thread调用。可以在该方法中做一些准备工作，如在界面上显示一个进度条。 
　　  doInBackground(Params...), 将在onPreExecute 方法执行后马上执行，该方法运行在后台线程中。这里将主要负责执行那些很耗时的后台计算工作。可以调用 publishProgress方法来更新实时的任务进度。该方法是抽象方法，子类必须实现。 
　　  onProgressUpdate(Progress...),在publishProgress方法被调用后，UI thread将调用这个方法从而在界面上展示任务的进展情况，例如通过一个进度条进行展示。 
　　  onPostExecute(Result), 在doInBackground 执行完成后，onPostExecute 方法将被UI thread调用，后台的计算结果将通过该方法传递到UI thread. 

	 */

	
/*	private class AsyncCenter extends AsyncTask<String, Integer, String> {

		
		ProgressDialog pdialog;
		
		 * 为了正确的使用AsyncTask类，以下是几条必须遵守的准则： 
　　1) Task的实例必须在UI thread中创建 
　　2) execute方法必须在UI thread中调用 
　　3) 不要手动的调用onPreExecute(), onPostExecute(Result)，doInBackground(Params...), onProgressUpdate(Progress...)这几个方法 
　　4) 该task只能被执行一次，否则多次调用时将会出现异常 
      doInBackground方法和onPostExecute的参数必须对应，这两个参数在AsyncTask声明的泛型参数列表中指定，第一个为doInBackground接受的参数，第二个为显示进度的参数，第第三个为doInBackground返回和onPostExecute传入的参数。
		 * 
		 
		
		
		public AsyncCenter(Context context) {
			pdialog = new ProgressDialog(context, 0);   
            pdialog.setButton("取消", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int i) {
              dialog.cancel();
             }
            });
            pdialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
             public void onCancel(DialogInterface dialog) {
              dialog.dismiss();
             }
            });
            pdialog.setCancelable(true);
            pdialog.setMax(100);
            pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pdialog.show();
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stu
			super.onPostExecute(result);
			
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			try{

				HttpClient client = new DefaultHttpClient();
				// params[0]代表连接的url
				// HttpGet get = new HttpGet(params[0]);
				HttpPost httpPost = new HttpPost(params[0]);
				List<NameValuePair> param = new ArrayList<NameValuePair>();

				param.add(new BasicNameValuePair("op", "mobile"));
				httpPost.setEntity(new UrlEncodedFormEntity(param, "utf-8"));
				HttpResponse response = client.execute(httpPost);
				HttpEntity entity = response.getEntity();
				long length = entity.getContentLength();
				InputStream is = entity.getContent();
				String s = null;
				if (is != null) {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();

					byte[] buf = new byte[128];

					int ch = -1;

					int count = 0;

					while ((ch = is.read(buf)) != -1) {

						baos.write(buf, 0, ch);

						count += ch;

						if (length > 0) {
							// 如果知道响应的长度，调用publishProgress（）更新进度
							publishProgress((int) ((count / (float) length) * 100));
						}

						// 让线程休眠100ms
						Thread.sleep(100);
					}
					s = new String(baos.toByteArray());
					System.out.println(s);
				}
				// 返回结果
				return s;
			} catch (Exception e) {
				e.printStackTrace();

			}

			return null;
		}
	}*/
	
	public void onResume() {
	    super.onResume();
	    MobclickAgent.onPageStart("MainScreen"); //统计页面
	}
	public void onPause() {
	    super.onPause();
	    MobclickAgent.onPageEnd("MainScreen"); 
	}

}
