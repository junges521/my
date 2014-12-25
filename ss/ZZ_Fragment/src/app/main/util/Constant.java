package app.main.util;

public class Constant {
	public final static String SERVER_IP="192.168.155.1";//ip地址
	public final static String SERVER_PORT=":8080";//端口号
	
	public final static String server_http="http://";//协议
	
	public final static String SERVER_APP="/dwzStruts/dwz/";//项目和包名
	
	public final static String SERVER_ACTION="";//Struts中的action名
	public final static String SERVER_URL=server_http+SERVER_IP+SERVER_PORT+SERVER_APP;
	
	public static final int PORT=8521;//聊天端口号
	
	public static final String t_parents="parents";
	public static final String t_students="students";
	public static final String t_classes="classes";
	public static final String t_teachers="teachers";
	public static final String t_test="test";
	public static final String t_grade="grade";
}
