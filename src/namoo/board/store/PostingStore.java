package namoo.board.store;

import java.util.List;

import namoo.board.entity.Posting;

public interface PostingStore {
    
    String create(Posting article);
    Posting retrieve(String articleId);
    List<Posting> retrieveAll(String boardId);
    void update(Posting article);
    void delete(String articleId);
}
