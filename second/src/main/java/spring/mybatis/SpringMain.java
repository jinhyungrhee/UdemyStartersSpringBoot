package spring.mybatis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) throws IOException {
		
		ApplicationContext factory = 
				new ClassPathXmlApplicationContext("spring/mybatis/spring-mybatis.xml");
		
		//String names[] = factory.getBeanDefinitionNames();
		//System.out.println("=========================================");
		//for (String n : names) {
		//	System.out.println(n);
		//}		
		//System.out.println("=========================================");
		
		
		MemberService service = (MemberService)factory.getBean("service");
		
		////////////////////////////////////////////////////////////////////
		// test1 - 리스트 조회
		
		List<MemberDTO> list = service.memberlist();
		for (MemberDTO m : list) {
			System.out.println(m.getId() + " : " + m.getPw() + " : " + m.getName());
		}
		
		// test2 - 조회 int
		//System.out.println("전체 회원수  = " + service.membercount());
		
		// test3 - 조회(입력파라미터 존재, 결과 MemberDTO)
		/*
		MemberDTO m = service.onemember("maria123");
		if (m != null) { // 예외처리
			System.out.println(m.getId() + " : " + m.getPw() + " : " + m.getName());
		}
		*/
		
		// test4 - 페이징 처리된 리스트 조회
		/*
		int[] limit = {0, 3}; // {시작페이지, 페이지수}
		List<MemberDTO> list = service.paginglist(limit);
		for (MemberDTO m : list) {
			System.out.println(m.getId() + " : " + m.getPw() + " : " + m.getName());
		}
		*/
		
		// test5
		/*
		MemberDTO insertdto = new MemberDTO();
		insertdto.setId("mybatis");
		insertdto.setPw("mybatis");
		insertdto.setName("박대한");
		insertdto.setPhone("01012345678");
		insertdto.setEmail("A@b.com");
		insertdto.setAddress("서울시 용산구");
		service.insertmember(insertdto);
		*/
		
		// test6
		// id=mybatis, name="박한국", phone=01087654321 이메일=mybatis@b.com 으로 수정
		// updatemember sql 실행
		/*
		MemberDTO m = service.onemember("mybatis");
		m.setName("박한국");
		m.setPhone("01087654321");
		m.setEmail("mybatis@b.com");
		service.updatemember(m);
		*/
		
		// test7
		// id가 mybatis인 데이터 삭제
		//service.deletemember("mybatis");
		
		/* 1. id('mybatis') 회원이 작성한 글이 있는지 검사
		 * 2. 글이 있으면 사용자 탈퇴전에 글도 삭제하시겠습니까? y/n
		 * 3-1. 사용자 탈퇴 처리 
		 * 3-2. 사용자 탈퇴 취소 
		 */
		/*
		Scanner sc = new Scanner(System.in);
		System.out.println("사용자 ID를 입력하세요:");
		String id = sc.next();
		sc.nextLine(); // 개행문자 처리
		
		MemberDTO m = service.onemember(id);
		if (m != null) { // 예외처리
			
			List<HashMap<String, String>> boardlist = service.memberboard(id);
			// 글이 있으면 빈리스트가 아님
			// 글이 없으면 빈리스트 리턴
			if (!boardlist.isEmpty()) { // 글이 있으면
				System.out.println("탈퇴전에 글도 삭제하시겠습니까");
				String answer = sc.next();
				if (answer.equals("y")) {
					service.deletemember(id);
					System.out.println("사용자 탈퇴 처리");
				} // "n"을 입력한 경우 삭제하지 않음(삭제취소)
				else {
					System.out.println("사용자 탈퇴 취소");
				}
				
 			} else { // 글이 없으면
				service.deletemember(id);
			}
		}
		else {
			System.out.println("해당 아이디가 존재하지 않습니다!");
		}
		*/
		
		// test8 - map
		/*
		HashMap<String, String> map = new HashMap();
		//map.put("colname", "name");
		//map.put("colvalue", "%길동%");
		map.put("colname", "indate"); // ${colname}
		map.put("colvalue", "2023%"); // #{colvalue}
		*/
		// column 이름이나 table 이름 (sql문 정적 이름)은 ${} 사용!
		// parameter로 입력받은 값들은 #{} 사용!
		// select * from member where ${colname} like #{colvalue}
		/*
		List<MemberDTO> searchlist = service.searchmember(map);
		for (MemberDTO m : searchlist) {
			System.out.println
			(m.getId()+" : " + m.getPw() + " : " + m.getName() + " : " + m.getEmail() + " : " + m.getPhone()
			+ " : " + m.getIndate());
		} 
		*/
		
		// test9 
		/*
		ArrayList<String> addresslist = new ArrayList();
		addresslist.add("서울시 용산구");
		addresslist.add("제주시 용산구");
		addresslist.add("서울시 노원구");
		List<MemberDTO> resultlist= service.addresssearch(addresslist);
		for (MemberDTO m : resultlist) {
			System.out.println
			(m.getId()+" : " + m.getPw() + " : " + m.getName() + " : " + m.getEmail() + " : " + m.getPhone()
			+ " : " + m.getAddress());
		} 
		*/
		// 동적인 sql 생성
		/* select from member where address in 
		 * <foreach collection="list" item="addr" open="(" separator="," close=")" >
		 * #{addr}
		 * </foreach>
		 * */
 		// select from member where address in('서울시 용산구', '제주시 용산구', '부산시 xxx');
		
		// test10 - 조합 + 동적조건절
		/*
		MemberDTO dto = new MemberDTO();
		dto.setName("마리아");
		//dto.setEmail("maria@gmail.com");
		List<MemberDTO> resultlist = service.combination(dto);
		for (MemberDTO m : resultlist) {
			System.out.println
			(m.getId()+" : " + m.getPw() + " : " + m.getName() + " : " + m.getEmail() + " : " + m.getPhone()
			+ " : " + m.getAddress());
		} 
		*/
		
		// test11 - join시 resultMap 이용 (하나의 방법)
		/*
		List<HashMap<String, String>> boardlist = service.memberboard("mybatis");
		// System.out.println(boardlist); // 없으면 빈리스트
		for (HashMap map : boardlist) {
			Set<String> keys = map.keySet();
			for (String s : keys) {
				System.out.println(s + " : " + map.get(s));
			}
		}
		*/
		
	}

}
