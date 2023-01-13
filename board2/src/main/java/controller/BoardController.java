package controller;

import java.io.IOException;


import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import DAO.BoardDAO;
import DTO.Board;

@WebServlet("/")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardDAO dao;
	private ServletContext ctx;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new BoardDAO();
		ctx = getServletContext();
	}

//    public BoardController() {
//        super();
//    
//    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String context = request.getContextPath();
		String command = request.getServletPath();
		String site = null;

		// 경로 라우팅
		switch (command) {
		case "/list":
			site = getList(request);
			break;
		case "/view":
			site = getView(request);
			break;
		case "/insert":
			site = insertBoard(request);
			break;
		case "/write":
			site = "write.jsp";
			break;
		case "/edit":
			site = getViewForEdit(request);
			break;
		case "/update":
			site = updateBoard(request);
			break;
		case "/delete":
			site = deleteBoard(request);
			break;
		}
/*
 * 둘 다 페이지를 이동한다
		 redirect 
		 > URL의 변화가 있다,객체재사용불가능(리퀘스트리스폰스)
		 DB에 변화가 생기는 요청에 사용(글쓰기나 회원가입) insert, update, delete
		 
		 
		 forward 
		 > URL의 변화가 없다 (보안),객체재사용가능 
		 단순한 조회 (리스트보기와 검색) SELECT
*/		
		if (site.startsWith("redirect:/")) {
			String rview = site.substring("redirect:/".length());
			response.sendRedirect(rview);
		} else {
			getServletContext().getRequestDispatcher("/" + site).forward(request, response);
			
		}
	}

	public String getList(HttpServletRequest request) {
		List<Board> list;
		try {
			list = dao.getList();
			request.setAttribute("boardList", list);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시판 목록 생성 과정에서 문제 발생");
			request.setAttribute("error", "게시판 목록이 정상적으로 처리되지 않았습니다!");
		}
		return "index.jsp";
		
	}

	public String getView(HttpServletRequest request) {
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		try {
			dao.updateView(board_no); //	조회수증가 
  			Board b = dao.getView(board_no);
			request.setAttribute("board", b);
			System.out.print(b);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시판 목록 생성 과정에서 문제 발생");
			request.setAttribute("error", "게시판 목록이 정상적으로 처리되지 않았습니다!");
		}
		return "view.jsp";
	}
	
	public String insertBoard(HttpServletRequest request) {
		Board b = new Board();
		try {
	System.out.print("insert");
			BeanUtils.populate(b, request.getParameterMap());
			dao.insertBoard(b);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("추가과정에서 문제발생");
			request.setAttribute("error", "게시글을 정상적으로 등록하지 못했습니");
			return getList(request);
		}
		
		System.out.print("return");
		return "redirect:/list";
	}
	
	public String getViewForEdit(HttpServletRequest request) {
        int board_no = Integer.parseInt(request.getParameter("board_no"));
        try {
			Board b = dao.getViewForEdit(board_no);			
			request.setAttribute("board", b);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시글을 가져오는 과정에서 문제 발생!!");
			request.setAttribute("error", "게시글을 정상적으로 가져오지 못했습니다!!");
		}

		return "edit.jsp";
	}
	
	public String updateBoard(HttpServletRequest request) {
		Board b = new Board();
		try {
			BeanUtils.populate(b, request.getParameterMap());
			dao.updateBoard(b);
			
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("수정 과정에서 문제 발생!!");
			try {
				String encodeName =URLEncoder.encode("게시물이 정상적으로 등록되지 않았습니다!", "UTF-8");
			return "redirect:/view?board_no=" + b.getBoard_no() + "&error=" + encodeName;
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "redirect:/view?board_no=" + b.getBoard_no(); 
	
	}
	public String deleteBoard(HttpServletRequest request) {
    	int board_no = Integer.parseInt(request.getParameter("board_no"));
		try {
			dao.deleteBoard(board_no);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("삭제 과정에서 문제 발생!!");
			request.setAttribute("error", "정상적으로 삭제되지 않았습니다!!");
			return getList(request);
		}
		return "redirect:/list";
	}
}


