//package com.exam.controller.comment;
//
//import java.io.PrintWriter;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import com.exam.controller.Controller;
//import com.exam.dao.CommentDao;
//import com.exam.vo.CommentVo;
//import com.google.gson.Gson;
//
//
//public class CommentsNumController implements Controller {
//
//	@Override
//	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("CommentsNumController......");
//		
//		// VO DAO �غ�
//		CommentDao commentDao = CommentDao.getInstance();
//		
//		// ���Խñ� ��ȣ �޾ƿ���
//		int b_num = Integer.parseInt(request.getParameter("b_num"));
//		String pageNum = request.getParameter("pageNum");
//		
//		// ��ȣ�� �ش� �Խù� ��� �� �ҷ�����
//        int comments = commentDao.getCommentsNum(b_num);
//        
//        response.setContentType("text/html; charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        out.print(comments);
//        out.close();
//        
//        return null;
//	}
//
//}
