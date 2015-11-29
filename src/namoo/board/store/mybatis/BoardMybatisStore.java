package namoo.board.store.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import namoo.board.comm.PoolManager;
import namoo.board.entity.Board;
import namoo.board.mapper.BoardMapper;
import namoo.board.store.BoardStore;

public class BoardMybatisStore implements BoardStore {
	
//	//sqlSession에서 getMapper 가져오기
//	MyBatisUtil util = new MyBatisUtil();
//	BoardMapper boardMapper = util.getMapper(BoardMapper.class);
	//singleton 패턴
	SqlSession session = null;
	BoardMapper boardMapper = null;
	
	@Override
	public String create(Board board) {
		session = PoolManager.getInstance().getSession();
		boardMapper = session.getMapper(BoardMapper.class);
		boardMapper.insertBoard(board);
		session.commit();
		session.close();
		return null;
	}

	@Override
	public Board retrieve(String boardId) {
		session = PoolManager.getInstance().getSession();
		boardMapper = session.getMapper(BoardMapper.class);
		Board board = boardMapper.selectBoard(boardId);
		session.close();
		return board;
	}

	@Override
	public void update(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String boardId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Board> retrieveAll() {
		session = PoolManager.getInstance().getSession();
		boardMapper = session.getMapper(BoardMapper.class);
		List<Board> boardList = new ArrayList<Board>();
		boardList = boardMapper.selectAllBoards();
		session.close();
		return boardList;
	}

	@Override
	public String retrieveMaxBoardId() {
		session = PoolManager.getInstance().getSession();
		boardMapper = session.getMapper(BoardMapper.class);
		String maxBoardId = boardMapper.retrieveMaxBoardId();
		session.close();
		return maxBoardId;
	}

}
