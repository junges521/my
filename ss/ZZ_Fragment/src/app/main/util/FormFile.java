package app.main.util;

import java.io.Serializable;

public class FormFile implements Serializable{
	/* �ϴ��ļ������� */
	private byte[] data;
	/* �ļ����� */
	private String filname;
    /*��չ��*/
	private String fileExtendName;
	/* ���ֶ����� */
	private String formname;
	/* �������� */
	private String contentType = "application/octet-stream"; // ��Ҫ������ص�����

	public FormFile(String filname, byte[] data, String formname,
			String contentType) {
		this.data = data;
		this.filname = filname;
		this.formname = formname;
		if (contentType != null)
			this.contentType = contentType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getFilname() {
		return filname;
	}

	public void setFilname(String filname) {
		this.filname = filname;
	}

	public String getFormname() {
		return formname;
	}

	public void setFormname(String formname) {
		this.formname = formname;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileExtendName() {
		return fileExtendName;
	}

	public void setFileExtendName(String fileExtendName) {
		this.fileExtendName = fileExtendName;
	}

}
