package edu.kh.fiesta.main.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.fiesta.main.model.dao.MainDAO;
import edu.kh.fiesta.main.model.vo.Board;
import edu.kh.fiesta.main.model.vo.Pagination;

@Service
public class MainServiceImpl implements MainService {
	
	@Autowired
	private MainDAO dao;
	
	
		
	
	@Override
	public Map<String, Object> selectBoardList(int memberNo) {
		
		int listCount = dao.getListCount(memberNo);		
		
		Pagination pagination = new Pagination(listCount, 1);
		
		List<Board> boardList = dao.selectBoardList(pagination, memberNo);
				
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		
		return map;
	}
	

	@Override
	public List<Board> selectBoardList(int memberNo, int cp) {
		
		int listCount = dao.getListCount(memberNo);		
		
		Pagination pagination = new Pagination(listCount, cp);
		
		return dao.selectBoardList(pagination, memberNo);
	}
	

	
	

}
