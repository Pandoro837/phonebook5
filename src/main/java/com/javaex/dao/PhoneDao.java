package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<PersonVo> getList(){
		
		//리스트를 가져온다
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");
		
		System.out.println(personList.toString());
		
		return personList;
	}
	
	public int personInsert(PersonVo personVo) {
		
		int iCount = -1;
		
		iCount = sqlSession.insert("phonebook.personInsert", personVo);
		
		return iCount;
	}

	public int personInsert2(Map<String, Object> personInfo) {
		
		int iCount = -1;
		
		iCount = sqlSession.insert("phonebook.personInsert2", personInfo);
		
		return iCount;
	}
	
	public int personDelete(int personId) {
		
		int iCount = -1;
		
		iCount = sqlSession.delete("phonebook.personDelete", personId);
		
		return iCount;
	}

	public PersonVo getPerson(int personId) {
		
		PersonVo personInfo = sqlSession.selectOne("phonebook.getPerson", personId);
		
		System.out.println(personInfo);
		
		return personInfo;
	}

	public Map<String, Object> getPerson2(int personId) {
		
		Map<String, Object> personInfo = sqlSession.selectOne("phonebook.getPerson2", personId);
		
		System.out.println(personInfo);	//id 있음
		
		return personInfo;
	}
	
	public int personUpdate(PersonVo personVo) {
		int iCount = -1;
		
		iCount = sqlSession.update("phonebook.personUpdate", personVo);
		
		return iCount;
	}
	
	public int personUpdate2(Map<String, Object> personInfo) {
		int iCount = -1;
		
		iCount = sqlSession.update("phonebook.personUpdate2", personInfo);
		
		return iCount;
	}
}
	
	
	
