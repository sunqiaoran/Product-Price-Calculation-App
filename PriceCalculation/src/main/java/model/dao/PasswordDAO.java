package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Password;

/**
 * パスワード情報用のDAO
 * @author Sun
 */
public class PasswordDAO {

	/**
	 * パスワード変更
	 * @param password パスワードオブジェクト
	 * @return 処理件数
	 * @throws Exception
	 */
	public int update(Password password) throws Exception {
		int cnt = 0;

		String sql =   " UPDATE m_password "
					 + " SET    password = ? "
					 + " WHERE  user_id = ? ";
		
		try (Connection con = MyConnection.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(2, password.getUserId());
			pstmt.setString(1, password.getPassword());

			cnt = pstmt.executeUpdate();	// レコード更新処理の実行
		}
		
		return cnt;
	}
	
	/**
	 * ユーザIDよりパスワード取得
	 * @param userId
	 * @return パスワード（文字列）
	 * @throws Exception DB関連のエラー・レコードが存在しない場合
	 */
	public String selectById(String userId) throws Exception {

		String password = null;		// 戻り値の初期化

		String sql =   " SELECT password    " 
					 + " FROM m_password    "
					 + " WHERE user_id = ?  ";

		try (Connection con = MyConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, userId);

			try (ResultSet res = pstmt.executeQuery()) {

				if (res.next()) {
					password = res.getString("password");
				} else {
					throw new Exception();
				}
			}
		}

		return password;
	}
	
}
