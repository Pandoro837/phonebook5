package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PersonVo;

public class PhoneDao {
	
	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id ="phonedb";
	private String pw ="phonedb";
	
	//db와 연결
	private void getConnection() {
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
		    // 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
		 
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
	}
	
	//db와 연결 해제
	private void close() {
		try {
	        if (rs != null) {
	            rs.close();
	        }                
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    } catch (SQLException e) {
	        System.out.println("error:" + e);
	    }
	}
	
	public List<PersonVo> getList(){
		List<PersonVo> personList = new ArrayList<PersonVo>();
		
		getConnection();
		
		try {
			String query = "";
			query+= " SELECT ";
			query+= " 		person_id, ";
			query+= " 		name, ";
			query+= " 		hp, ";
			query+= " 		company ";
			query+= " FROM ";
			query+= " 		person ";
			query+= " ORDER BY person_id ASC ";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				PersonVo personVo = new PersonVo(personId, name, hp, company);
				personList.add(personVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return personList;
	}
	
	public void printList(List<PersonVo> personList) {
		for(PersonVo personInfo : personList) {
			System.out.println(personInfo);
		}
	}
	
	public int insert(PersonVo personVo) {
		int iCount = -1;
		
		getConnection();
		
		try {
			String query = "";
			query+= " INSERT INTO person ";
			query+= " VALUES(seq_person_id.NEXTVAL, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			
			iCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return iCount;
	}
	
	public int update(PersonVo personVo) {
		int iCount = -1;
		
		getConnection();
		
		try {
			String query = "";
			query+= " UPDATE person ";
			query+= " SET ";
			query+= " name = ?, ";
			query+= " hp = ?, ";
			query+= " company = ? ";
			query+= " WHERE person_id = ? ";
			
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPersonId());
			
			iCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return iCount;
	}
	
	public int delete(int personId) {
		int iCount = -1;
		
		getConnection();
		
		try {
			String query = "";
			query+= " DELETE ";
			query+= " person ";
			query+= " WHERE person_id = ? ";
			
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personId);
			
			iCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return iCount;
	}
	
	public List<PersonVo> search(String searchWord) {
		List<PersonVo> personList = new ArrayList<PersonVo>();
		
		getConnection();
		
		try {
			String query = "";
			query+= " SELECT ";
			query+= " 		person_id, ";
			query+= " 		name, ";
			query+= " 		hp, ";
			query+= " 		company ";
			query+= " FROM ";
			query+= " 		person ";
			query+= " WHERE name LIKE ? ";
			query+= "    OR hp LIKE ? ";
			query+= "    OR company LIKE ? ";
			
//			query+= " WHERE (name||hp||company) LIKE ? ";  이 쿼리문을 이용해서도 검색이 가능하다
			
			pstmt = conn.prepareStatement(query);
			searchWord = "%" + searchWord + "%";
			pstmt.setString(1, searchWord);
			pstmt.setString(2, searchWord);
			pstmt.setString(3, searchWord);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				PersonVo personVo = new PersonVo(personId, name, hp, company);
				personList.add(personVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		
		return personList;
	}
	
//	public PersonVo getPerson(int personId) {
//		PersonVo personVo = this.getList().get(personId);
//		return personVo;
//	}
	
	public PersonVo getPerson(int personId) {
		PersonVo personVo = new PersonVo();
		getConnection();
		
		try {
			String query = "";
			query+= " SELECT ";
			query+= " 		person_id, ";
			query+= " 		name, ";
			query+= " 		hp, ";
			query+= " 		company ";
			query+= " FROM ";
			query+= " 		person ";
			query+= " WHERE person_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personId);
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				personVo = new PersonVo(personId, name, hp, company);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		close();
		
		return personVo;
	}
	
}
	
	
	
