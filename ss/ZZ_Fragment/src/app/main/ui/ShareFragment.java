package app.main.ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import app.main.jxt.R;

import com.bshare.BShare;
import com.bshare.core.BSShareItem;
import com.bshare.core.Config;
import com.bshare.core.DefaultHandler;
import com.bshare.core.PlatformType;
import com.bshare.core.ShareResult;
import com.umeng.analytics.MobclickAgent;

public class ShareFragment extends Fragment {
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				Toast.makeText(getActivity(), "分享成功", Toast.LENGTH_SHORT)
						.show();
			} else if (msg.what == 2) {
				Toast.makeText(getActivity(), "分享失败",
						Toast.LENGTH_SHORT).show();
			} else if (msg.what == 3) {
				Toast.makeText(getActivity(), "正在连接微博分享",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getActivity(), "Verification Error",
						Toast.LENGTH_SHORT);
			}
		};
	};
	private EditText etSummary;
	private EditText etUrl;
	private CheckBox cbPostImage;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		System.setProperty("debug", "true");
		Config.configObject().setAutoCloseShareList(true);
		Config.configObject().setShouldTrackBackClick(true);
		Config.configObject().setPublisherUUID("dfdfkjdfkjdkfj");
		// Config.configObject().setSohu//在者進行搜狐微博的key和secret的設置
		List<PlatformType> platforms = new ArrayList<PlatformType>(Config
				.configObject().getShareList());
		Config.configObject().setShareList(platforms);
		final BShare bshare = BShare.instance(getActivity());

		bshare.addCredential(getActivity(), PlatformType.QQMB, "abc", "123",
				null);
		List<PlatformType> bindedAccounts = bshare
				.getBindedAccount(getActivity());
		System.out.println(bindedAccounts);



		View lv_left=findViewById(R.id.iv_left);
		View lv_right=findViewById(R.id.iv_right);
		lv_left.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				((MainActivity) getActivity()).showLeft();
			}
		});

		lv_right.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				((MainActivity) getActivity()).showRight();
			}
		});
		// showEditor = (Button) findViewById(R.id.btn_show_editor);
		cbPostImage = (CheckBox) findViewById(R.id.cb_post_img);
		etSummary = (EditText) findViewById(R.id.et_summary);
		etUrl = (EditText) findViewById(R.id.et_url);
		Button showShareList = (Button) findViewById(R.id.button1);
		showShareList.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				BSShareItem shareItem = new BSShareItem(
						PlatformType.SOHUMINIBLOG, "標題", etSummary.getText()
								.toString(), etUrl.getText().toString());
				if (cbPostImage.isChecked()) {
					shareItem.setImg(getTestImg());
				}
				bshare.showShareList(getActivity(), shareItem, new DemoHandler(
						handler));
			}
		});
	}

	private View findViewById(int cbPostImg) {
		// TODO Auto-generated method stub
		return getActivity().findViewById(cbPostImg);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View mView = inflater.inflate(R.layout.share_main, null);
		return mView;
	}

	private byte[] getTestImg() {
		AssetManager am = getActivity().getApplication().getAssets();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			InputStream is = am.open("test.jpg");
			byte[] buf = new byte[1024];
			while (is.read(buf) > -1) {
				baos.write(buf, 0, buf.length);
			}
			return baos.toByteArray();
		} catch (IOException e) {
			return null;
		}
	}

	private class DemoHandler extends DefaultHandler {

		private Handler handler;

		public DemoHandler(Handler handler) {
			this.handler = handler;
		}

		@Override
		public void onShareComplete(PlatformType p, ShareResult sr) {
			System.out.println(p.getPlatfromName());
			Message msg = handler.obtainMessage(sr.isSuccess() ? 1 : 2, sr);
			handler.sendMessage(msg);
		}

		@Override
		public void onShareStart(PlatformType p, BSShareItem shareItem) {
			handler.sendEmptyMessage(3);
		}

		@Override
		public void onVerifyError(PlatformType p) {
			handler.sendEmptyMessage(4);
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
