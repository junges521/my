package app.main.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;
import android.widget.Toast;
import app.main.jxt.R;

import com.jxt.database.MyDatatabaseHelper;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

/**
 * @author 谢俊良2013-10-27编写LoginActivity.java用于登录的activity
 * 
 */
public class LoginActivity extends Activity
{

	private static final String TAG = LoginActivity.class.getSimpleName();

	private EditText login_et_username;// 编辑框
	private EditText login_et_password;
	private CheckBox login_cb_rempwd;
	private CheckBox login_cb_showpwd;
	private CheckBox login_cb_autologin;
	private RadioButton login_rb_tel;
	private RadioButton login_rb_cer;
	private Button login_btn_submit;// denglu
	private Button login_btn_cancel;
	private RadioGroup login_rg_select;
	private SharedApplication application;
	private SharedPreferences pref;
	public static String PAGE;
	public static String INDEXPAGE;
	private Boolean runFlag;
	private String INFO;
	private ImageView iv_handler;
	private SlidingDrawer slindadraw;
	private String ERROR;
	private TextView AUTHOR;
	private String checkedWhat = "";
	private ImageView image_logo;
	private static final String AUT = "各位家长老师，请文明上网，遵守互联网文明上网协议，为孩子的成长铺砖搭桥。由信息科学技术学院谢俊良同学独立制作，版权归其所有";

	private ProgressDialog t;
	private Boolean isNetwork;
	private Async TASK;
	SQLiteOpenHelper sqlHelper;

	/*
	 * private static final String ERROR_PATTERN = "alert\\('(.+)'\\)"; private
	 * static final String NAME_PATTERN = "<span id=\"lbXM\">(.+)</span>";
	 * private static final String MAJOR_PATTERN =
	 * "<span id=\"lbZYMC\">(.+)</span>"; private static final String
	 * CLASSNUM_PATTERN = "<span id=\"lbBJMC\">(.+)</span>"; private static
	 * final String NUM_PATTERN = "<span id=\"lbXH\">(.+)</span>";
	 */

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		PushAgent mPushAgent = PushAgent.getInstance(this);
		mPushAgent.enable();
		mPushAgent.onAppStart();

		setContentView(R.layout.login_layout);
		sqlHelper = new MyDatatabaseHelper(this);
		image_logo = (ImageView) findViewById(R.id.jxt_logo);
		application = (SharedApplication) this.getApplication();
		pref = getSharedPreferences("LOGIN", MODE_PRIVATE);
		PAGE = SharedApplication.TEL_PAGE;
		INDEXPAGE = SharedApplication.HTTP_TEL_ADDR;
		runFlag = false;
		AUTHOR = (TextView) this.findViewById(R.id.author);
		AUTHOR.setText(AUT);
		iv_handler = (ImageView) findViewById(R.id.iv_handle);
		PAGE = SharedApplication.CER_PAGE;// 服务器url

		this.login_et_username = (EditText) this.findViewById(R.id.login_et_username);
		this.login_et_password = (EditText) this.findViewById(R.id.login_et_password);
		this.login_cb_rempwd = (CheckBox) this.findViewById(R.id.login_cb_rempwd);
		this.login_cb_showpwd = (CheckBox) this.findViewById(R.id.login_cb_showpwd);
		this.login_cb_autologin = (CheckBox) findViewById(R.id.login_cb_autologin);
		this.login_rb_tel = (RadioButton) this.findViewById(R.id.login_rb_tel);
		this.login_rb_cer = (RadioButton) this.findViewById(R.id.login_rb_cer);
		this.login_btn_submit = (Button) this.findViewById(R.id.login_btn_submit);
		this.login_btn_cancel = (Button) this.findViewById(R.id.login_btn_cancel);
		this.login_rg_select = (RadioGroup) this.findViewById(R.id.login_rg_select);
		this.slindadraw = (SlidingDrawer) findViewById(R.id.slingdraw);
		if (this.login_cb_rempwd.isChecked())
		{
			login_cb_autologin.setVisibility(View.VISIBLE);
			login_cb_autologin.setChecked(true);
		}

