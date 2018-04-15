package optthesisdata;

import java.io.Serializable;
import java.sql.Date;

public class optthesisdata implements Serializable {
	private int thesisid;
	private String studentid;
	private String thesistopic;
	private String thesisteacher;
	private String ischecked;
	private Date chosedtime;
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getThesistopic() {
		return thesistopic;
	}
	public void setThesistopic(String thesistopic) {
		this.thesistopic = thesistopic;
	}
	public String getThesisteacher() {
		return thesisteacher;
	}
	public void setThesisteacher(String thesisteacher) {
		this.thesisteacher = thesisteacher;
	}
	public String getIschecked() {
		return ischecked;
	}
	public void setIschecked(String ischecked) {
		this.ischecked = ischecked;
	}
	public Date getChosedtime() {
		return chosedtime;
	}
	public void setChosedtime(Date chosedtime) {
		this.chosedtime = chosedtime;
	}
	public int getThesisid() {
		return thesisid;
	}
	public void setThesisid(int thesisid) {
		this.thesisid = thesisid;
	}

	
}
