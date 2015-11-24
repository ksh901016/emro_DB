package namoo.board.service;

import java.util.List;

import namoo.board.entity.Board;
import namoo.board.entity.Posting;

public interface BoardService {
    
    /**
     * 게시글 등록하기
     * 
     * @param Posting
     */
    void registerPosting(Posting Posting);
    
    /**
     * 게시글 조회하기
     * 
     * @param PostingId
     * @return
     */
    Posting findPosting(String PostingId);
    
    /**
     * 게시판 조회 (등록된 게시글 목록조회)
     * 
     * @param boardId
     * @return
     */
    Board findBoard(String boardId);

    /**
     * 게시물 삭제
     * 
     * @param PostingId
     */
	void removePosting(String PostingId);

	/**
	 * 게시물 수정
	 * 
	 * @param Posting
	 */
	void modifyPosting(Posting Posting);

	/**
	 * 게시판 등록
	 * 
	 * @param board
	 */
	void registerBoard(Board board);

	/**
	 * 게시판 목록
	 * 
	 * @return
	 */
	List<Board> findAllBoards();

}
