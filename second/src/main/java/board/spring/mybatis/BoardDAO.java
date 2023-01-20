package board.spring.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

// mapper 형태로 DAO 만들 때에는 Interface로 설정!
@Mapper
@Repository
public interface BoardDAO {
	
	// 동일한 이름의 sql 실행
	// 게시물리스트
	int getTotalBoard(); 
	List<BoardDTO> getBoardList(int limit);
	// 상세 게시물 조회
	BoardDTO getOneBoard(int seq);
	void updateViewcount(int seq);
	void insertBoard(BoardDTO dto);

}
