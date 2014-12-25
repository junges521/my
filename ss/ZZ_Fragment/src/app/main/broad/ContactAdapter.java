package app.main.broad;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.main.jxt.R;
import app.main.pojo.News;
/**�����������ڸ���View*/
public class ContactAdapter extends BaseAdapter {
	private List<News> data;
	private int listviewItem;
	private File cache;
	/**
	 * LayoutInflater������������ findViewById(),��ͬ����LayoutInflater��������layout�ļ����µ�xml�����ļ�������ʵ������
	 * �� findViewById()���Ҿ���ĳһ��xml�µľ��� widget�ؼ�(��:Button,TextView��)��
	 */
	private LayoutInflater layoutInflater;

	public ContactAdapter(Context context, List<News> data,
			int listviewItem, File cache) {
		this.data = data;
		this.listviewItem = listviewItem;
		this.cache = cache;
		this.layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//ȡ��xml�ﶨ���view
		/***
		 * getSystemService()��Android����Ҫ��һ��API������Activity��һ��������
		 * ���ݴ����NAME��ȡ�ö�Ӧ��Object��Ȼ��ת������Ӧ�ķ���������½���ϵͳ��Ӧ�ķ��� 
		 * 	�����Name 					���صĶ��� 					˵�� 
			WINDOW_SERVICE   			WindowManager 			����򿪵Ĵ��ڳ��� 
			LAYOUT_INFLATER_SERVICE 	LayoutInflater 			ȡ��xml�ﶨ���view 
			ACTIVITY_SERVICE 			ActivityManager 		����Ӧ�ó����ϵͳ״̬ 
			POWER_SERVICE 				PowerManger 			��Դ�ķ��� 
			ALARM_SERVICE 				AlarmManager 			���ӵķ��� 
			NOTIFICATION_SERVICE 		NotificationManager 	״̬���ķ��� 
			KEYGUARD_SERVICE 			KeyguardManager 		�������ķ��� 
			LOCATION_SERVICE 			LocationManager 		λ�õķ�����GPS 
			SEARCH_SERVICE 				SearchManager 			�����ķ��� 
			VEBRATOR_SERVICE 			Vebrator 				�ֻ��𶯵ķ��� 
			CONNECTIVITY_SERVICE 		Connectivity 			�������ӵķ��� 
			WIFI_SERVICE 				WifiManager 			Wi-Fi���� 
			TELEPHONY_SERVICE 			TeleponyManager 		�绰���� 

		 */
	}

	/** �õ����ݵ����� */

	public int getCount() {
		return data.size();
	}


	public Object getItem(int position) {
		return data.get(position);
	}


	public long getItemId(int position) {
		return position;
	}


	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = null;
		TextView textView1 = null;
		TextView textView2 = null;
		
