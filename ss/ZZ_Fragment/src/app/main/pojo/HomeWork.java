package app.main.pojo;

public class HomeWork {
	private String id;
	private String coursename;
	private String coursecontent;
	private String createDate;
	private String finishtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HomeWork(String id, String coursename, String coursecontent,
			String createDate, String finishtime) {
		super();
		this.id = id;
		this.coursename = coursename;
		this.coursecontent = coursecontent;
		this.createDate = createDate;
		this.finishtime = finishtime;
	}

	public HomeWork() {
		super();
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getCoursecontent() {
		return coursecontent;
	}

	public void setCoursecontent(String coursecontent) {
		this.coursecontent = coursecontent;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}
}
