package administratordata;

import java.io.Serializable;

public class administratordata implements Serializable {
	private String administratorid;
	private String administratorpassword;
	private String start;
	public String getAdministratorid() {
		return administratorid;
	}
	public void setAdministratorid(String administratorid) {
		this.administratorid = administratorid;
	}
	public String getAdministratorpassword() {
		return administratorpassword;
	}
	public void setAdministratorpassword(String administratorpassword) {
		this.administratorpassword = administratorpassword;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	

}
