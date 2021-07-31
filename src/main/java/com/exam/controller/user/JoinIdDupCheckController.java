package com.exam.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Controller;
import com.exam.dao.UserDao;

public class JoinIdDupCheckController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("JoinIdDupCheckController......");
		
		// �Ķ���Ͱ� id ��������
		String id = request.getParameter("id");

		// DAO ��ü �غ�
		UserDao userDao = UserDao.getInstance();

		// �ߺ����̵� ���� Ȯ��
		int count = userDao.getCountById(id);
		
		// (view����µ� �ʿ��� ������ id�� count)
		// ���ǿ� �����ؼ� �信�� ����ص������� ���ʿ� �޸𸮸� ���������� �ʿ�¾��� �׷���
		// request��ü�� ���
							//Web Application Server
		// * �������� ������ü 4���� (�����ֱ�ū��)(��Ĺ���� WAS�� �����ϴ� Map �÷���)
		//   application : �����α׷� 1���� �����Ǵ� ������ü 
//						(����ڸ��� �������ʿ���� ��ü �����ؾ��ϴ°��� �������)
		//   session : ����� 1��� �����Ǵ� ������ü 
		//   request : ��û �ѹ����� �����Ǵ� ������ü (��û���� ����� �������� �� ���)
//						(����ġ��Ŀ� ���� ������ �Ѿ�� �Ѿ�� �� �� request ���������� ���� ����)
		//   page : jsp ������ �ѹ� ���ට�� �����Ǵ� ������ü 
					
		
		// request ������ü�� Viewȭ�鿡�� �ʿ��� �����͸� ����
		request.setAttribute("id", id); // ��û-������ �ڵ����� �����Ǵ� ��ü�� ����ڰ� ������ �����ʿ�x
		request.setAttribute("count", count); // ������ �����Ǵ� ��ü�ϱ� ������� �Ʋ����� ��
		
		// ����ġ ������� jsp�� �ٷν����ϸ�
		// �տ��� ����� request��ü�� jsp���� ���޵ǹǷ�
		// �����͸� ����� �� ����
		
		return "user/joinIdDupCheck"; // FrontController�� 2�ܰ� strView�� �����ؼ� �յڷ� �޾���
	}	// request ��ü�� ���� �� �� �ִ� dispatch ���

}
