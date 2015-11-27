package namoo.board.mapper;

import java.util.List;

import namoo.board.entity.Board;

public interface BoardMapper {
	List<Board> selectAllBoards();
	void insertBoard(Board board);
	Board selectBoard(String boardId);
	String retrieveMaxBoardId();
}
