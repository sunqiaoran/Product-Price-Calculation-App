package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Sales;

/**
 * 売上情報用のDAO
 * @author Sun
 */
public class SalesDAO {

	/**
	 * レコード追加（複数件）
	 * @param salesList ... 登録する Salesオブジェクトのリスト
	 * @return 処理件数
	 * @throws Exception
	 */
	public int insertAll(List<Sales> salesList) throws Exception {

		int cnt = 0;	// 処理件数の初期化
		
		String sql =   " INSERT INTO t_sales "
				     + " (user_id, product_id, quantity, sales_date) "
				     + " VALUES (?, ?, ?, ?) ";
		
		try (Connection con = MyConnection.getConnection()) {
			try (PreparedStatement pstmt = con.prepareStatement(sql)) {

				// オートコミットOFF=トランザクション開始
				con.setAutoCommit(false);

				// salesList を 順に登録（複数件）
				for (Sales sales : salesList) {
					// プレースホルダに値を設定
					pstmt.setString(1, sales.getUserId());
					pstmt.setString(2, sales.getProductId());
					pstmt.setInt(3, sales.getQuantity());
					pstmt.setDate(4, sales.getSalesDate());

					cnt += pstmt.executeUpdate();	// レコード追加処理の実行
				}

				// コミット
				con.commit();						// 全て登録時にコミット
				
			} catch (Exception e) {
				System.out.println("ロールバックします");
				con.rollback();						// エラー発生時にロールバック
				throw new Exception();
			}
		}
		
		return cnt;
	}
	
	/**
	 * 売上リストの取得（ユーザIDより）
	 * @param userId 対象のユーザID
	 * @return 売上リスト
	 * @throws Exception
	 */
	public List<Sales> selectByUserId(String userId)  throws Exception {
		// 結果格納用
		List<Sales> salesList = new ArrayList<Sales>();

		String sql = 
		    "  SELECT                                       "
		  + "   ts.sales_id,                                "
		  + "   ts.user_id,                                 "
		  + "   mu.user_name,                               "
		  + "   ts.product_id,                              "
		  + "   mp.product_name,                            "
		  + "   mp.price,                                   "
		  + "   ts.quantity,                                "
		  + "   ts.sales_date                               "
		  + " FROM                                          "
		  + "   t_sales ts                                  "
		  + "   INNER JOIN m_product mp                     "
		  + "           ON ts.product_id = mp.product_id    "
		  + "   LEFT OUTER JOIN m_user mu                   "
		  + "                ON ts.user_id = mu.user_id     "
		  + " WHERE                                         "
		  + "   ts.user_id = ?                              "
		  + " ORDER BY                                      "
		  + "   ts.sales_date DESC,                         "
		  + "   ts.sales_id ASC                             ";

		try (Connection con = MyConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, userId);

			try (ResultSet res = pstmt.executeQuery()) {
				while (res.next()) {
					int salesId        = res.getInt("sales_id");
					String userName    = res.getString("user_name");
					String productId   = res.getString("product_id");
					String productName = res.getString("product_name");
					int price          = res.getInt("price");
					int quantity       = res.getInt("quantity");
					Date salesDate     = res.getDate("sales_date");
					
					// １件分のオブジェクトを生成してリストに追加
					Sales sales
						= new Sales(salesId, userId, userName, productId, productName,
									price, quantity, salesDate);
					salesList.add(sales);
				}
			}
		}

		return salesList;
	}

}
