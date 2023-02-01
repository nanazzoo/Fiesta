package edu.kh.fiesta.main.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.fiesta.main.model.vo.Board;
import edu.kh.fiesta.main.model.vo.Pagination;
import edu.kh.fiesta.main.model.vo.Report;
import edu.kh.fiesta.member.model.vo.Member;


@Repository
public class MainDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;


	/**
	 * 게시물 수 조회
	 * @param memberNo
	 * @return listCount
	 */
	public int getListCount(int memberNo) {
		
		return sqlSession.selectOne("mainMapper.getListCount", memberNo);
	}
	
	

	/**게시물 목록 조회 DAO
	 * @param pagination
	 * @param memberNo
	 * @return boardList
	 */
	public List<Board> selectBoardList(Pagination pagination, int memberNo) {
		
		// 조회해 올 게시물의 시작 번호 
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		// 조회해 올 행의 영역을 설정하는 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
	
		return sqlSession.selectList("mainMapper.selectBoardList", memberNo, rowBounds);
	}


	/** 寃뚯떆湲� 醫뗭븘�슂 利앷�
	 * @param boardNo
	 * @param memberNo
	 * @return result
	 */
	public int boardLikeUp(int boardNo, int memberNo) {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("boardNo", boardNo);
		map.put("memberNo", memberNo);
		
		int result = sqlSession.insert("mainMapper.boardLikeUp", map);
		
		if(result > 0) {
			result = sqlSession.selectOne("mainMapper.likeCount", map);
		}
		
		return result;
	}


	/** 寃뚯떆湲� 醫뗭븘�슂 媛먯냼
	 * @param boardNo
	 * @param memberNo
	 * @return result
	 */
	public int boardLikeDown(int boardNo, int memberNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("boardNo", boardNo);
		map.put("memberNo", memberNo);
		
		int result = sqlSession.delete("mainMapper.boardLikeDown", map);
		
		if(result > 0) {
			result = sqlSession.selectOne("mainMapper.likeCount", map);
		}
		
		return result;
	}


	/** 寃뚯떆湲� 遺곷쭏�겕 異붽�
	 * @param boardNo
	 * @param memberNo
	 * @return result
	 */
	public int boardBookmarkOn(int boardNo, int memberNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("boardNo", boardNo);
		map.put("memberNo", memberNo);
		
		return sqlSession.insert("mainMapper.boardBookmarkOn", map);
	}
	
	/** 寃뚯떆湲� 遺곷쭏�겕 �빐�젣
	 * @param boardNo
	 * @param memberNo
	 * @return result
	 */
	public int boardBookmarkOff(int boardNo, int memberNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("boardNo", boardNo);
		map.put("memberNo", memberNo);
		
		return sqlSession.delete("mainMapper.boardBookmarkOff", map);
	}

	
	/**
	 * 寃뚯떆湲� �궘�젣
	 * @param boardNo
	 * @return result
	 */
	public int deleteBoard(int boardNo) {
		return sqlSession.update("mainMapper.deleteBoard", boardNo);
	}


	/** �떊怨� �궫�엯
	 * @param report
	 * @return
	 */
	public int insertReport(Report report) {
		return sqlSession.insert("mainMapper.insertReport", report);
	}

	
	public List<Member> selectMember(int memberNo) {
		
		return sqlSession.selectList("mainMapper.selectMemberList", memberNo);
	}



}
