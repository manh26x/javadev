package beans;

public class ChatRoom {
	
	private String idchat;
	private String roomname;
	private String passwordRoom;
	private String nameRoom;
	private byte[] avatar;
	private String avtName;
	
	public ChatRoom() {
		
	}

	public String getIdchat() {
		return idchat;
	}

	public void setIdchat(String idchat) {
		this.idchat = idchat;
	}


	public String getPasswordRoom() {
		return passwordRoom;
	}

	public void setPasswordRoom(String passwordRoom) {
		this.passwordRoom = passwordRoom;
	}

	public String getNameRoom() {
		return nameRoom;
	}

	public void setNameRoom(String nameRoom) {
		this.nameRoom = nameRoom;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public String getAvtName() {
		return avtName;
	}

	public void setAvtName(String avtName) {
		this.avtName = avtName;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	
	
}
