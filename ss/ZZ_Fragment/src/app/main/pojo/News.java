package app.main.pojo;

import java.util.Date;



/**
 * News entity. @author MyEclipse Persistence Tools
 */

public class News implements java.io.Serializable {

	// Fields

	private Integer newsId;
	private Admin admin;
	private Category category;
	private String newsName;
	private String newsContent;
	private Date newsTime;
	private String newsPhoto;

	// Constructors

	/** default constructor */
	public News() {
	}

	/** minimal constructor */
	public News(Integer newsId) {
		this.newsId = newsId;
	}

	/** full constructor */
	public News(Integer newsId, Admin admin, Category category,
			String newsName, String newsContent, Date newsTime, String newsPhoto) {
		this.newsId = newsId;
		this.admin = admin;
		this.category = category;
		this.newsName = newsName;
		this.newsContent = newsContent;
		this.newsTime = newsTime;
		this.newsPhoto = newsPhoto;
	}

	// Property accessors

	public Integer getNewsId() {
		return this.newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getNewsName() {
		return this.newsName;
	}

	public void setNewsName(String newsName) {
		this.newsName = newsName;
	}

	public String getNewsContent() {
		return this.newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public Date getNewsTime() {
		return this.newsTime;
	}

	public void setNewsTime(Date newsTime) {
		this.newsTime = newsTime;
	}

	public String getNewsPhoto() {
		return this.newsPhoto;
	}

	public void setNewsPhoto(String newsPhoto) {
		this.newsPhoto = newsPhoto;
	}

	@Override
	public String toString() {
		return "{\'admin\':" + admin + ", \'category\':" + category
				+ ", \'newsContent\':\'" + newsContent + "\', \'newsId\':\'"
				+ newsId + "\', \'newsName\':\'" + newsName
				+ "\', \'newsPhoto\':\'" + newsPhoto + "\', \'newsTime\':\'"
				+ newsTime + "\'}";
	}

}