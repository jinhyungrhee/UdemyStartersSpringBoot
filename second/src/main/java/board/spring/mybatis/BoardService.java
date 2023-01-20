package board.spring.mybatis;

import java.util.List;

public interface BoardService {
	// 게시물 리스트 서비스 - 명세서(필요한 스펙들)
	int getTotalBoard();
	List<BoardDTO> getBoardList(int limit);
	// 상세 게시물 정보
	BoardDTO getOneBoard(int seq);
	//void updateViewcount(int seq); // getOneBoard안에 위치시킴
	void insertBoard(BoardDTO dto);
}
