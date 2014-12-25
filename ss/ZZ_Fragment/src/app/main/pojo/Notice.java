package app.main.pojo;

import java.util.Date;

/**
 * Notice entity. @author MyEclipse Persistence Tools
 */

public class Notice implements java.io.Serializable {

	// Fields

	private Integer noticeId;
	private String noticeName;
	private String noticeContent;
	private Date noticeTime;
	private String noticeFile;

	// Constructors

	/** default constructor */
	public Notice() {
	}

	/** minimal constructor */
	public Notice(Integer noticeId, String noticeName, String noticeContent,
			Date noticeTime) {
		this.noticeId = noticeId;
		this.noticeName = noticeName;
		this.noticeContent = noticeContent;
		this.noticeTime = noticeTime;
	}

	/** full constructor */
	public Notice(Integer noticeId, String noticeName, String noticeContent,
			Date noticeTime, String noticeFile) {
		this.noticeId = noticeId;
		this.noticeName = noticeName;
		this.noticeContent = noticeContent;
		this.noticeTime = noticeTime;
		this.noticeFile = noticeFile;
	}

	// Property accessors

	public Integer getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeName() {
		return this.noticeName;
	}

	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}

	public String getNoticeContent() {
		return this.noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Date getNoticeTime() {
		return this.noticeTime;
	}

	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}

	public String getNoticeFile() {
		return this.noticeFile;
	}

	public void setNoticeFile(String noticeFile) {
		this.noticeFile = noticeFile;
	}

	@Override
	public String toString() {
		return "{\'noticeContent\':\'" + noticeContent
				+ "\', \'noticeFile\':\'" + noticeFile + "\', \'noticeId\':\'"
				+ noticeId + "\', \'noticeName\':\'" + noticeName
				+ "\', \'noticeTime\':\'" + noticeTime + "\'}";
	}

}