package com.javaex.dao;

import java.util.List;

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
	
}
	
	
	