		if (pref.getBoolean("TEL", true))
		{
			this.login_rb_tel.setChecked(true);
			PAGE = SharedApplication.TEL_PAGE;
			INDEXPAGE = SharedApplication.HTTP_TEL_ADDR;
		} else
		{
			this.login_rb_cer.setChecked(true);
			PAGE = SharedApplication.CER_PAGE;
			INDEXPAGE = SharedApplication.HTTP_CER_ADDR;
		}

		if (pref.getBoolean("PASSWORD", false))
		{
			this.login_cb_rempwd.setChecked(true);
		} else
		{
			this.login_cb_rempwd.setChecked(false);
		}

		if (this.login_cb_rempwd.isChecked() && !pref.getString("user", "").equals(""))
		{
			this.login_et_username.setText(pref.getString("user", ""));
			this.login_et_password.setText(pref.getString("pwd", ""));
		}

		this.login_cb_rempwd.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if (isChecked)
				{
					login_cb_autologin.setVisibility(View.VISIBLE);
					Editor tmp = pref.edit();
					tmp.putBoolean("PASSWORD", true);
					tmp.commit();
				} else
				{
					login_cb_autologin.setVisibility(View.INVISIBLE);
					Editor tmp = pref.edit();
					tmp.putBoolean("PASSWORD", false);
					tmp.putString("user", "");
					tmp.putString("pwd", "");
					tmp.commit();
				}
			}
		});
		this.login_cb_showpwd.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if (isChecked)
				{
					LoginActivity.this.login_et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				} else
				{
					LoginActivity.this.login_et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
				}
			}
		});

		if (this.login_cb_autologin.isChecked())
		{
			TASK = new Async();
			TASK.execute(PAGE);
		}// 自动进行登录

		this.login_rg_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
		{
			public void onCheckedChanged(RadioGroup group, int checkedId)
			{
				if (checkedId == R.id.login_rb_cer)
				{
					Editor tmp = pref.edit();
					tmp.putBoolean("TEL", false);
					tmp.commit();
					LoginActivity.this.checkedWhat = LoginActivity.this.login_rb_cer.getText().toString();

					INDEXPAGE = SharedApplication.HTTP_CER_ADDR;
					Log.d(TAG, "SLELCT CER " + PAGE);
				} else
				{
					Editor tmp = pref.edit();
					tmp.putBoolean("TEL", true);
					tmp.commit();
					LoginActivity.this.checkedWhat = LoginActivity.this.login_rb_tel.getText().toString();
					PAGE = SharedApplication.TEL_PAGE;
					INDEXPAGE = SharedApplication.HTTP_TEL_ADDR;
					Log.d(TAG, "SLELCT TEL " + PAGE);
				}
			}
		});

		this.login_btn_cancel.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				LoginActivity.this.finish();
			}
		});

		this.login_btn_submit.setOnClickListener(new OnSubmitclikImpl());

		Log.i(TAG, "ONCREATE");
		slindadraw.setOnDrawerOpenListener(new OndrawOpenListenImpl());
		slindadraw.setOnDrawerCloseListener(new OndrawCloseListenImpl());
	}

	protected void onResume()
	{
		super.onResume();
		MobclickAgent.onResume(this);
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
		this.image_logo.startAnimation(animation);

	}

	private class OnSubmitclikImpl implements OnClickListener
	{
		public void onClick(View v)
		{
			if (login_et_username.getText().toString().equals("") || login_et_password.getText().toString().equals(""))
			{
				Toast.makeText(LoginActivity.this, "用户名和密码不能为空", Toast.LENGTH_LONG).show();
			} else
			{
				if (login_cb_rempwd.isChecked())
				{
					Editor tmp = pref.edit();
					tmp.putString("user", login_et_username.getText().toString());
					tmp.putString("pwd", login_et_password.getText().toString());
					tmp.commit();

					isNetwork = SharedApplication.networkIsAvailable(LoginActivity.this);
					if (isNetwork)
					{
						if (runFlag == false)
						{
							t = new ProgressDialog(LoginActivity.this);
							t.setProgressStyle(ProgressDialog.STYLE_SPINNER);
							t.setTitle("正在连接");
							t.setMessage("请稍候···");
							t.show();
							TASK = new Async();
							TASK.execute(PAGE);
							System.out.println("sdfs" + PAGE);
							runFlag = true;
						}
					} else
					{
						Toast.makeText(LoginActivity.this, "无网络连接", Toast.LENGTH_LONG).show();
					}
				} else
				{
					// 不为空，不记住密码
					isNetwork = SharedApplication.networkIsAvailable(LoginActivity.this);
					if (isNetwork)
					{
						if (runFlag == false)
						{
							t = new ProgressDialog(LoginActivity.this);
							t.setProgressStyle(ProgressDialog.STYLE_SPINNER);
							t.setTitle("正在连接");
							t.setMessage("请稍候···");
							t.show();
							TASK = new Async();
							TASK.execute(PAGE);
							runFlag = true;
						}
					} else
					{
						Toast.makeText(LoginActivity.this, "无网络连接", Toast.LENGTH_LONG).show();
					}
				}
			}
		}

	}

	private class Async extends AsyncTask<String, Integer, String>
	{
		@Override
		protected String doInBackground(String... params)
		{
			String URL = params[0];
			String result = new String();

			/*
			 * HttpGet httpGet = new HttpGet(URL); try { HttpResponse
			 * getResponse = SharedApplication.client .execute(httpGet); if
			 * (getResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			 * { Log.d("ConnectionOK", "OK"); LoginActivity.this.ERROR =
			 * EntityUtils.toString(getResponse .getEntity()); } else { return
			 * "FAIL"; // } } catch (Exception e) { e.printStackTrace(); return
			 * ""; } finally { httpGet.abort(); }
			 */

			// ///////////////////
			System.out.println(URL);
			HttpPost httpPost = new HttpPost(URL);
			List<NameValuePair> param = new ArrayList<NameValuePair>();

			param.add(new BasicNameValuePair(SharedApplication.USERNAME, login_et_username.getText().toString()));
			param.add(new BasicNameValuePair(SharedApplication.userPwd, login_et_password.getText().toString()));
			param.add(new BasicNameValuePair(SharedApplication.IDENTITY, checkedWhat));

			try
			{
				httpPost.setEntity(new UrlEncodedFormEntity(param, "utf-8"));
				HttpResponse httpResponse = SharedApplication.client.execute(httpPost);
				Log.i("status", httpResponse.getStatusLine().getStatusCode() + "返回值");
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
				{
					result = EntityUtils.toString(httpResponse.getEntity());
					System.out.println("result" + result);
				} else
				{
					return "FAIL";
					//
				}
			} catch (Exception e)
			{
				e.printStackTrace();
				return "";
			} finally
			{
				httpPost.abort();
			}

			/*
			 * HttpGet GetINFO = new HttpGet(LoginActivity.INDEXPAGE +
			 * SharedApplication.INFO_PAGE); try { HttpResponse INFOResponse =
			 * SharedApplication.client .execute(GetINFO); if
			 * (INFOResponse.getStatusLine().getStatusCode() ==
			 * HttpStatus.SC_OK) { Log.d("ConnectionOK", "ok");
			 * LoginActivity.this.INFO = EntityUtils.toString(INFOResponse
			 * .getEntity()); } else { return "FAIL"; // } } catch (Exception e)
			 * { e.printStackTrace(); return ""; } finally { httpGet.abort(); }
			 */

			// ////////////////////
			return result;
		}

		@Override
		protected void onPostExecute(String result)
		{
			// Pattern p = Pattern.compile(ERROR_PATTERN);

			if (result.equals("FAIL"))
			{
				t.dismiss();
				new AlertDialog.Builder(LoginActivity.this).setTitle("错误").setMessage("无法连接服务器").setPositiveButton("确定", new DialogInterface.OnClickListener()
				{

					public void onClick(DialogInterface dialog, int which)
					{
						// TODO Auto-generated method stub
						Intent intent = null;
						// 判断手机系统的版本 即API大于10 就是3.0或以上版本
						if (android.os.Build.VERSION.SDK_INT > 10)
						{
							intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
						} else
						{
							intent = new Intent();
							ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
							intent.setComponent(component);
							intent.setAction("android.intent.action.VIEW");
						}
						LoginActivity.this.startActivity(intent);
					}
				}).setNegativeButton("取消", new DialogInterface.OnClickListener()
				{

					public void onClick(DialogInterface dialog, int which)
					{
					
						Intent intent = new Intent(LoginActivity.this, MainActivity.class);
						LoginActivity.this.startActivity(intent);
						// TODO Auto-generated method stub
						dialog.dismiss();
						LoginActivity.this.finish();
					}
				}).show();

				runFlag = false;

			} else if (result.length() == 0 || result == null)
			{
				t.dismiss();
				new AlertDialog.Builder(LoginActivity.this).setTitle("错误").setMessage("网络连接错误，是否进行网络设置").setPositiveButton("确定", new DialogInterface.OnClickListener()
				{

					public void onClick(DialogInterface dialog, int which)
					{
						// TODO Auto-generated method stub
						Intent intent = null;
						// 判断手机系统的版本 即API大于10 就是3.0或以上版本
						if (android.os.Build.VERSION.SDK_INT > 10)
						{
							intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
						} else
						{
							intent = new Intent();
							ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
							intent.setComponent(component);
							intent.setAction("android.intent.action.VIEW");
						}
						LoginActivity.this.startActivity(intent);
					}
				}).setNegativeButton("取消", new DialogInterface.OnClickListener()
				{

					public void onClick(DialogInterface dialog, int which)
					{
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				}).show();
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				LoginActivity.this.startActivity(intent);
				LoginActivity.this.finish();
				runFlag = false;
			} /*
			 * else if (ERROR.indexOf("系统警告") != -1 || result.indexOf("系统警告") !=
			 * -1 || INFO.indexOf("系统警告") != -1) { t.dismiss();
			 * Toast.makeText(LoginActivity.this, "系统警告，你懂的",
			 * Toast.LENGTH_LONG).show(); runFlag = false; }
			 */else if (result != null)
			{
				t.dismiss();
				Log.i("result", result);
				// JsonUtils jsonUtil=new JsonUtils();
				// SQLiteDatabase
				// db=LoginActivity.this.sqlHelper.getWritableDatabase();
				// MytabOperator mytabOp=new MytabOperator(db);
				// Parents p=jsonUtil.parseJsondata(result);
				// mytabOp.insert(p);
				LoginActivity.this.finish();
				Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
				Log.d("LOGIN", result);

				runFlag = false;

				Log.d("ADDR", INDEXPAGE);
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);

				intent.putExtra("parents", result);
				LoginActivity.this.startActivity(intent);
				LoginActivity.this.finish();

			}
		}

		@Override
		protected void onProgressUpdate(Integer... values)
		{
			super.onProgressUpdate(values);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.menu_about:
			new AlertDialog.Builder(this).setTitle("About").setMessage("VERSION:0.05 BETA\nJust for fun!\nAuthor:Zirconi\nMail:Geekkou@gmail.com").setPositiveButton("OK", null).show();

			break;
		}

		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		menu.add("退出");

		return true;
	}

	private class OndrawOpenListenImpl implements OnDrawerOpenListener
	{

		public void onDrawerOpened()
		{
			// TODO Auto-generated method stub
			iv_handler.setImageResource(android.R.drawable.arrow_up_float);
		}

	}

	private class OndrawCloseListenImpl implements OnDrawerCloseListener
	{

		public void onDrawerClosed()
		{
			// TODO Auto-generated method stub
			iv_handler.setImageResource(android.R.drawable.arrow_down_float);
		}

	}



		public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
		}

}
