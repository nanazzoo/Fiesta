package edu.kh.fiesta.main.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.fiesta.main.model.dao.MainDAO;
import edu.kh.fiesta.main.model.vo.Board;
import edu.kh.fiesta.main.model.vo.Pagination;
import edu.kh.fiesta.main.model.vo.Report;
import edu.kh.fiesta.member.model.vo.Member;

@Service
public class MainServiceImpl implements MainService {
	
	@Autowired
	private MainDAO dao;
	
	
		
	
	@Override
	public Map<String, Object> selectBoardList(int memberNo) {
		
		// 추천 멤버 목록 조회
		List<Member> accountList = dao.selectMember(memberNo);
		
		// 조회할 게시글의 수 조회
		int listCount = dao.getListCount(memberNo);	
		
		// 페이지네이션 객체 생성
		Pagination pagination = new Pagination(listCount, 1);
		
		// 게시물 목록 조회
		List<Board> boardList = dao.selectBoardList(pagination, memberNo);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		map.put("accountList", accountList);
		
		
		return map;
	}
	

	@Override
	public Map<String, Object> selectBoardList(int memberNo, int cp) {
		
		int listCount = dao.getListCount(memberNo);		
		
		Pagination pagination = new Pagination(listCount, cp);
		
		if(cp <= pagination.getMaxPage()) {
			
			List<Board> boardList = dao.selectBoardList(pagination, memberNo);
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("pagination", pagination);
			map.put("boardList", boardList);
			
			return map;
		} else {
			return null;
		}
		
	}
	
	
	@Override
	public int boardLikeUp(int boardNo, int memberNo) {
		return dao.boardLikeUp(boardNo, memberNo);
	}

	@Override
	public int boardLikeDown(int boardNo, int memberNo) {
		return dao.boardLikeDown(boardNo, memberNo);
	}
	
	/** 寃뚯떆湲� 遺곷쭏�겕 異붽�
	 *
	 */
	@Override
	public int boardBookmarkOn(int boardNo, int memberNo) {
		return dao.boardBookmarkOn(boardNo, memberNo);
	}
	
	/** 寃뚯떆湲� 遺곷쭏�겕 �빐�젣
	 *
	 */
	@Override
	public int boardBookmarkOff(int boardNo, int memberNo) {
		return dao.boardBookmarkOff(boardNo, memberNo);
	}


	/**
	 * 寃뚯떆湲� �궘�젣
	 * @param boardNo
	 * @return result
	 */
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}
	
	/** �떊怨� �궫�엯
	 *
	 */
	@Override
	public int insertReport(Report report) {
		return dao.insertReport(report);
	}

}
