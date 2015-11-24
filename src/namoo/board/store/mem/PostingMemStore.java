package namoo.board.store.mem;

import java.util.List;

import namoo.board.entity.Posting;
import namoo.board.store.PostingStore;

public class PostingMemStore implements PostingStore {

    private BoardRepository repo;
    
    public PostingMemStore() {
        //
        this.repo = BoardRepository.getInstance();
    }
    
    @Override
    public String create(Posting posting) {
        //
        return repo.insertPosting(posting);
    }

    @Override
    public Posting retrieve(String postingId) {
        // 
    	//TODO
    	return null;
    }

    @Override
    public List<Posting> retrieveAll(String boardId) {
        // 
        return repo.selectAllPosting(boardId);
    }

    @Override
    public void update(Posting posting) {
        // 
        repo.updatePosting(posting);
    }

    @Override
    public void delete(String postingId) {
        //
        repo.deletePosting(postingId);
    }

}
