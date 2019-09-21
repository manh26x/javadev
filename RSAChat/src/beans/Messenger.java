package beans;

import java.util.Date;

public class Messenger {
	
	private String idmess;
	private String username;
	private String idchat;
	private Date time;
	private String data;
	private String filepath;
	
	public Messenger() {
		
	}

	public String getIdmess() {
		return idmess;
	}

	public void setIdmess(String idmess) {
		this.idmess = idmess;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIdchat() {
		return idchat;
	}

	public void setIdchat(String idchat) {
		this.idchat = idchat;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	
}
