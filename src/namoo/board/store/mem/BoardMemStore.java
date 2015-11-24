package namoo.board.store.mem;

import java.util.List;

import namoo.board.entity.Board;
import namoo.board.store.BoardStore;

public class BoardMemStore implements BoardStore {

    private BoardRepository repo;
    
    public BoardMemStore() {
        //
        this.repo = BoardRepository.getInstance();
    }
    
    @Override
    public String create(Board board) {
        //
        return repo.insertBoard(board);
    }

    @Override
    public Board retrieve(String boardId) {
        //
        return repo.selectBoard(boardId);
    }

    @Override
    public void update(Board board) {
        //
        repo.updateBoard(board);
    }

    @Override
    public void delete(String boardId) {
        //
        repo.deleteBoard(boardId);
    }

	@Override
	public List<Board> retrieveAll() {
		// 
		return repo.selectAllBoards();
	}
}
