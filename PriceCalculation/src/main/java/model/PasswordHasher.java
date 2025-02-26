package model;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * パスワードのハッシュ化クラス
 * @author Sun
 */
public class PasswordHasher {

	/**
	 * ハッシュ化
	 * @param password
	 * @return ハッシュ化した結果
	 * @throws Exception
	 */
	public static String hashPassword(String password) throws Exception {
		
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] digest = md.digest();

        return Base64.getEncoder().encodeToString(digest);
    }
	
	/**
	 * ハッシュ値の比較
	 * @param pass 比較対象のパスワード（入力値）
	 * @param hashedPass ハッシュ化されたパスワード（DBからの値）
	 * @return 結果
	 * @throws Exception
	 */
	public static boolean checkPassword(String pass, String hashedPass) throws Exception {
		
		//比較結果を返す
		return hashPassword(pass).equals(hashedPass);

	}

}
