package app.main.ui;

import java.util.ArrayList;
import java.util.List;

import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import app.main.jxt.R;
import app.main.pojo.Position;

/*
 * 进行查询其他学校的设置完成
 * 添加其他学校或学校周边路线的查询
 */
public class OtherActivity extends Activity
{
	private List<Position> list = new ArrayList<Position>();
	private AutoCompleteTextView rount_target = null;// 路线自动产生

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.targerount);
		rount_target = (AutoCompleteTextView) findViewById(R.id.rountschool);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.target_location));
		rount_target.setAdapter(adapter);
		// Map map=new HashMap<String, Position>();
		list.add(new Position(29.710983, 116.01265));
		list.add(new Position(29.710983, 116.01265));
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		rount_target.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{

			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				String value = parent.getItemAtPosition(position).toString();
				Position pos = list.get(position);
				intent.putExtra("target", value);
				intent.putExtra("pos", pos);
				intent.setAction("app.main.ROUNTEDEMO");
				startActivity(intent);

			}

			public void onNothingSelected(AdapterView<?> parent)
			{
				// TODO Auto-generated method stub

			}
		});
		MobclickAgent.onResume(this);
	}



	public void onPause()
	{
		super.onPause();
		MobclickAgent.onPause(this);
	}

}
