package com.mike.chatapp.beans;

import org.hibernate.annotations.Entity;
import org.springframework.data.annotation.Id;

@Entity
public class UserAccount {
		public static final String genderMale = "M";
		public static final String genderFemale = "F";
		@Id
		private String username;
		private String password;
		private String salt;
		private String firstName;
		private String lastName;
		private String avatarPath;
		private String avatarName;
		private String publicKey;
		private String privateKey;
		private String gender;
		private String email;
		
		public UserAccount() {
			
		}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getAvatarName() {
		return avatarName;
	}

	public void setAvatarName(String avatarName) {
		this.avatarName = avatarName;
	}

	public static String getGendermale() {
		return genderMale;
	}

	public static String getGenderfemale() {
		return genderFemale;
	}
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}
	
}
