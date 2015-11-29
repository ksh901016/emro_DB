package namoo.board.store.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import namoo.board.comm.PoolManager;
import namoo.board.entity.Posting;
import namoo.board.mapper.PostingMapper;
import namoo.board.store.PostingStore;

public class PostingMybatisStore implements PostingStore {
	
//	MyBatisUtil util = new MyBatisUtil();
//	PostingMapper postingMapper = util.getMapper(PostingMapper.class);
	
	//singleton 패턴
	SqlSession session = null;
	PostingMapper postingMapper = null;
	
	@Override
	public String create(Posting article) {
		postingMapper = getPostingMapper();
		postingMapper.insertPost(article);
		session.commit();
		session.close();
		return null;
	}

	@Override
	public Posting retrieve(String articleId) {
		postingMapper = getPostingMapper();
		Posting posting = postingMapper.selectPosting(articleId);
		session.close();
		return posting;
	}

	@Override
	public List<Posting> retrieveAll(String boardId) {
		postingMapper = getPostingMapper();
		List<Posting> postingList = new ArrayList<Posting>();
		postingList = postingMapper.selectAllPosting(boardId);
		session.close();
		return postingList;
	}

	@Override
	public void update(Posting article) {
		postingMapper = getPostingMapper();
		postingMapper.updatePosting(article);
		session.commit();
		session.close();
	}

	@Override
	public void delete(String articleId) {
		postingMapper = getPostingMapper();
		postingMapper.deletePosting(articleId);
		session.commit();
		session.close();
	}

	@Override
	public String retrieveMaxPostingId() {
		postingMapper = getPostingMapper();
		String id = postingMapper.MaxIdSQL();
		session.close();
		return id;
	}
	
	public PostingMapper getPostingMapper(){
		this.session = PoolManager.getInstance().getSession();
		return session.getMapper(PostingMapper.class);
	}

}
