package cafe.jjdev.ajaxcrud.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.ajaxcrud.member.vo.Member;

@Mapper
public interface MemberMapper {
	// totalRow
	public int selectMemberCount();
<<<<<<< HEAD
	public List<Member> selectMemberList(int BEGIN_ROW, int ROW_PER_PAGE);
=======
	public List<Member> selectMemberList(int beginRow, int ROW_PER_PAGE);
>>>>>>> refs/remotes/AjaxCRUD/master
	public int insertMember(Member member);
	public int deleteMember(Member member);
	public int updateMember(Member member);
}
