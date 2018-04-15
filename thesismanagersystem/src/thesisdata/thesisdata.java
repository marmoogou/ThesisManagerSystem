package thesisdata;

import java.io.Serializable;
import java.sql.Date;

public class thesisdata implements Serializable {
	private int thesisid; 
	private String thesistitle;
	private String thesisbrief;
	private String thesisteacher;
	private Date publicdate;
	private String ischosed;
	private String salaryid;
	private String major;
	public String getThesistitle() {
		return thesistitle;
	}
	public void setThesistitle(String thesistitle) {
		this.thesistitle = thesistitle;
	}
	public String getThesisbrief() {
		return thesisbrief;
	}
	public void setThesisbrief(String thesisbrief) {
		this.thesisbrief = thesisbrief;
	}
	public String getThesisteacher() {
		return thesisteacher;
	}
	public void setThesisteacher(String thesisteacher) {
		this.thesisteacher = thesisteacher;
	}
	public Date getPublicdate() {
		return publicdate;
	}
	public void setPublicdate(Date publicdate) {
		this.publicdate = publicdate;
	}
	public String getIschosed() {
		return ischosed;
	}
	public void setIschosed(String ischosed) {
		this.ischosed = ischosed;
	}
	public String getSalaryid() {
		return salaryid;
	}
	public void setSalaryid(String salaryid) {
		this.salaryid = salaryid;
	}
	public int getThesisid() {
		return thesisid;
	}
	public void setThesisid(int thesisid) {
		this.thesisid = thesisid;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}

}
