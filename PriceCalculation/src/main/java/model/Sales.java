package model;

import java.sql.Date;

/**
 * 売上情報を表すクラス
 * @author Sun
 */
public class Sales {
	/******** フィールド ******************************************/
	/**
	 * 売上ID
	 */
	private int salesId;
	
	/**
	 * ユーザID
	 */
	private String userId;
	
	/**
	 * ユーザ名（ユーザマスタ）
	 */
	private String userName;
	
	/**
	 * 商品ID
	 */
	private String productId;
	
	/**
	 * 商品名（商品マスタ）
	 */
	private String productName;
	
	/**
	 * 単価（商品マスタ）
	 */
	private int price;
	
	/**
	 * 数量
	 */
	private int quantity;
	
	/**
	 * 売上日
	 */
	private Date salesDate;
	
	/******** コンストラクタ **************************************/
	/**
	 * 引数なしコンストラクタ
	 */
	public Sales() {
	}

	/**
	 * コンストラクタ（フィールド初期化）
	 * @param userId ユーザID
	 * @param productId 商品ID
	 * @param quantity 数量
	 * @param salesDate 売上日
	 */
	public Sales(String userId, String productId,
			int quantity, Date salesDate) {
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.salesDate = salesDate;
	}

	/**
	 * コンストラクタ（フィールド初期化）
	 * @param salesId 売上ID
	 * @param userId ユーザID
	 * @param userName ユーザ名
	 * @param productId 商品ID
	 * @param productName 商品名
	 * @param price 単価
	 * @param quantity 数量
	 * @param salesDate 売上日
	 */
	public Sales(int salesId, String userId, String userName, 
			String productId, String productName, int price,
			int quantity, Date salesDate) {
		this.salesId = salesId;
		this.userId = userId;
		this.userName = userName;
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.salesDate = salesDate;
	}

	/******** メソッド ******************************************/
	/*--------------------getter/setter--------------------*/
	/**
	 * @return salesId 売上ID
	 */
	public int getSalesId() {
		return salesId;
	}

	/**
	 * @param salesId セットする salesId
	 */
	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}

	/**
	 * @return userId ユーザID
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
	 * @return userName ユーザ名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName セットする userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return productId 商品ID
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId セットする productId
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return productName 商品名
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName セットする productName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return price 単価
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price セットする price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return quantity 数量
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity セットする quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return salesDate 売上日
	 */
	public Date getSalesDate() {
		return salesDate;
	}

	/**
	 * @param salesDate セットする salesDate
	 */
	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}

	/*--------------------通常メソッド--------------------*/
	/**
	 * 価格を文字列で返す（３桁カンマ区切り＋"円"）
	 * @return 価格 + 円
	 */
	public String getPriceString() {
		return String.format("%,d", price) + "円";
	}

}
