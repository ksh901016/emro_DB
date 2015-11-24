package namoo.board.service.pojo;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import namoo.board.entity.Board;
import namoo.board.entity.Posting;
import namoo.board.service.BoardService;
import namoo.board.store.BoardStore;
import namoo.board.store.PostingStore;
import namoo.board.store.mem.BoardMemStore;
import namoo.board.store.mem.PostingMemStore;

public class BoardLogic implements BoardService {

    private BoardStore boardStore;
    private PostingStore postingStore;
    
    public BoardLogic() {
        //
        this.boardStore = new BoardMemStore();
        this.postingStore = new PostingMemStore();
    }
    
    @Override
    public void registerPosting(Posting posting) {
        //
        Date today = new Date(Calendar.getInstance().getTimeInMillis());
        posting.setRegDate(today);
        
        postingStore.create(posting);
    }

    @Override
    public Posting findPosting(String postingId) {
        //
        //TODO
    	return null;
    }

    @Override
    public Board findBoard(String boardId) {
        // 
        Board board = boardStore.retrieve(boardId);
        
        if (board == null) {
        	return null;
        }
        
        List<Posting> postings = postingStore.retrieveAll(boardId);
        board.setPostings(postings);
        
        return board;
    }

	@Override
	public void removePosting(String postingId) {
		//
		postingStore.delete(postingId);
	}

	@Override
	public void modifyPosting(Posting posting) {
		// 
		Posting before = 
				postingStore.retrieve(posting.getPostingId());
		
		// 제목, 내용만 교체
		before.setTitle(posting.getTitle());
		before.setContents(posting.getContents());
		
		postingStore.update(before);
	}

	@Override
	public void registerBoard(Board board) {
		// 
        Date today = new Date(Calendar.getInstance().getTimeInMillis());
        board.setCreatedDate(today);
        
		boardStore.create(board);
	}

	@Override
	public List<Board> findAllBoards() {
		// 
		return boardStore.retrieveAll();
	}
}
