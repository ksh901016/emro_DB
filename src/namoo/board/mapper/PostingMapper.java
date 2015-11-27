package namoo.board.mapper;

import java.util.List;

import namoo.board.entity.Posting;

public interface PostingMapper {
	void insertPost(Posting article);
	Posting selectPosting(String articleId);
	List<Posting> selectAllPosting(String boardId);
	void updatePosting(Posting article);
	void deletePosting(String articleId);
	String MaxIdSQL();
}
