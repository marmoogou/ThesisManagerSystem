package mailfunction;
import thesisdata.*;
import studentdata.studentdata;
import teacherdata.*;

public class mailinfo {
	private String Usermail;
	private String username;
	private String thesistitle;
	private String thesisteacher;
	private String qqnumber;
	private String tomail;
	private String telephone;
	private String grade;
	private String major;
	private String brief;
	private String studentid;
	private String teachertitle;
	public void setstudentinfo(studentdata data,thesisdata data1,teacherdata data2) {
		setUsermail(data2.getTeacheremail());
		setUsername(data.getStudentname());
		setThesistitle(data1.getThesistitle());
		setThesisteacher(data1.getThesisteacher());
		setQqnumber(data.getStudentqq());
		setTomail(data.getStudentemail());
		setTelephone(data.getStudenttel());
		setGrade(data.getStudentgrade());
		setMajor(data.getStudentmajor());
		setBrief(data1.getThesisbrief());
		setStudentid(data.getStudentid());
		
		
	}
	public void setteacherinfo(studentdata data,thesisdata data1,teacherdata data2) {
		setUsermail(data.getStudentemail());
		setThesistitle(data1.getThesistitle());
		setThesisteacher(data1.getThesisteacher());
		setQqnumber(data2.getTeacherqq());
		setTomail(data2.getTeacheremail());
		setTelephone(data2.getTeachertel());
		setBrief(data1.getThesisbrief());
		setTeachertitle(data2.getTeachertitle());
		
	}
	public String getUsermail() {
		return Usermail;
	}
	public void setUsermail(String usermail) {
		Usermail = usermail;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getThesistitle() {
		return thesistitle;
	}
	public void setThesistitle(String thesistitle) {
		this.thesistitle = thesistitle;
	}
	public String getThesisteacher() {
		return thesisteacher;
	}
	public void setThesisteacher(String thesisteacher) {
		this.thesisteacher = thesisteacher;
	}
	public String getQqnumber() {
		return qqnumber;
	}
	public void setQqnumber(String qqnumber) {
		this.qqnumber = qqnumber;
	}
	public String getTomail() {
		return tomail;
	}
	public void setTomail(String tomail) {
		this.tomail = tomail;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getTeachertitle() {
		return teachertitle;
	}
	public void setTeachertitle(String teachertitle) {
		this.teachertitle = teachertitle;
	}
	
	
}
