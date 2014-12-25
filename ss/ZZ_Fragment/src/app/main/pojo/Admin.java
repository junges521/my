package app.main.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin implements java.io.Serializable {

	// Fields

	private Integer adminId;
	private String adminName;
	private String adminPwd;
	private String adminTel;
	private Set newses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** minimal constructor */
	public Admin(Integer adminId) {
		this.adminId = adminId;
	}

	/** full constructor */
	public Admin(Integer adminId, String adminName, String adminPwd,
			String adminTel, Set newses) {
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPwd = adminPwd;
		this.adminTel = adminTel;
		this.newses = newses;
	}

	// Property accessors

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPwd() {
		return this.adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public String getAdminTel() {
		return this.adminTel;
	}

	public void setAdminTel(String adminTel) {
		this.adminTel = adminTel;
	}

	public Set getNewses() {
		return this.newses;
	}

	public void setNewses(Set newses) {
		this.newses = newses;
	}

	@Override
	public String toString() {
		return "{\'adminId\':\'" + adminId + "\', \'adminName\':\'" + adminName
				+ "\', \'adminPwd\':\'" + adminPwd + "\', \'adminTel\':\'"
				+ adminTel + "\'}";
	}

}