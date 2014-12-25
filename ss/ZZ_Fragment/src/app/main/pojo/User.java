package app.main.pojo;



/**
 * User entity. @author MyEclipse Persistence Tools
 */

/**
 * @author 谢俊良
 *
 * 家校通项目
 */
public class User  implements java.io.Serializable {


    // Fields    

     private Integer userId;
  
     private String userName;
     private String userPwd;


    // Constructors

    /** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(Integer userId) {
        this.userId = userId;
    }
    
    /** full constructor */
    public User(Integer userId,  String userName, String userPwd) {
        this.userId = userId;

        this.userName = userName;
        this.userPwd = userPwd;
    }

   
    // Property accessors

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }



    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return this.userPwd;
    }
    
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

	@Override
	public String toString() {
		return "{\'userId\':\'" + userId
				+ "\', \'userName\':\'" + userName + "\', \'userPwd\':\'"
				+ userPwd + "\'}";
	}


}