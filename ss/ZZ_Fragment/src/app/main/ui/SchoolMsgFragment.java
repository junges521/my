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
 * @author л����2013-10-22��дSchoolMsgFragment.java
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
		setHasOptionsMenu(true);//�����ڸ�fragment��ӵ�в˵�
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
	 * ͨ��onCreateOptionsMenu()��fragment����Ϊactivity��Options Menu�ṩ�˵��Ϊ��ȷ����һ�����ɹ�ʵ�ֻص���������onCreate()�ڼ����setHasOptionsMenu()��֪Options Menu fragmentҪ��Ӳ˵��
ͨ��fragment��ӵĲ˵�����������в˵�֮��ѡ�в˵���ʱ��fragmentҲ�����onOptionsItemSelected()�ص���
ͨ��registerForContextMenu()ע�ᣬonCreateContextMenu()�����û�����Ϣ��onContextItemSelected()�����û������Ϣ����ʵ��Context Menu��
�û�����˵�����Ϣ���ȴ��ݸ�activity�����activity�������򴫵ݸ�fragment��
	 * (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateOptionsMenu(android.view.Menu, android.view.MenuInflater)
	 */
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		 menu.add("����֪ͨ");
         menu.add("�꼶֪ͨ");
         menu.add(R.string.test3);
    
	}
	private ArrayList<Map<String, Object>>  getData(){
		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("list_title", "ѧУ�˶���ٰ�֪ͨ");
		item1.put("list_image", R.drawable.p1);
		item1.put("list_contect", "�Ž��е�һ��ѧ��ʮ���촺���˶���ٰ�֪ͨ");
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
	 * ʵ��AsyncTask�ж��������һ���򼸸����� 
����   onPreExecute(), �÷�������ִ��ʵ�ʵĺ�̨����ǰ��UI thread���á������ڸ÷�������һЩ׼�����������ڽ�������ʾһ���������� 
����  doInBackground(Params...), ����onPreExecute ����ִ�к�����ִ�У��÷��������ں�̨�߳��С����ｫ��Ҫ����ִ����Щ�ܺ�ʱ�ĺ�̨���㹤�������Ե��� publishProgress����������ʵʱ��������ȡ��÷����ǳ��󷽷����������ʵ�֡� 
����  onProgressUpdate(Progress...),��publishProgress���������ú�UI thread��������������Ӷ��ڽ�����չʾ����Ľ�չ���������ͨ��һ������������չʾ�� 
����  onPostExecute(Result), ��doInBackground ִ����ɺ�onPostExecute ��������UI thread���ã���̨�ļ�������ͨ���÷������ݵ�UI thread. 

	 */
	
	private class AsyncCenter extends AsyncTask<String, Integer, String> {

		
		ProgressDialog pdialog;
		/*
		 * Ϊ����ȷ��ʹ��AsyncTask�࣬�����Ǽ����������ص�׼�� 
����1) Task��ʵ��������UI thread�д��� 
����2) execute����������UI thread�е��� 
����3) ��Ҫ�ֶ��ĵ���onPreExecute(), onPostExecute(Result)��doInBackground(Params...), onProgressUpdate(Progress...)�⼸������ 
����4) ��taskֻ�ܱ�ִ��һ�Σ������ε���ʱ��������쳣 
      doInBackground������onPostExecute�Ĳ��������Ӧ��������������AsyncTask�����ķ��Ͳ����б���ָ������һ��ΪdoInBackground���ܵĲ������ڶ���Ϊ��ʾ���ȵĲ������ڵ�����ΪdoInBackground���غ�onPostExecute����Ĳ�����
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
	               // params[0]�������ӵ�url
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
	                          // ���֪����Ӧ�ĳ��ȣ�����publishProgress�������½���
	                          publishProgress((int) ((count / (float) length) * 100));
	                      }

	                      // ���߳�����100ms
	                      Thread.sleep(100);
	                   }
	                   s = new String(baos.toByteArray());              }
	               // ���ؽ��
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
			 TextView title = (TextView) convertView.findViewById(R.id.schoolmsg_title);//��ĳ���ؼ�  
	            title.setText(data.get(position).get("list_title").toString());//���ÿؼ���������(���ݴӼ���������)  
	            TextView time = (TextView) convertView.findViewById(R.id.school_msg_content);//��ĳ���ؼ�  
	            time.setText(data.get(position).get("list_contect").toString());//���ÿؼ���������(���ݴӼ���������)  
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
	    MobclickAgent.onPageStart("MainScreen"); //ͳ��ҳ��
	}
	public void onPause() {
	    super.onPause();
	    MobclickAgent.onPageEnd("MainScreen"); 
	}

}
