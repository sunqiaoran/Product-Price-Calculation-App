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
 * カートから商品を削除するサーブレット
 * @author Sun
 */
@WebServlet("/remove-prod-servlet")
public class RemoveProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// パラメータの取得
		request.setCharacterEncoding("UTF-8");
		int idx = Integer.parseInt(request.getParameter("idx"));

		// セッションオブジェクト取得
		HttpSession session = request.getSession();

		// カートからの商品削除処理
		Operation op = new Operation();
		op.removeProd(idx, session);
		
		// 転送先設定
		String url = "cart.jsp";
		
		// 転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
