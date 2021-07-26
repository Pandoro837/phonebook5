package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	// 쓰기폼

	@RequestMapping(value = "/writeForm2", method = { RequestMethod.GET, RequestMethod.POST })
	public String wirteForm2() {
		
		System.out.println(this.test + "writeForm");
		
		String path = "/WEB-INF/views/writeForm2.jsp";
		return path;
	}

	// 쓰기 - @ModelAttribute를 이용한 방법
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String wirte(@ModelAttribute PersonVo personVo) {

		System.out.println(this.test + "writeForm");

		System.out.println(personVo.toString());

		int iCount = phoneDao.personInsert(personVo);
		
		System.out.println(iCount);
		
		String url = "/pb/list"; // path를 제외한 mapping만 적어주면 된다
		return "redirect:" + url;
	}
	
	// 쓰기 - Map을 이용한 방법
	@RequestMapping(value = "/write2", method = { RequestMethod.GET, RequestMethod.POST })
	public String wirte2(@RequestParam("name") String name, @RequestParam("hp") String hp, @RequestParam("company") String company) {
		
		System.out.println(this.test + "writeForm");
		
		Map<String, Object> personInfo = new HashMap<String, Object>();
		personInfo.put("name", name);
		personInfo.put("hp", hp);
		personInfo.put("company", company);
		
		int iCount = phoneDao.personInsert2(personInfo);
		
		System.out.println(iCount);
		
		String url = "/pb/list"; // path를 제외한 mapping만 적어주면 된다
		return "redirect:" + url;
	}

	// 삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("personId") int personId) {

		System.out.println(this.test + "delete");

		phoneDao.personDelete(personId);

		String url = "/pb/list";
		return "redirect:" + url;
	}

	// 수정폼
	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(Model model, @RequestParam("personId") int personId) {

		System.out.println(this.test + "updateForm");
		
		PersonVo personInfo =  phoneDao.getPerson(personId);
		
		model.addAttribute("personInfo", personInfo);

		String path = "/WEB-INF/views/updateForm.jsp";
		return path;
	}
	
	@RequestMapping(value = "/updateForm2", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm2(Model model, @RequestParam("personId") int personId) {
		
		System.out.println(this.test + "updateForm");
		
		Map<String, Object> personInfo =  phoneDao.getPerson2(personId);
		
		model.addAttribute("personInfo", personInfo);
		
		String path = "/WEB-INF/views/updateForm2.jsp";
		return path;
	}
	
	// 수정
	@RequestMapping(value="/update", method= { RequestMethod.GET, RequestMethod.POST})
	public String update(@ModelAttribute PersonVo personUpdate) {
		
		System.out.println(this.test + "update");
		
		phoneDao.personUpdate(personUpdate);
		
		String url = "/pb/list";
		return "redirect:" + url;
	}
	
	// 수정
	@RequestMapping(value="/update2", method= { RequestMethod.GET, RequestMethod.POST})
	public String update2(@RequestParam("personId") int personId, @RequestParam("name") String name, @RequestParam("hp") String hp, @RequestParam("company") String company) {
		
		System.out.println(this.test + "update");
		
		System.out.println(personId);
		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);
		
		Map<String, Object> personUpdate = new HashMap<String, Object>();
		personUpdate.put("personId", personId);
		personUpdate.put("name", name);
		personUpdate.put("hp", hp);
		personUpdate.put("company", company);
		
		phoneDao.personUpdate2(personUpdate);
		
		String url = "/pb/list";
		return "redirect:" + url;
	}


}
