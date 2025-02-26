package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.Operation;
import model.Sales;

/**
 * Servlet implementation class GetHistoryServlet
 */
@WebServlet("/get-history-servlet")
public class GetHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 転送先設定
		String url = "history.jsp";
		
		try {
			// ユーザID 取得
			HttpSession session = request.getSession();
			Cart cart = (Cart) session.getAttribute("cart");
			String userId = cart.getUserId();

			// 精算リスト（精算履歴）取得処理
			Operation op = new Operation();
			List<Sales> salesList = op.getHistory(userId);
			if (!salesList.isEmpty()) {
				request.setAttribute("history", salesList);	
			} else {
				request.setAttribute("errorMsg", "精算履歴がありません。");	
			}
		} catch (Exception e) {
			request.setAttribute("errorMsg", "精算履歴取得時にエラーが発生しました。");	
		}
		
		// 転送
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
