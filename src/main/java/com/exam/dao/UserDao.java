package com.exam.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.exam.vo.UserVo;

public class UserDao {
	
	// �̱��� ���� ����
	private static UserDao instance = new UserDao();
	
	public static UserDao getInstance() {
		return instance;
	}
	
	///////////////////////////////////////////////////////////

	private UserDao() {
	}

	// ȸ������ 1�� insert�ϱ�
	public void addUser(UserVo userVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = JdbcUtils.getConnection();
			
			String sql = "";
			sql += "INSERT INTO web.user (id, pwd, name, age, gender, email, reg_date, birth_day, address, tel) ";
			sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getPwd());
			pstmt.setString(3, userVo.getName());
			pstmt.setInt(4, userVo.getAge());
			pstmt.setString(5, userVo.getGender());
			pstmt.setString(6, userVo.getEmail());
			pstmt.setTimestamp(7, userVo.getRegDate());
			pstmt.setString(8, userVo.getBirthDay());
			pstmt.setString(9, userVo.getAddress());
			pstmt.setString(10, userVo.getTel());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ���� �߻����ο� ������� ������ �����۾� ������.
			// try��Ͽ��� ���� ��ü�� �����ϴ� �۾��� �ַ� ��
			JdbcUtils.close(con, pstmt);
		}
	} // addMember()
	
	
	// �α��� Ȯ��.
	// check -1  ���� ���̵�
	// check  0  �н����� Ʋ��
	// check  1  ���̵�, �н����� ��� ��ġ
	public int userCheck(String id, String pwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		int check = -1; // ���� ���̵� ���°����� �ʱ�ȭ
		
		try {
			con = JdbcUtils.getConnection();
			// id�� �ش��ϴ� passwd ��������
			sql = "SELECT pwd FROM web.user WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// rs�� ����
			rs = pstmt.executeQuery();
			// rs�� ������(��) ������
			//             �н����� ��  ������  check = 1  Ʋ����  check = 0
			// rs�� ������(��) ������   check = -1
			if (rs.next()) {
				if (pwd.equals(rs.getString("pwd"))) {
					check = 1;
				} else {
					check = 0;
				}
			} else {
				check = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		return check;
	} // userCheck()
	
	
	
	
	// ��ü ȸ����� ��������
	public List<UserVo> getAllUsers() {
		List<UserVo> list = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			sql = "SELECT * FROM web.user ORDER BY id";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				UserVo userVo = new UserVo();
				userVo.setId(rs.getString("id"));
				userVo.setPwd(rs.getString("pwd"));
				userVo.setName(rs.getString("name"));
				userVo.setAge(rs.getInt("age"));
				userVo.setGender(rs.getString("gender"));
				userVo.setEmail(rs.getString("email"));
				userVo.setRegDate(rs.getTimestamp("reg_date"));
				userVo.setBirthDay(rs.getString("birth_day"));
				userVo.setAddress(rs.getString("address"));
				userVo.setTel(rs.getString("tel"));
				
				list.add(userVo);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		return list;
	} // getAllMembers()
	
	
	// Ư��id�� �ش��ϴ� ȸ�� 1�� ��������
	public UserVo getUserById(String id) {
		UserVo userVo = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			sql = "SELECT * FROM web.user WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				userVo = new UserVo();
				userVo.setId(rs.getString("id"));
				userVo.setPwd(rs.getString("pwd"));
				userVo.setName(rs.getString("name"));
				userVo.setAge(rs.getInt("age"));
				userVo.setGender(rs.getString("gender"));
				userVo.setEmail(rs.getString("email"));
				userVo.setRegDate(rs.getTimestamp("reg_date"));
				userVo.setBirthDay(rs.getString("birth_day"));
				userVo.setAddress(rs.getString("address"));
				userVo.setTel(rs.getString("tel"));
			} // if
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		return userVo;
	} // getMemberById()
	
	
	public int getCountById(String id) {
		int count = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			sql = "SELECT COUNT(*) FROM web.user WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			} // if
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		return count;
	} // getCountById()
	
	
	// Ư��id�� �ش��ϴ� ȸ�� ���� �����ϱ�
	public void update(UserVo userVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = JdbcUtils.getConnection();
			
			String sql = "";
			sql += "UPDATE web.user ";
			sql += "SET name = ?, age = ?, gender = ?, email = ?, birth_day = ?, address = ?, tel = ? ";
			sql += "WHERE id = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userVo.getName());
			pstmt.setInt(2, userVo.getAge());
			pstmt.setString(3, userVo.getGender());
			pstmt.setString(4, userVo.getEmail());
			pstmt.setString(5, userVo.getBirthDay());
			pstmt.setString(6, userVo.getAddress());
			pstmt.setString(7, userVo.getTel());
			pstmt.setString(8, userVo.getId());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ���� �߻����ο� ������� ������ �����۾� ������.
			// try��Ͽ��� ���� ��ü�� �����ϴ� �۾��� �ַ� ��
			JdbcUtils.close(con, pstmt);
		}
	} // update()
	
	// Ư��id�� �ش��ϴ� ȸ�� ���� �����ϱ�
	public void update(String id, String name, int age, String gender, String email, String birthday, String address, String tel) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = JdbcUtils.getConnection();
			
			String sql = "";
			sql += "UPDATE web.user ";
			sql += "SET name = ?, age = ?, gender = ?, email = ?, birth_day = ?, address = ?, tel = ? ";
			sql += "WHERE id = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setString(3, gender);
			pstmt.setString(4, email);
			pstmt.setString(5, birthday);
			pstmt.setString(6, address);
			pstmt.setString(7, tel);
			pstmt.setString(8, id);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ���� �߻����ο� ������� ������ �����۾� ������.
			// try��Ͽ��� ���� ��ü�� �����ϴ� �۾��� �ַ� ��
			JdbcUtils.close(con, pstmt);
		}
	} // update()
	
	// Ư��id�� �ش��ϴ� ȸ�� 1�� �����ϱ�
	public void deleteById(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = JdbcUtils.getConnection();
			
			String sql = "";
			sql += "DELETE FROM web.user WHERE id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt);
		}
	} // deleteById()
	
	
	// ��� ȸ�� �����ϱ�
	public void deleteAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = JdbcUtils.getConnection();
			
			String sql = "";
			sql += "DELETE FROM web.user ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt);
		}
	} // deleteAll()
	
	
	public static void main(String[] args) {
		
		// MemberDao ��ü �غ�
		UserDao userDao = new UserDao();
		
		Random random = new Random();
		
		userDao.deleteAll(); // ��ü����
		
		System.out.println("======== insert �׽�Ʈ =========");
		
		for (int i=0; i<1000; i++) {
			UserVo userVo = new UserVo();
			userVo.setId("user"+i);
			userVo.setPwd("1234");
			userVo.setName("����"+i);
			
			// ���̰��� ����  8���̻� ~ 100������
			int age = random.nextInt(93) + 8; // (0~92)+8 -> (8~100)
			userVo.setAge(age);
			
			boolean isMale = random.nextBoolean(); // ���� true ���� false
			if (isMale) {
				userVo.setGender("��");
			} else {
				userVo.setGender("��");
			}
			
			userVo.setEmail("user" + i + "@user.com");
			userVo.setRegDate(new Timestamp(System.currentTimeMillis()));
			userVo.setAddress("�λ��");
			userVo.setTel("010-1234-5678");
			userDao.addUser(userVo);
			System.out.println("insert ����!");
		}
		
		List<UserVo> list = userDao.getAllUsers();
		for (UserVo userVo : list) {
			System.out.println(userVo);
		}
		
		System.out.println("======== getMemberById �׽�Ʈ =========");
		
		String testId = "user0";
		
		UserVo userVo = userDao.getUserById(testId);
		System.out.println(userVo);
		
		System.out.println("======== update �׽�Ʈ =========");
		
		userVo.setName("�̼���"); // ������ �̸��� ����
		userDao.update(userVo);
		
		UserVo getUserVo = userDao.getUserById(testId);
		System.out.println(getUserVo);
		
		System.out.println("======== deleteById �׽�Ʈ =========");
		
		userDao.deleteById(testId);
		
		UserVo getUserVo2 = userDao.getUserById(testId);
		System.out.println(getUserVo2);
		
	} // main
	
}






