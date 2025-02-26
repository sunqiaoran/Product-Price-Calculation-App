package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Operation;

/**
 * ログイン処理を行うサーブレット
 * @author Sun
 */
@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		// ログイン処理＋転送先設定
		String url = "select.jsp";		// 転送先の初期化
		try {
			// ログイン処理
			HttpSession session = request.getSession();	// セッションオブジェクト取得
			Operation op = new Operation();
			boolean result = op.loginProc(userId, password, session);

			if (!result) {					// 認証失敗の場合にはログイン画面に戻す
				request.setAttribute("errorMsg", "ユーザID または パスワードに 誤りがあります。");	
				url = "login.jsp";
			} else if (password.equals("")) {	// パスワード未設定の場合 chg-pass.jsp へ
				request.setAttribute("errorMsg", "パスワードを設定しましょう。");	
				url = "chg-pass.jsp";
			}
			
		} catch (Exception e) {				// 例外発生時にはログイン画面に戻す
			request.setAttribute("errorMsg", "ログイン時にエラーが発生しました。");	
			url = "login.jsp";
		}
		
		// 転送
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
