package namoo.board.store.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import namoo.board.comm.MyBatisUtil;
import namoo.board.comm.PoolManager;
import namoo.board.entity.Board;
import namoo.board.mapper.BoardMapper;
import namoo.board.store.BoardStore;

public class BoardMybatisStore implements BoardStore {
	
//	//sqlSession에서 getMapper 가져오기
//	MyBatisUtil util = new MyBatisUtil();
//	BoardMapper boardMapper = util.getMapper(BoardMapper.class);
	//singleton 패턴
	PoolManager pm = PoolManager.getInstance();
	SqlSession session = pm.getSession();
	BoardMapper boardMapper = session.getMapper(BoardMapper.class);
	
	@Override
	public String create(Board board) {
		boardMapper.insertBoard(board);
		session.commit();
		return null;
	}

	@Override
	public Board retrieve(String boardId) {
		Board board = boardMapper.selectBoard(boardId);
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
		List<Board> boardList = new ArrayList<Board>();
		boardList = boardMapper.selectAllBoards();
		return boardList;
	}

	@Override
	public String retrieveMaxBoardId() {
		return boardMapper.retrieveMaxBoardId();
	}

}
