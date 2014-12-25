package app.main.pojo;

public class ChatEntity {

	private int userImage;
	private String content;
	private String chatTime;
	private boolean isComeMsg;
	private String chatParentsName;

	
	
	public ChatEntity() {
		super();
	}
	public ChatEntity(String str) {
		System.out.println(str);
		String []chatStr=str.split(",");
		if(chatStr.length>=3){
		this.setChatParentsName(chatStr[0]);
		this.setChatTime(chatStr[1]);
		this.setContent(chatStr[2]);
		}
		
	}
	public int getUserImage() {
		return userImage;
	}
	public void setUserImage(int userImage) {
		this.userImage = userImage;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getChatTime() {
		return chatTime;
	}
	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}
	public boolean isComeMsg() {
		return isComeMsg;
	}
	public void setComeMsg(boolean isComeMsg) {
		this.isComeMsg = isComeMsg;
	}
	public String getChatParentsName() {
		return chatParentsName;
	}
	public void setChatParentsName(String chatParentsName) {
		this.chatParentsName = chatParentsName;
	}
	@Override
	public String toString() {
		return chatParentsName+","+chatTime+","+content+"\n";
	}
	
}
