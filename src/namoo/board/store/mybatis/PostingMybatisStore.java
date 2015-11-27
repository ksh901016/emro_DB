package namoo.board.store.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import namoo.board.comm.MyBatisUtil;
import namoo.board.comm.PoolManager;
import namoo.board.entity.Posting;
import namoo.board.mapper.BoardMapper;
import namoo.board.mapper.PostingMapper;
import namoo.board.store.PostingStore;

public class PostingMybatisStore implements PostingStore {
	
//	MyBatisUtil util = new MyBatisUtil();
//	PostingMapper postingMapper = util.getMapper(PostingMapper.class);
	
	//singleton 패턴
	PoolManager pm = PoolManager.getInstance();
	SqlSession session = pm.getSession();
	PostingMapper postingMapper = session.getMapper(PostingMapper.class);
	
	@Override
	public String create(Posting article) {
		postingMapper.insertPost(article);
		session.commit();
		return null;
	}

	@Override
	public Posting retrieve(String articleId) {
		Posting posting = postingMapper.selectPosting(articleId);
		return posting;
	}

	@Override
	public List<Posting> retrieveAll(String boardId) {
		List<Posting> postingList = new ArrayList<Posting>();
		postingList = postingMapper.selectAllPosting(boardId);
		return postingList;
	}

	@Override
	public void update(Posting article) {
		postingMapper.updatePosting(article);
		session.commit();
	}

	@Override
	public void delete(String articleId) {
		postingMapper.deletePosting(articleId);
		session.commit();
	}

	@Override
	public String retrieveMaxPostingId() {
		String id = postingMapper.MaxIdSQL();
		return id;
	}

}
