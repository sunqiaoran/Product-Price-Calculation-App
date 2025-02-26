package model;

/**
 * パスワードを表すクラス
 * @author Sun
 */
public class Password {
	/******** フィールド ******************************************/
	/**
	 * ユーザID
	 */
	private String userId;

	/**
	 * パスワード
	 */
	private String password;
	
	/******** コンストラクタ **************************************/
	/**
	 * 引数なしコンストラクタ
	 */
	public Password() {
	}

	/**
	 * フィールド初期化コンストラクタ
	 * @param userId
	 * @param password
	 */
	public Password(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	/******** メソッド ******************************************/
	/*--------------------getter/setter--------------------*/
	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
