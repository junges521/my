package app.main.ui;

import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.FeedbackAgent;
import com.umeng.update.UmengUpdateAgent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import app.main.jxt.R;

public class LeftFragment extends Fragment {

	private TextView user;// �û�
	private TextView collect;// ����
	private TextView exit;// �˳�
	private TextView help;// ����
	private TextView about;// ����
	private TextView feedBack;// ����
	private My_infoFragment myInfoFragment;// ������Ƭ
	private MainActivity mainActivity = null;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.left, null);

		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		user = (TextView) getActivity().findViewById(R.id.mian_user);
		collect = (TextView) getActivity().findViewById(R.id.main_collect);
		exit = (TextView) getActivity().findViewById(R.id.main_standard);
		help = (TextView) getActivity().findViewById(R.id.main_help);
		about = (TextView) getActivity().findViewById(R.id.main_about);
		feedBack = (TextView) getActivity().findViewById(R.id.main_feedback);

		myInfoFragment = new My_infoFragment();// ��ʼ��
		mainActivity = (MainActivity) getActivity();

		user.setOnClickListener(new OnclikListenImpl());
		collect.setOnClickListener(new OnclikListenImpl());
		exit.setOnClickListener(new OnclikListenImpl());
		help.setOnClickListener(new OnclikListenImpl());
		about.setOnClickListener(new OnclikListenImpl());
		feedBack.setOnClickListener(new OnclikListenImpl());
	}

	private class OnclikListenImpl implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {

			case R.id.mian_user:

				mainActivity.changeLeftFragment(myInfoFragment);
				mainActivity.showLeft();
				break;
			case R.id.main_about:
				LayoutInflater inflater = LayoutInflater.from(getActivity());
				View view = inflater.inflate(R.layout.dialog_about, null);
				Dialog adialog = new AlertDialog.Builder(getActivity())
						.setIcon(R.drawable.ic_launcher)
						.setTitle("����Ӧ��")
						.setPositiveButton("ȷ��",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
									}
								}).setView(view).create();
				adialog.show();

				break;
			case R.id.main_standard:
				Dialog edialog = new AlertDialog.Builder(getActivity())
						.setIcon(R.drawable.ic_launcher)
						.setTitle("�˳�")
						.setMessage("ȷ���˳�Ӧ��? ")
						.setPositiveButton("ȷ��",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										/*
										 * //���·����������ͷ�SDKռ�õ�ϵͳ��Դ
										 * AppConnect.getInstance
										 * (this).finalize();
										 */
										System.exit(0);
									}
								})
						.setNegativeButton("ȡ��",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// ȡ�� ����
									}
								}).create();
				edialog.show();
			case R.id.main_collect:
				ShareFragment sFragment = new ShareFragment();
				mainActivity.changeLeftFragment(sFragment);
				mainActivity.showLeft();
				break;
			case R.id.main_feedback:
				FeedbackAgent agent = new FeedbackAgent(getActivity());
				agent.sync();
				agent.startFeedbackActivity();
				break;
			case R.id.main_help:
				UmengUpdateAgent.forceUpdate(getActivity());
				break;
			default:
				break;
			}
		}

	}

	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("MainScreen"); // ͳ��ҳ��
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("MainScreen");
	}

}
