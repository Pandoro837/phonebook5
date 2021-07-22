package com.javaex.controller;

import java.util.List;

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

	private String test = "PhoneBookController - ";

	// 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST }) // 둘 이상의 메소드 방식을 지정할 경우, {}로
																							// 묶어주어야 한다
	public String list(Model model) {

		System.out.println(this.test + "list");

		// Dao호출
		PhoneDao phoneDao = new PhoneDao();

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
		// @ModelAttribute - > new PersonVo();
		// - > 기본 생성자 + setter로 입력

		System.out.println(this.test + "writeForm");

		System.out.println(personVo.toString());

		// dao 호출
		PhoneDao phoneDao = new PhoneDao();
		// 사용하여 insert
		int iCount = phoneDao.insert(personVo);

		System.out.println(iCount + "건 저장되었습니다");

		String url = "/pb/list"; // path를 제외한 mapping만 적어주면 된다
		return "redirect:" + url;
	}

	// 삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("personId") int personId) {

		System.out.println(this.test + "delete");

		PhoneDao phoneDao = new PhoneDao();
		phoneDao.delete(personId);

		String url = "/pb/list";
		return "redirect:" + url;
	}

	// 수정폼
	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(Model model, @RequestParam("personId") int personId) {

		System.out.println(this.test + "updateForm");

		PhoneDao phoneDao = new PhoneDao();

		model.addAttribute("personInfo", phoneDao.getPerson(personId));

		String path = "/WEB-INF/views/updateForm.jsp";
		return path;
	}
	
	// 수정
	@RequestMapping(value="/update", method= { RequestMethod.GET, RequestMethod.POST})
	public String update(@ModelAttribute PersonVo personUpdate) {
		
		System.out.println(this.test + "update");
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.update(personUpdate);
		
		String url = "/pb/list";
		return "redirect:" + url;
	}
	// 쓰기 - @RequestPram을 이용한 방법
//	@RequestMapping(value="write", method = {RequestMethod.GET, RequestMethod.POST})
//	public String wirte(@RequestParam("name") String name,
//						@RequestParam("hp") String hp,
//						@RequestParam("company") String company)
//	{
//		
//		System.out.println(this.test + "writeForm");
//		
//		PersonVo personVo = new PersonVo(name, hp, company);
//		
//		System.out.println(personVo.toString());
//		
//		return "";
//	}

	// 쓰기 - @RequestPram, 파라미터가 있을 수도, 없을 수도 있을 때
//	@RequestMapping(value="write", method = {RequestMethod.GET, RequestMethod.POST})
//	public String wirte(@RequestParam("name") String name,
//						@RequestParam("hp") String hp,
//						@RequestParam(value = "company", required=false, defaultValue="default") String company)	{
//		
//		System.out.println(this.test + "writeForm");
//		
//		PersonVo personVo = new PersonVo(name, hp, company);
//		
//		System.out.println(personVo.toString());
//		
//		return "";
//	}

//	테스트
//	*******************************************************************************************
	@RequestMapping(value = "/board/read/{no}", method = { RequestMethod.GET, RequestMethod.PATCH })
	public String read(@PathVariable("no") int boardNo) {
		System.out.println("PathVariable - test");

		System.out.println(boardNo);

		return "";
	}

	@RequestMapping(value = "/test") // value 내부의 값일 때, 해당 메소드가 작동한다고 알려주는 어노테이션
	public String test() {
		System.out.println(this.test + "test");

		String path = "/WEB-INF/views/test.jsp";

		return path; // DispatcherServlet에게 해당 경로로 포워드하도록 지시한다
	}

}
