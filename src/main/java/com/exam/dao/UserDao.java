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
	
	// 싱글톤 패턴 설계
	private static UserDao instance = new UserDao();
	
	public static UserDao getInstance() {
		return instance;
	}
	
	///////////////////////////////////////////////////////////

	private UserDao() {
	}

	// 회원정보 1명 insert하기
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
			// 예외 발생여부에 관계없이 무조건 정리작업 수행함.
			// try블록에서 만든 객체를 정리하는 작업을 주로 함
			JdbcUtils.close(con, pstmt);
		}
	} // addMember()
	
	
	// 로그인 확인.
	// check -1  없는 아이디
	// check  0  패스워드 틀림
	// check  1  아이디, 패스워드 모두 일치
	public int userCheck(String id, String pwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		int check = -1; // 없는 아이디 상태값으로 초기화
		
		try {
			con = JdbcUtils.getConnection();
			// id에 해당하는 passwd 가져오기
			sql = "SELECT pwd FROM web.user WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// rs에 저장
			rs = pstmt.executeQuery();
			// rs에 데이터(행) 있으면
			//             패스워드 비교  맞으면  check = 1  틀리면  check = 0
			// rs에 데이터(행) 없으면   check = -1
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
	
	
	
	
	// 전체 회원목록 가져오기
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
	
	
	// 특정id에 해당하는 회원 1명 가져오기
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
	
	
	// 특정id에 해당하는 회원 정보 수정하기
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
			// 예외 발생여부에 관계없이 무조건 정리작업 수행함.
			// try블록에서 만든 객체를 정리하는 작업을 주로 함
			JdbcUtils.close(con, pstmt);
		}
	} // update()
	
	// 특정id에 해당하는 회원 정보 수정하기
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
			// 예외 발생여부에 관계없이 무조건 정리작업 수행함.
			// try블록에서 만든 객체를 정리하는 작업을 주로 함
			JdbcUtils.close(con, pstmt);
		}
	} // update()
	
	// 특정id에 해당하는 회원 1명 삭제하기
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
	
	
	// 모든 회원 삭제하기
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
		
		// MemberDao 객체 준비
		UserDao userDao = new UserDao();
		
		Random random = new Random();
		
		userDao.deleteAll(); // 전체삭제
		
		System.out.println("======== insert 테스트 =========");
		
		for (int i=0; i<1000; i++) {
			UserVo userVo = new UserVo();
			userVo.setId("user"+i);
			userVo.setPwd("1234");
			userVo.setName("유저"+i);
			
			// 나이값의 범위  8세이상 ~ 100세이하
			int age = random.nextInt(93) + 8; // (0~92)+8 -> (8~100)
			userVo.setAge(age);
			
			boolean isMale = random.nextBoolean(); // 남성 true 여성 false
			if (isMale) {
				userVo.setGender("남");
			} else {
				userVo.setGender("여");
			}
			
			userVo.setEmail("user" + i + "@user.com");
			userVo.setRegDate(new Timestamp(System.currentTimeMillis()));
			userVo.setAddress("부산시");
			userVo.setTel("010-1234-5678");
			userDao.addUser(userVo);
			System.out.println("insert 성공!");
		}
		
		List<UserVo> list = userDao.getAllUsers();
		for (UserVo userVo : list) {
			System.out.println(userVo);
		}
		
		System.out.println("======== getMemberById 테스트 =========");
		
		String testId = "user0";
		
		UserVo userVo = userDao.getUserById(testId);
		System.out.println(userVo);
		
		System.out.println("======== update 테스트 =========");
		
		userVo.setName("이순신"); // 수정될 이름값 설정
		userDao.update(userVo);
		
		UserVo getUserVo = userDao.getUserById(testId);
		System.out.println(getUserVo);
		
		System.out.println("======== deleteById 테스트 =========");
		
		userDao.deleteById(testId);
		
		UserVo getUserVo2 = userDao.getUserById(testId);
		System.out.println(getUserVo2);
		
	} // main
	
}






