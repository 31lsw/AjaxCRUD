package cafe.jjdev.ajaxcrud.member.controller;

import java.util.Map;
import java.util.List; // 수업 초반에 데이터 담아올 때 Map까지 필요 없었을 때

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; // 설명하려다 지금 sts에 기능에서는 굳이 안써도 자동적으로 처리되서 설명 x. 나중에 써볼 것
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import cafe.jjdev.ajaxcrud.member.service.MemberService;
import cafe.jjdev.ajaxcrud.member.vo.Member;

@RestController
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	// 멤버 목록
	@GetMapping("/getMembers")
	public Map<String, Object> getMembers(@RequestParam( value="currentPage", defaultValue="1") int currentPage) {
		System.out.println("/getMembers 요청 [GET]");
		System.out.println("MemberController.getMembers currentPage : "+currentPage);
		Map<String, Object> map = memberService.getMemberList(currentPage);
		System.out.println("[return] memberService.getMemberList(currentPage) map : "+map);
		System.out.println(map.get("currentPage") + " : currentPage");
		System.out.println(map.get("lastPage") + " : lastPage");
		return map;
		
	}
	
	// 멤버 추가
	@PostMapping("/addMember")
	public void addMember(Member member) {
		System.out.println("/addMember 요청 [POST]");
		System.out.println("MemberController.addMember member : "+member);
		memberService.addMember(member);
	}
	
	// 멤버 삭제
	@PostMapping("/removeMember") //@GetMapping("/removeMember") -> GetMapping은 배열객체를 받을 수 없다
	public void removeMember(@RequestParam(value="ck[]") String[] ck) { // spring에서는 List도 자동으로 배열로 변환해준다 : List<String> ck
		System.out.println("/removeMember 요청 [POST]");
		System.out.println("MemberController.addMember ck : "+ck);
		System.out.println("MemberController.addMember ck[0] : "+ck[0]);
		memberService.removeMember(ck);
	}
	
	// 멤버 수정
	@PostMapping("/modifyMember")
	public void modifyMember(Member member) {
		System.out.println("/modifyMember 요청 [POST]");
		System.out.println("MemberController.modifyMember member : "+member);
		memberService.modifyMember(member);
	}
}
