package members_Info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MemberDAO {

	public static void join(MemberVO vo) {

//		회원정보 모두 입력 되었는지 확인
		boolean flag = true;
		if (vo.getName().length() == 0) {
			JOptionPane.showMessageDialog(null, "이름이 입력되지 않았습니다.");
			flag = false;
		} else if (vo.getId().length() == 0) {
			JOptionPane.showMessageDialog(null, "id가 입력되지 않았습니다.");
			flag = false;
		} else if (vo.getPassword().length() == 0) {
			JOptionPane.showMessageDialog(null, "password가 입력되지 않았습니다.");
			flag = false;
		} else if (vo.getBirthday() == null) {
			JOptionPane.showMessageDialog(null, "생년월일이 입력되지 않았습니다.");
			flag = false;
		} else if (vo.getPhoneno().length() == 0) {
			JOptionPane.showMessageDialog(null, "핸드폰 번호가 입력되지 않았습니다.");
			flag = false;
		}
		if (flag) {
			try {
				Connection conn = DBUtil.getMySQLConnection();
				String sql = "INSERT INTO members(NAME, id, PASSWORD, birthday, phoneno) VALUES(?, ?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getId());
				pstmt.setString(3, vo.getPassword());
				pstmt.setString(4, vo.getBirthday());
				pstmt.setString(5, vo.getPhoneno());
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
				DBUtil.close(conn);
				DBUtil.close(pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static ArrayList<MemberVO> select() {
		ArrayList<MemberVO> list = null;
		try {
			Connection conn = DBUtil.getMySQLConnection();
			String sql = "select * from memo order by idx desc";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			list = new ArrayList<MemberVO>();
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setBirthday(rs.getString("birthday"));
				vo.setPhoneno(rs.getString("phoneno"));
				list.add(vo);
			}
			DBUtil.close(conn);
			DBUtil.close(pstmt);
			DBUtil.close(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static MemberVO selectByIdx(int position) {
		MemberVO vo = null;
		try {
			Connection conn = DBUtil.getMySQLConnection();
			String sql = "";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, position);
			ResultSet rs = pstmt.executeQuery();
			rs.next();

			vo = new MemberVO();
			vo.setIdx(rs.getInt("idx"));
			vo.setName(rs.getString("name"));
			vo.setId(rs.getString("id"));
			vo.setPassword(rs.getString("password"));
			vo.setBirthday(rs.getString("birthday"));
			vo.setPhoneno(rs.getString("phoneno"));
			DBUtil.close(conn);
			DBUtil.close(pstmt);
			DBUtil.close(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	public static void delete(int selectedRow, String password) {
		if (password.length() == 0) {
			JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
		} else {
			MemberVO vo = selectByIdx(selectedRow);
			try {
				Connection conn = DBUtil.getMySQLConnection();
				String sql = "delete from member where idx = ? and password = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getIdx());
				pstmt.setString(2, password);
				if (pstmt.executeUpdate() == 1) {
					JOptionPane.showMessageDialog(null, vo.getName() + "님의 회원정보가 삭제되었습니다.");
				} else {
					JOptionPane.showConfirmDialog(null, "잘못된 비밀번호입니다.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void update(int selectedRow, String password, String name, String id, String birthday, String phoneno) {
		if(password.length() == 0) {JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");}
		else if(id.length() == 0){JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");}
		else if(birthday.length() == 0) {JOptionPane.showMessageDialog(null, "생년월일을 입력해주세요");}
		else if(phoneno.length() == 0) {JOptionPane.showMessageDialog(null, "핸드폰번호를 입력해주세요");}
		else if(name.length() == 0) {JOptionPane.showMessageDialog(null, "이름을 입력해주세요");}
		else {
			MemberVO vo = selectByIdx(selectedRow);
			try {
				Connection conn = DBUtil.getMySQLConnection();
				String sql = "update member set name = ?, id = ?, birthday = ?, phoneno = ? "
						+ "where idx = ? and password = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, id);
				pstmt.setString(3, birthday);
				pstmt.setString(4, phoneno);
				pstmt.setInt(5, vo.getIdx());
				pstmt.setString(6, password);
				if(pstmt.executeUpdate() == 1) {
					JOptionPane.showMessageDialog(null, vo.getName() + "님의 회원정보가 수정되었습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "잘못된 비밀번호입니다.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}



















