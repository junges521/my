 package app.main.ui;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.main.jxt.R;
import app.main.pojo.ChatEntity;



public class ChatAdapt extends BaseAdapter{
	private Context context = null;
	private List<ChatEntity> chatList = null;
	private LayoutInflater inflater = null;
	private int COME_MSG = 0;
	private int TO_MSG = 1;
	
	
	public ChatAdapt() {
		super();
	}



	public ChatAdapt(Context context,List<ChatEntity> chatList){
		this.context = context;
		
		this.chatList = chatList;
		Collections.reverse(this.chatList);
		//reset(chatList);
		inflater = LayoutInflater.from(this.context);
	}


	public int getCount() {
		return chatList.size();
	}

	
	public Object getItem(int position) {
		return chatList.get(position);
	}


	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public int getItemViewType(int position) {
		// 区别两种view的类型，标注两个不同的变量来分别表示各自的类型
	 	ChatEntity entity = chatList.get(position);
	 	if (entity.isComeMsg())
	 	{
	 		return COME_MSG;
	 	}else{
	 		return TO_MSG;
	 	}
	}

	@Override
	public int getViewTypeCount() {
		// 这个方法默认返回1，如果希望listview的item都是一样的就返回1，我们这里有两种风格，返回2
		return 2;
	}


	public View getView(int position, View convertView, ViewGroup parent) {
		ChatHolder chatHolder = null;
		int i=chatList.size();
		if (convertView == null) {
			chatHolder = new ChatHolder();
			if (chatList.get(position).isComeMsg()) {
				convertView = inflater.inflate(R.layout.chat_from_item, null);
			}else {
				convertView = inflater.inflate(R.layout.chat_to_item, null);
			}
			chatHolder.timeTextView = (TextView) convertView.findViewById(R.id.tv_time);
			chatHolder.contentTextView = (TextView) convertView.findViewById(R.id.tv_content);
			chatHolder.userImageView = (ImageView) convertView.findViewById(R.id.iv_user_image);
			convertView.setTag(chatHolder);
		}else {
			chatHolder = (ChatHolder)convertView.getTag();
		}
		
		chatHolder.timeTextView.setText(chatList.get(position).getChatTime());
		chatHolder.contentTextView.setText(chatList.get(position).getContent());
		chatHolder.userImageView.setImageResource(chatList.get(position).getUserImage());
		
		return convertView;
	}
	
	private class ChatHolder{
		private TextView timeTextView;
		private ImageView userImageView;
		private TextView contentTextView;
	}
	
}
