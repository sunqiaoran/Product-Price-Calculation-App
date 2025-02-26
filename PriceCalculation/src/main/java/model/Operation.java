package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import model.dao.PasswordDAO;
import model.dao.ProductDAO;
import model.dao.SalesDAO;

/**
 * 店内オペレーションクラス
 * @author Sun
 */
public class Operation {
	/**
	 * ログイン時の処理
	 * @param userId リクエストパラメータ
	 * @param password リクエストパラメータ
	 * @param session セッションオブジェクト
	 * @return true .. 正常、false .. ID／パスワード誤り
	 * @throws Exception 
	 */
	public boolean loginProc(String userId, String password, HttpSession session) throws Exception {

		// ログイン認証
		boolean result = authenticate(userId, password);

		if (result) {
			// 店舗データの作成⇒セッションに格納
			Store store = makeStore();
			session.setAttribute("store", store);
			
			// カート情報の作成（userId設定・商品リストは空）⇒セッションに格納
			Cart cart = new Cart(userId, new ArrayList<Product>());
			session.setAttribute("cart", cart);
		}

		return result;
	}

	/**
	 * 認証する
	 * @param userId ユーザID
	 * @param password パスワード
	 * @return 結果 (true / false)
	 */
	private boolean authenticate(String userId, String password)  throws Exception {

		boolean result = false;

		// DBよりパスワード取得
		PasswordDAO passwordDao = new PasswordDAO();
		String dbPass = passwordDao.selectById(userId);

		if (password.equals("") && dbPass.equals("")) {
			// 初期状態 を OK とする
			result = true;
		} else {
			//比較結果の設定
			result = PasswordHasher.checkPassword(password, dbPass);
		}

		return result;
	}
	
	/**
	 * 店舗情報（店舗名＋選択データ（リスト））を作成する
	 * @return 店舗情報
	 * @throws Exception 
	 */
	private Store makeStore() throws Exception {

		ProductDAO productDao = new ProductDAO();
		List<Product> list = productDao.selectAll();
			
		// 店舗情報作成
		Store store = new Store("商品価格精算アプリ", list);
		
		return store;
	}
	
	/**
	 * ログアウト時の処理
	 * @param session
	 */
	public void logoutProc(HttpSession session) {

		session.invalidate();
		
	}

	/**
	 * 商品追加処理
	 * @param idx 商品一覧の選択した商品のidx (セッション：store内)
	 * @param session セッションオブジェクト
	 */
	public void addProd(int idx, HttpSession session) {
		
		// 店舗情報・カート情報の取得（セッションより）
		Store store = (Store) session.getAttribute("store");
		Cart cart = (Cart) session.getAttribute("cart");

		if ((store != null) && (cart != null)) {
			// カートに指定の商品を追加
			cart.add(store.getListProd().get(idx));

			// セッションに再度格納
			session.setAttribute("cart", cart);
		}

	}

	/**
	 * カートから商品削除処理
	 * @param idx カートの中の選択した商品のidx
	 * @param session セッションオブジェクト
	 */
	public void removeProd(int idx, HttpSession session) {
	
		// カート内商品情報の取得（セッションより）
		Cart cart = (Cart) session.getAttribute("cart");
	
		if (cart != null) {
			// カートから指定の商品を削除
			cart.remove(idx);

			// セッションに書き戻す
			session.setAttribute("cart", cart);
		}
			
	}
	
	/**
	 * 精算処理
	 * @param session セッションオブジェクト
	 */
	public void pay(HttpSession session) throws Exception {

		// カート内商品情報の取得
		Cart cart = (Cart) session.getAttribute("cart");

		if (cart != null) {
			
			List<Sales> list = new ArrayList<Sales>();	// 空の商品リスト作成
			
			// java.sql.Date型の現在日を取得
			Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());
			
			// 売上リストの作成
			for (Product product : cart.getListProd()) {
				Sales one = new Sales(cart.getUserId(), product.getId(), 1, currentDate);
				list.add(one);
			}

			// 売上登録
			SalesDAO salesDao = new SalesDAO();
			salesDao.insertAll(list);

			// セッションに格納（精算済みデータ）
			session.setAttribute("pay", cart);

			// カート情報の新規作成⇒セッションに格納
			Cart newCart = new Cart(cart.getUserId(), new ArrayList<Product>());
			session.setAttribute("cart", newCart);
		}

	}
	
	/**
	 * パスワード変更処理
	 * @param userId ユーザID
	 * @param password パスワード（入力値）
	 * @throws Exception
	 */
	public void chgPassword(String userId, String password) throws Exception {

		String hashedPassword = PasswordHasher.hashPassword(password);
		
		Password pw = new Password(userId, hashedPassword);
		
		// パスワード変更
		PasswordDAO passwordDao = new PasswordDAO();
		int cnt = passwordDao.update(pw);
		
		if (cnt < 1) {
			throw new Exception();
		}
		
	}

	/**
	 * 精算履歴を取得する
	 * @param userId 対象のユーザID
	 * @return 精算履歴一覧
	 * @throws Exception
	 */
	public List<Sales> getHistory(String userId) throws Exception {
		
		// DBより対象データ取得
		SalesDAO salesDao = new SalesDAO();
		List<Sales> list = salesDao.selectByUserId(userId);
		
		return list;
	}

}
