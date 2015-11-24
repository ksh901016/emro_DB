package namoo.board.store.mem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import namoo.board.entity.Board;
import namoo.board.entity.Posting;

public class BoardRepository {
    
    private static BoardRepository instance;
    
    private Map<String, Board> boardMap;
    private Map<String, Posting> postingMap;
    private int nextBoardId;
    private int nextPostingId;
    
    private BoardRepository() {
        //
        this.boardMap = new LinkedHashMap<String, Board>();
        this.postingMap = new HashMap<String, Posting>();
    }
    
    public synchronized static BoardRepository getInstance() {
        //
        if (instance == null) {
            instance = new BoardRepository();
        }
        return instance;
    }
    
    public String insertBoard(Board board) {
        //
        String boardId = String.valueOf(++nextBoardId);
        board.setBoardId(boardId);
        boardMap.put(boardId, board);
        
        return boardId;
    }
    
    public Board selectBoard(String boardId) {
        //
        return boardMap.get(boardId);
    }
    
    public void updateBoard(Board board) {
        //
        boardMap.put(board.getBoardId(), board);
    }
    
    public void deleteBoard(String boardId) {
        //
        boardMap.remove(boardId);
    }
    
    public String insertPosting(Posting posting) {
        //
        String postingId = String.valueOf(++nextPostingId);
        posting.setPostingId(postingId);
        postingMap.put(postingId, posting);
        
        Board board = boardMap.get(posting.getBoardId());
        board.addPosting(posting);
        
        return postingId;
    }
    
    public Posting selectPosting(String postingId) {
        //
    	//TODO
    	return null;
    }
    
    public List<Posting> selectAllPosting(String boardId) {
        //
        Board board = boardMap.get(boardId);
        if (board != null) {
            return board.getPostings();
        }
        
        return null;
    }    
    
    public void updatePosting(Posting posting) {
        //
        postingMap.put(posting.getPostingId(), posting);
    }
    
    public void deletePosting(String postingId) {
        //
        Posting posting = postingMap.get(postingId);
        
        if (posting != null) {
            //
            postingMap.remove(postingId);
            
            Board board = boardMap.get(posting.getBoardId());
            if (board != null) {
                board.removePosting(posting);
            }
        }
    }

	public List<Board> selectAllBoards() {
		//
		List<Board> boards = 
			new ArrayList<Board>(boardMap.values());
		
		return boards;
	}
}
