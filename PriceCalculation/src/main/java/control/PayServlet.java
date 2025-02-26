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
 * 精算するサーブレット
 * @author Sun
 */
@WebServlet("/pay-servlet")
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションオブジェクト取得
		HttpSession session = request.getSession();

		String url = "pay.jsp";		// 転送先の初期化
		
		// 精算処理
		try {
			Operation op = new Operation();
			op.pay(session);
		} catch (Exception e) {
			request.setAttribute("errorMsg", "精算処理でエラーが発生しました。");	
			url = "cart.jsp";		// 転送先の再設定
		} 
		
		// 転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
