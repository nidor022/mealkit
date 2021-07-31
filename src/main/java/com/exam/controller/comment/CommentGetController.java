package com.exam.controller.comment;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.exam.controller.Controller;
import com.exam.dao.CommentDao;
import com.exam.vo.CommentVo;
import com.google.gson.Gson;


public class CommentGetController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CommentGetController......");
		
		// VO DAO �غ�
		CommentDao commentDao = CommentDao.getInstance();
		
		// ���Խñ� ��ȣ �޾ƿ���
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		String pageNum = request.getParameter("pageNum");
		
		// ��ȣ�� �ش� �Խù� ��� �� �ҷ�����
        List<CommentVo> commentList = commentDao.getCommentsByNum(b_num);
        
        Map<String, Object> map = new HashMap<>();
        map.put("commentList", commentList);
        map.put("totalCount", commentList.size());
        
//        JSONArray jsonArray = new JSONArray();  
        Gson gson = new Gson();
        String strJson = gson.toJson(map);
        System.out.println("strJson = " + strJson);
        
        //response.setContentType("text/html; charset=UTF-8");
        response.setContentType("application/json; charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	out.println(strJson);
//    	int cnt = 0;
    	/*
        if(commentList.size() > 0) {
        	
        	for(int i=0; i<commentList.size(); i++) {
        		CommentVo comment = commentList.get(i);
	        	out.print(" <div>");
	        	out.print("		<div>");
	        	out.print("			<table class='table'>");
	        	out.print("				<tr>");
	        	out.print("					<td>");
	        	out.print(" 					<div>" + comment.getC_id() + "</div>");
	        	out.print("  					<div>" + comment.getC_content() + "</div>");
	        	out.print("					</td>");
	        	out.print("				</tr>");
	        	out.print("				<tr>");
	        	out.print("					<td>" + comment.getC_date() + "</td>");
	        	out.print("				</tr>");
	        	out.print("     	</table>");
	        	out.print("   	</div>");
	        	out.print(" </div>");
//	        	cnt++;
        	} 
        } else {
        	out.print("<div>");
        	out.print("	   <div>");
        	out.print("        <table class='table'>");
        	out.print("			   <tr>");
        	out.print("				   <td><div><p>��ϵ� ����� �����ϴ�.</p></div></td>");
        	out.print("			   </tr>");
        	out.print("		   </table>");
        	out.print("	   </div>");
        	out.print("</div>");
    	}
    	*/
//        System.out.println(cnt);
       
        
        
    	//out.print(strJson);
        out.close();
        
//        return "redirect:/fileContent.do?num=" + b_num + "&pageNum=" + pageNum;
        return null;
	}

}
