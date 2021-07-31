package com.exam.controller.user;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Controller;
import com.exam.dao.UserDao;
import com.exam.vo.UserVo;

public class UserJoinProController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserJoinProController......");
		
		// VO ��ü �غ�
		UserVo vo = new UserVo();
		
		// �׼��±׷� VO��ü�� �Ķ���Ͱ� ����
		vo.setId(request.getParameter("id"));
		vo.setPwd(request.getParameter("pwd"));
		vo.setName(request.getParameter("name"));
		vo.setAge(Integer.parseInt(request.getParameter("age")));
		vo.setGender(request.getParameter("gender"));
		vo.setEmail(request.getParameter("email"));
		vo.setAddress(request.getParameter("address"));
		vo.setTel(request.getParameter("tel"));
		
		// ����
		vo.setBirthDay(request.getParameter("birthday"));
		
		// ���Գ�¥ �����ؼ� �ֱ�
		vo.setRegDate(new Timestamp(System.currentTimeMillis()));

		// DAO ��ü �غ�
		UserDao userDao = UserDao.getInstance();
		// ȸ������ �޼��� ȣ��
		userDao.addUser(vo);
		
		// �α��� ȭ�� ��û��η� �����̷�Ʈ��Ű�� ���ؼ�
		// �����̷�Ʈ ������ ������
		return "redirect:/userLogin.do";
//		return "member/login";
		// �̷��������ϸ� ȸ�������� ������ �ٷ� jsp�� �����ؼ� ����ȭ���� login�ΰ�
		// �Ȱ���. ��� ���ΰ�ħ�� �߻���. �ѹ��� insert�ȴ�.
	}

}
