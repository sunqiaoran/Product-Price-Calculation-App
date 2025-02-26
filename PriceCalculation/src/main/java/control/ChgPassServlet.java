package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.Operation;

/**
 * パスワード変更を行うサーブレット
 * @author Sun
 */
@WebServlet("/chg-pass-servlet")
public class ChgPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "select.jsp";	// 転送先の初期化
		
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String password = request.getParameter("password");
		String again = request.getParameter("again");

		if (password.equals(again)) {

			try {
				Cart cart = (Cart) request.getSession().getAttribute("cart");
				Operation op = new Operation();
				op.chgPassword(cart.getUserId(), password);
			} catch (Exception e) {
				request.setAttribute("errorMsg", "パスワード変更時エラーが発生しました。");	
				url = "chg-pass.jsp";
			}
			
		} else {
			request.setAttribute("errorMsg", "入力値が一致しません。");	
			url = "chg-pass.jsp";
		}

		// 転送
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
