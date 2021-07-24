package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller // 이 클래스가 콘트롤러라고 알려주는 어노테이션(주석)
@RequestMapping(value = "/pb") // 클래스 자체에 RequestMapping을 붙일 경우, 내부의 메소드들은 /pb/ - 가 된다
public class PhoneController {
	
	@Autowired
	private PhoneDao phoneDao;		//외부에서 접근하지 못하게 접근제한자는 private로 설정해주는 것이 좋다
	private String test = "PhoneBookController - ";

	// 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST }) // 둘 이상의 메소드 방식을 지정할 경우, {}로
																							// 묶어주어야 한다
	public String list(Model model) {

		System.out.println(this.test + "list");

		// Dao를 사용, 리스트 가져오기
		List<PersonVo> personList = phoneDao.getList();

		// model에 담기
		model.addAttribute("personList", personList);

		String path = "/WEB-INF/views/list.jsp";
		return path;
	}

	// 쓰기폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String wirteForm() {

		System.out.println(this.test + "writeForm");

		String path = "/WEB-INF/views/writeForm.jsp";
		return path;
	}

	// 쓰기 - @ModelAttribute를 이용한 방법
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String wirte(@ModelAttribute PersonVo personVo) {

		System.out.println(this.test + "writeForm");

		System.out.println(personVo.toString());

		int iCount = phoneDao.personInsert(personVo);
		
		String url = "/pb/list"; // path를 제외한 mapping만 적어주면 된다
		return "redirect:" + url;
	}

	// 삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("personId") int personId) {

		System.out.println(this.test + "delete");

		//phoneDao.delete(personId);

		String url = "/pb/list";
		return "redirect:" + url;
	}

	// 수정폼
	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(Model model, @RequestParam("personId") int personId) {

		System.out.println(this.test + "updateForm");

		//model.addAttribute("personInfo", phoneDao.getPerson(personId));

		String path = "/WEB-INF/views/updateForm.jsp";
		return path;
	}
	
	// 수정
	@RequestMapping(value="/update", method= { RequestMethod.GET, RequestMethod.POST})
	public String update(@ModelAttribute PersonVo personUpdate) {
		
		System.out.println(this.test + "update");
		
		//phoneDao.update(personUpdate);
		
		String url = "/pb/list";
		return "redirect:" + url;
	}


}
