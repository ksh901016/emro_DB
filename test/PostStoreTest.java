import namoo.board.store.BoardStore;
import namoo.board.store.mybatis.BoardMybatisStore;

public class PostStoreTest {
	//
	public static void main(String[] args){
		//
		BoardStore boardStore = new BoardMybatisStore();
		System.out.println(boardStore.retrieveAll());
		
	}

}
