/**
 * 
 */
package app.main.ui;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.umeng.analytics.MobclickAgent;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import app.main.jxt.R;

/**
 * @author 谢俊良2013-10-22编写SchoolMsgFragment.java
 *
 */
public class SchoolMsgFragment extends ListFragment{
	ImageView lv_left;
	ImageView iv_right;
	TextView tv_mainpage;
	private final int notifyId=0x123;
	private ArrayList<Map<String, Object>> data;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.list, null);
		lv_left = (ImageView) mView.findViewById(R.id.iv_left);
		iv_right = (ImageView) mView.findViewById(R.id.iv_right);
		tv_mainpage=(TextView) mView.findViewById(R.id.tv_mainpage);
		return mView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);//设置在该fragment中拥有菜单
		data=(ArrayList<Map<String, Object>>) getData();
		
		MsgAdapter adapter=new MsgAdapter(getActivity());
		setListAdapter(adapter);
	
		
		tv_mainpage.setText(getResources().getString(R.string.school_msg));

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
		
	}
	/*
	 * 通过onCreateOptionsMenu()，fragment可以为activity的Options Menu提供菜单项。为了确保这一方法成功实现回调。必须在onCreate()期间调用setHasOptionsMenu()告知Options Menu fragment要添加菜单项。
通过fragment添加的菜单会添加在已有菜单之后。选中菜单项时，fragment也会接收onOptionsItemSelected()回调。
通过registerForContextMenu()注册，onCreateContextMenu()接收用户打开信息，onContextItemSelected()接收用户点击信息可以实现Context Menu。
用户点击菜单的信息首先传递给activity，如果activity不处理，则传递给fragment。
	 * (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateOptionsMenu(android.view.Menu, android.view.MenuInflater)
	 */
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		 menu.add("教务通知");
         menu.add("年级通知");
         menu.add(R.string.test3);
    
	}
	private ArrayList<Map<String, Object>>  getData(){
		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("list_title", "学校运动会举办通知");
		item1.put("list_image", R.drawable.p1);
		item1.put("list_contect", "九江市第一中学第十二届春季运动会举办通知");
		Map<String, Object> item2 = new HashMap<String, Object>();
		item2.put("list_title", getString(R.string.title1));
		item2.put("list_image", R.drawable.p2);
		item2.put("list_contect", getString(R.string.my_info_test));
		Map<String, Object> item3 = new HashMap<String, Object>();
		item3.put("list_title", getString(R.string.title1));
		item3.put("list_image", R.drawable.p3);
		item3.put("list_contect", getString(R.string.my_info_test));
		
		data = new ArrayList<Map<String, Object>>();
		
		data.add(item1);
		data.add(item2);
		data.add(item3);
		return data;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
		   case 0:

		       return true;
		   case 1:
		       
		       return true;
		   case 2:
		      
		       return true;
		   }
		   return false;
	}


	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Log.d("----->", position + "");

	
	}
	/*
	 * 实现AsyncTask中定义的下面一个或几个方法 
　　   onPreExecute(), 该方法将在执行实际的后台操作前被UI thread调用。可以在该方法中做一些准备工作，如在界面上显示一个进度条。 
　　  doInBackground(Params...), 将在onPreExecute 方法执行后马上执行，该方法运行在后台线程中。这里将主要负责执行那些很耗时的后台计算工作。可以调用 publishProgress方法来更新实时的任务进度。该方法是抽象方法，子类必须实现。 
　　  onProgressUpdate(Progress...),在publishProgress方法被调用后，UI thread将调用这个方法从而在界面上展示任务的进展情况，例如通过一个进度条进行展示。 
　　  onPostExecute(Result), 在doInBackground 执行完成后，onPostExecute 方法将被UI thread调用，后台的计算结果将通过该方法传递到UI thread. 

	 */
	
	private class AsyncCenter extends AsyncTask<String, Integer, String> {

		
		ProgressDialog pdialog;
		/*
		 * 为了正确的使用AsyncTask类，以下是几条必须遵守的准则： 
　　1) Task的实例必须在UI thread中创建 
　　2) execute方法必须在UI thread中调用 
　　3) 不要手动的调用onPreExecute(), onPostExecute(Result)，doInBackground(Params...), onProgressUpdate(Progress...)这几个方法 
　　4) 该task只能被执行一次，否则多次调用时将会出现异常 
      doInBackground方法和onPostExecute的参数必须对应，这两个参数在AsyncTask声明的泛型参数列表中指定，第一个为doInBackground接受的参数，第二个为显示进度的参数，第第三个为doInBackground返回和onPostExecute传入的参数。
		 * 
		 */
		
		
		public AsyncCenter(Context context) {
			pdialog = new ProgressDialog(context, 0);   
            pdialog.setButton("cancel", new DialogInterface.OnClickListener() {
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
			// TODO Auto-generated method stub
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
	               HttpGet get = new HttpGet(params[0]);
	               HttpResponse response = client.execute(get);
	               HttpEntity entity = response.getEntity();
	               long length = entity.getContentLength();
	               InputStream is = entity.getContent();
	               String s = null;
	               if(is != null) {
	                   ByteArrayOutputStream baos = new ByteArrayOutputStream();

	                   byte[] buf = new byte[128];

	                   int ch = -1;

	                   int count = 0;

	                   while((ch = is.read(buf)) != -1) {

	                      baos.write(buf, 0, ch);

	                      count += ch;

	                      if(length > 0) {
	                          // 如果知道响应的长度，调用publishProgress（）更新进度
	                          publishProgress((int) ((count / (float) length) * 100));
	                      }

	                      // 让线程休眠100ms
	                      Thread.sleep(100);
	                   }
	                   s = new String(baos.toByteArray());              }
	               // 返回结果
	               return s;
	            } catch(Exception e) {
	               e.printStackTrace();

	            }

	            return null;

		}
	}
	private class MsgAdapter extends BaseAdapter{
		private LayoutInflater inflater;
 public MsgAdapter(Context context){
	this.inflater=LayoutInflater.from(context);
}
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			convertView=inflater.inflate(R.layout.school_msg, null);
			 TextView title = (TextView) convertView.findViewById(R.id.schoolmsg_title);//找某个控件  
	            title.setText(data.get(position).get("list_title").toString());//给该控件设置数据(数据从集合类中来)  
	            TextView time = (TextView) convertView.findViewById(R.id.school_msg_content);//找某个控件  
	            time.setText(data.get(position).get("list_contect").toString());//给该控件设置数据(数据从集合类中来)  
	            ImageView info = (ImageView) convertView.findViewById(R.id.school_msg_category);  
	            info.setImageResource((Integer) data.get(position).get("list_image"));
	            ImageView iv=(ImageView) convertView.findViewById(R.id.iv_detail);
	            iv.setBackgroundColor(R.drawable.lib_mini_book);
	            final LinearLayout ll=(LinearLayout) convertView.findViewById(R.id.ll_msg_detail);
	            iv.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(ll.getVisibility()==View.GONE){
							ll.setVisibility(View.VISIBLE);
						}
						else 
							ll.setVisibility(View.GONE);
					
					}
				});
			return convertView;
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
