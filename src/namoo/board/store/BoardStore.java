package namoo.board.store;

import java.util.List;

import namoo.board.entity.Board;

public interface BoardStore {

    String create(Board board);
    Board retrieve(String boardId);
    void update(Board board);
    void delete(String boardId);
	List<Board> retrieveAll();
}