		if (convertView == null) {
			convertView = layoutInflater.inflate(listviewItem, null);
			imageView = (ImageView) convertView.findViewById(R.id.list_image);
			textView1 = (TextView) convertView.findViewById(R.id.list_title);
			textView2=(TextView) convertView.findViewById(R.id.list_contect);
			convertView.setTag(new DataWrapper(imageView, textView1,textView2));//�����ݰ�װ�����Ա��Ժ�ʹ��
		} else {
			DataWrapper dataWrapper=(DataWrapper) convertView.getTag();//����װ��ȡ����
			//�Ӱ�װ����ȡ����
			imageView=dataWrapper.getImageView();
			textView1=dataWrapper.getTextView1();
			textView2=dataWrapper.getTextView2();
		}
		News news=data.get(position);
		textView1.setText(news.getNewsName());
		textView2.setText(news.getNewsContent());
		/**�첽����ͼƬ�ļ�*/
		asynchImageLoad(imageView,news.getNewsPhoto());
		return convertView;
	}
	
	/*
	//�÷����ᴴ���ܶ���̣߳�Ҳ��ܺ���Դ
	private void asynchImageLoad(final ImageView imageView, final String imagePath) {
		final Handler handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {//���������߳���
				Uri uri=(Uri) msg.obj;
				if (uri!=null&&imageView!=null) {
					imageView.setImageURI(uri);
				}
			}
		};
		Runnable runnable=new Runnable() {
			@Override
			public void run() {
				try {
					Uri uri=ContactService.getImage(imagePath, cache);
					handler.sendMessage(handler.obtainMessage(10,uri));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		new Thread(runnable).start();
	}
	*/
	
	/**�첽����ͼƬ�ļ�*/
	private void asynchImageLoad(ImageView imageView, String imagePath) {
		AsycImageTask asycImageTask=new AsycImageTask(imageView);
		asycImageTask.execute(imagePath);
	}
	/**
	 * ʹ��AsyncTask�������
	 * ��ѡ������
		1��  onprogressupdate(progress��) ����ʹ�ý����������û�����ȡ��˷��������߳�ִ�У��û���ʾ����ִ�еĽ��ȡ�
		2��  onpreExecute()  �����������û�����excuteʱ�Ľӿڣ�������ִ��֮ǰ��ʼ���ô˷�����������������ʾ���ȶԻ���
		3��  onCancelled()  �û�����ȡ��ʱ��Ҫ���Ĳ�����
		
		 AsyncTask<Params, Progress, Result> 
		 AsyscTask���������ַ�������params,progress��result.
		 1��  params��������ִ�е��������������http�����URL
		 2��  progress��̨����ִ�еİٷֱ�
		 3��  result��ִ̨���������շ��صĽ��������String����������Ҫ�õ���list��
		
			ʹ��AsyncTask�࣬���ص�׼��1��  Task��ʵ��������UI thread�д�����2��  Execute����������UI thread�е���
			3��  ��Ҫ�ֶ��ĵ���onPfreexecute()��onPostExecute(result)Doinbackground(params��),onProgressupdate(progress��)�⼸��������
			4��  ��taskֻ�ܱ�ִ��һ�Σ������ε���ʱ��������쳣;
			AsyncTask���������ù��̶��Ǵ�execute������ʼ�ģ�һ�������߳��е���execute�������Ϳ���ͨ��onpreExecute������
			����һ��Ԥ��������������������￪ʼһ�����ȿ�ͬ��Ҳ����ͨ��onprogressupdate�������û�һ������������ʾ�������û����飻
			���ͨ��onpostexecute�������൱��handler����UI�ķ�ʽ�����������ʹ����doinbackground�õ��Ľ���������UI��
			�˷��������߳�ִ�У�����ִ�еĽ����Ϊ�˷����Ĳ�������
	 */
	private final class AsycImageTask extends AsyncTask<String, Integer, Uri>{
		private ImageView imageView;
		public AsycImageTask(ImageView imageView) {
			this.imageView=imageView;
		}
		/**
		 *  ��ִ̨�У��ȽϺ�ʱ�Ĳ��������Է������
			ע�����ﲻ��ֱ�Ӳ���UI���˷����ں�̨�߳�ִ�У�����������Ҫ����
			��ͨ����Ҫ�ϳ���ʱ�䡣��ִ�й����п��Ե���
			Public progress(progress��)����������Ľ��ȡ�
		 */
		@Override
		protected Uri doInBackground(String... params) {//���߳���ִ��
			try {
				return ContactService.getImage(params[0], cache);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		/**
		 * �൱��handler����UI�ķ�ʽ�����������ʹ����doinbackground�õ��Ľ��
		 * �������UI���˷��������߳�ִ�У�����ִ�еĽ����Ϊ�˷����Ĳ������ء�
		 */
		@Override
		protected void onPostExecute(Uri result) {//���������߳�
			if (result!=null&&imageView!=null) {
				imageView.setImageURI(result);
			}
		}
		
	}
	
	/**���ݰ�װ��*/
	private final class DataWrapper {
		private ImageView imageView;
		private TextView textView1;
		private TextView textView2;

		public ImageView getImageView() {
			return imageView;
		}

		public TextView getTextView1() {
			return textView1;
		}

		public TextView getTextView2() {
			return textView2;
		}

		public DataWrapper(ImageView imageView, TextView textView1,TextView textView2) {
			this.imageView = imageView;
			this.textView1 = textView1;
			this.textView2=textView2;
		}
	}

}
