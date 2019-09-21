package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.UserAccount;

public class DBUtils {
	
	public static UserAccount findUser(Connection conn, String username, String password) throws SQLException {
		String sql = "Select a.username, a.password from users a " //
				+ "where a.username = ? and a.password = ?";
		PreparedStatement ptsm = conn.prepareStatement(sql);
		ptsm.setString(1, username);
		ptsm.setString(2, password);
		ResultSet rs = ptsm.executeQuery();
		while(rs.next()) {
			UserAccount user = new UserAccount();
			user.setUsername(username);
			user.setPassword(password);
			user.setSalt(rs.getString("salt"));
			user.setFirstName(rs.getString("firsname"));
			user.setLastName(rs.getString("lastname"));
			user.setGender(rs.getString("gender"));
			user.setAvatarName(rs.getString("avatarname"));
			user.setAvatarPath(rs.getString("avatar"));
			user.setEmail(rs.getString("email"));
			user.setPublicKey(rs.getString("publickey"));
			user.setPrivateKey(rs.getString("privatekey"));
			return user;
		}
		return null;
		
	}
}
