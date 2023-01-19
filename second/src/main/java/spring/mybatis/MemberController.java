package spring.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession; // boot 3점대부터는 jakarta.servlet 사용

@Controller
public class MemberController {

	@Autowired
	@Qualifier("service")
	MemberService service;
	
	@GetMapping("/login")
	public String login() {
		return "mybatis/loginform";
	}
	
	@PostMapping("/login")
	public String login2(String id, String pw, HttpSession session) {
		MemberDTO dto = service.onemember(id);
		String view = "";
		if(dto == null) {
			// 회원가입화면 보여주는 컨트롤러의 URL 호출
			// 회원가입화면 뷰
			view="mybatis/memberinsert";
		}
		else {
			if(pw.equals(dto.getPw())) {
				// 로그인 - 세션 사용
				session.setAttribute("loginid", id);
				view="mybatis/start";
			}
			else {
				// 암호 다르다
				view="mybatis/loginform";
			}
		}
		return view;
	}
	
	// http://ip:port/spring/ - context root url
	@RequestMapping("/")
	public String start() {
		return "mybatis/start";
	}
	
	@RequestMapping("/mybatismemberlist")
	public ModelAndView memberlist() {
		List<MemberDTO> memberlist = service.memberlist();
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberlist", memberlist);
		mv.setViewName("mybatis/memberlist");
		return mv;
	}
	
	@GetMapping("/memberinsert")
	public String memberinsert() {
		return "mybatis/memberinsert";
	}
	
	// 파일 업로드 추가
	@PostMapping("/memberinsert")
	public ModelAndView memberinsert(MemberDTO dto) {
		// dto.setxxxx(xxx파라미터 자동 저장 - name속성명과 dto 필드명이 같은 경우)
		// 파일업로드 c:/upload 저장처리
		// dto image 변수에 c:/upload 저장파일명 세팅
		
		// dto 객체 저장값(폼 사용자값) - member테이블 저장
		// indate(가입일 입력X)
		// 저장한 결과 "정상회원가입처리" 모델로 저장
		// mybatis/memberinsert2 뷰
		
		MemberDTO db_dto = service.onemember(dto.getId());
		String insertresult = "";
		
		if (db_dto == null) {
			int result = service.insertmember(dto);
			if (result == 1) {
				insertresult = "정상회원가입처리";
			}
			else {
				insertresult = "회원가입처리오류발생";
			}
		}
		else {
			insertresult = "이미 사용중인 아이디입니다.";
		}
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("insertresult", insertresult);
		
		mv.setViewName("mybatis/memberinsert2");
		
		return mv;
	}
	
	@RequestMapping("/memberinform")
	public ModelAndView memberinform(HttpSession session) {
		/*
		 * 1. HttpSession 객체에 저장된 'loginid' attribute값을 가져와서
		 * 2. MemberDTO <-- service.onemember(loginid) 호출
		 * 3. 모델 저장
		 * 4. Mybatis/memberinform 뷰
		 * 5. 뷰 - form 태그로 미리 정보 출력 / submit - 내 정보 수정
		 * */
		ModelAndView mv = new ModelAndView();
		if (session.getAttribute("loginid") != null) {
			MemberDTO dto = service.onemember((String)session.getAttribute("loginid"));
			mv.addObject("member", dto);
			mv.setViewName("mybatis/memberinform");
		}
		else {
			mv.setViewName("mybatis/loginform");
		}
		return mv;
	}
	
	@PostMapping("/memberupdate")
	public ModelAndView update(MemberDTO dto) {
		
		/* post - /memberupdate
		 * 1. 내 정보 수정시 입력 정보 모두 가져온다
		 * 2. updatemember2 id sql 실행
		 * -- service + serviceImpl + dao 수정 (update2 용도)
		 * 4. "회원정보수정완료" 모델로 저장
		 * 5. start.jsp 모델 출력 수정
		 * */
		int row = service.updatemember2(dto);
		String updateresult = "";
		if(row == 1) {
			updateresult="회원정보수정완료";
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("updateresult", updateresult);
		mv.setViewName("mybatis/start");
		return mv;
		
	}
	
	/* 로그아웃 */
	/*
	 * 1. 세션에서 loginid 속성값 읽는다 
	 * 2. 존재하면 세션에서 속성 없앤다(remove)
	 * 3. start.jsp 이동
	 * 
	 * */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		if(session.getAttribute("loginid") != null) {
			session.removeAttribute("loginid");
		}
		return "mybatis/start";
	}
	
	/* 회원탈퇴 */
	/*
	 * 1. 세션에서 loginid 속성값 읽는다.
	 * 2. service.deletemember(loginid)
	 * 3. 2번 결과가 1이면 "회원탈퇴정상처리" 모델('updateresult')로 저장
	 * 4. start.jsp 이동
	 */
	@RequestMapping("/memberdelete")
	public ModelAndView memberdelete(HttpSession session) {	
		
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("loginid") != null) {
			String loginid = (String)session.getAttribute("loginid");
			int row = service.deletemember(loginid);
			session.removeAttribute("loginid");
			
			if (row == 1) {
				mv.addObject("updateresult", "회원탈퇴정상처리");
			}
			else {
				mv.addObject("updateresult", "회원탈퇴 실패");
			}
			
		}
		
		mv.setViewName("mybatis/start");
		return mv;
	}
	
}
