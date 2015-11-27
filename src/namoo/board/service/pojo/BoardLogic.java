package namoo.board.service.pojo;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import namoo.board.entity.Board;
import namoo.board.entity.Posting;
import namoo.board.service.BoardService;
import namoo.board.store.BoardStore;
import namoo.board.store.PostingStore;
import namoo.board.store.jdbc.BoardJdbcStore;
import namoo.board.store.jdbc.PostingJdbcStore;
import namoo.board.store.mem.BoardMemStore;
import namoo.board.store.mem.PostingMemStore;
import namoo.board.store.mybatis.BoardMybatisStore;
import namoo.board.store.mybatis.PostingMybatisStore;

public class BoardLogic implements BoardService {

	private BoardStore boardStore;
	private PostingStore postingStore;

	public BoardLogic() {
		//
		this.boardStore = new BoardMybatisStore();
		this.postingStore = new PostingMybatisStore();
	}

	@Override
	public void registerPosting(Posting posting) {
		//
		Date today = new Date(Calendar.getInstance().getTimeInMillis());
		posting.setRegDate(today);
		String postingId = "";
		if (postingStore.retrieveMaxPostingId() != null) {
			postingId = String.format("%04d", (Integer.parseInt(postingStore.retrieveMaxPostingId())) + 1);
		} else {
			postingId = String.format("%04d", 1);
		}
		posting.setPostingId(postingId);

		postingStore.create(posting);
	}

	@Override
	public Posting findPosting(String postingId) {

		if (postingStore.retrieve(postingId) != null) {
			return postingStore.retrieve(postingId);
		} else {
			return null;
		}
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
		Posting before = postingStore.retrieve(posting.getPostingId());

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
		String boardId = "";
		if (boardStore.retrieveMaxBoardId() != null) {
			boardId = String.format("%04d", Integer.parseInt(boardStore.retrieveMaxBoardId()) + 1);
		} else {
			boardId = String.format("%04d", 1);
		}
		board.setBoardId(boardId);

		boardStore.create(board);
	}

	@Override
	public List<Board> findAllBoards() {
		//
		return boardStore.retrieveAll();
	}
}
