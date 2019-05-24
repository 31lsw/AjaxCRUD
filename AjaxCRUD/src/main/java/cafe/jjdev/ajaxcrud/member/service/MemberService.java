package cafe.jjdev.ajaxcrud.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.jjdev.ajaxcrud.member.mapper.MemberMapper;
import cafe.jjdev.ajaxcrud.member.vo.Member;

@Transactional
@Service
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;
		
	// 멤버 추가
	public void addMember(Member member) {
		System.out.println("MemberService.addMember member : "+member);
		memberMapper.insertMember(member);
	}
	
	// 멤버 삭제
	public void removeMember(String[] ck) {
		System.out.println("/removeMember 요청");
		System.out.println("MemberService.removeMember ck : "+ck);
		System.out.println("MemberService.addMember ck[0] : "+ck[0]);
		//System.out.println("MemberController.removeMember id : "+ck.length); // 이 작업은 서비스 쪽에서 이루어져야 맞다
		for(String id : ck) {
			Member member = new Member();
			member.setId(id);
			memberMapper.deleteMember(member);
		}
	}
	
	// 멤버 수정
	public void modifyMember(Member member) {
		System.out.println("MemberService.modifyMember member : "+member);
		memberMapper.updateMember(member);
	}
	
	// 멤버 목록
	public Map<String, Object> getMemberList(int currentPage){
		// 호출된 메서드명과 입력값 확인
		System.out.println("MemberService.getMemberList");
		System.out.println("MemberService.getMemberList currentPage : " + currentPage);
		
		/*
		 *	리턴해줘야 될 데이터와 타입
		 *	1. 멤버 목록 List<Member>
		 *	2.마지막 페이지 int
		 *	3.현재 페이지 int
		 */
		
		// 맵 데이터 타입 객체를 생성해 데이터 리턴해준다. 리턴해줘야 될 데이터 타입이 많아서 하나의 타입으로 가공해서 보내줘야 하니까
		Map<String, Object> map = new HashMap<String, Object>();
		
		/* 1. 멤버 목록 List<Member> */
		// 한 페이지 당 보여줄 row 갯수
		final int ROW_PER_PAGE = 10;
		// 처음 출력을 시작할 row
		int beginRow = (currentPage - 1) * ROW_PER_PAGE;
		// 멤버 목록 조회 결과 변수에 담는다
		List<Member> list = memberMapper.selectMemberList(beginRow, ROW_PER_PAGE);
		System.out.println("[MemberService] memberMapper.selectMemberList().size : "+list.size());
		// 리턴할 맵 객체에 멤버 목록 담는다
		map.put("list", list);
		
		/* 2.마지막 페이지 int */
		// 총 row 갯수 통해 마지막 페이지(전체 페이지) 구한다 
		// 총 row 갯수 조회결과
		int totalRow = memberMapper.selectMemberCount();
		// 마지막 페이지(전체 페이지)
		int lastPage = totalRow / ROW_PER_PAGE;
		if(totalRow % ROW_PER_PAGE > 0) { // 나누어 떨어지지 않을 경우 한 페이지 더 표시해서 나머지 정보도 모두 표시되도록 한다
			lastPage += 1;
		}
		// 리턴할 객체에 마지막 페이지 셋팅 
		map.put("lastPage", lastPage);
		
		/* 3.현재 페이지 int */
		// 현재 페이지가 취할 수 있는 값의 범위를 제한
		if(currentPage > lastPage) {
			currentPage = lastPage; //현재 페이지의 최대값은 마지막 페이지
		}else if(currentPage < 1) {
			currentPage = 1; //현재 페이지의 최소값은 1로 이전 버튼 연산 시 음수가 되지 않도록한다 
		}
		// 리턴할 객체에 현재 페이지 셋팅
		map.put("currentPage", currentPage);
		
		// 데이터 담은 객체 리턴하고 메서드 종료 
		return map;
	}
}
