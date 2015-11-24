package namoo.board.ui.cli;

import java.util.List;
import java.util.Scanner;

import namoo.board.entity.Board;
import namoo.board.entity.Posting;
import namoo.board.service.BoardService;

public class PostingMenu {
	
	private String boardId;
	private BoardService boardService;
	private Scanner scanner;
	
	public PostingMenu(String boardId, BoardService boardService) {
		//
		this.boardId = boardId;
		this.boardService = boardService;
		this.scanner = new Scanner(System.in);
	}
	
	public void show() {
		//
		while (true) {
            //
            System.out.println();
            System.out.println("=====================================");
            System.out.println("게시물 관리");
            System.out.println("=====================================");
            System.out.println("1. 게시물 목록");
            System.out.println("2. 게시물 조회");
            System.out.println("3. 게시물 등록");
            System.out.println("4. 게시물 삭제");
            System.out.println("5. 게시물 수정");
            System.out.println("9. 이전으로");
            System.out.println("-------------------------------------");
            System.out.print("메뉴 선택 > ");
            
            String selectNum = scanner.nextLine();
            switch(selectNum) {
            case "1": // 게시물 목록
                boolean exists = showPostingList();
                if (exists) {
                    showPostingDetail();
                } else {
                    System.out.println("등록된 게시물이 없습니다.");
                }
                break;
            case "2": // 게시물 조회
                showPostingDetail();
                break;
            case "3": // 게시물 등록
                registerPosting();
                break;
            case "4": // 게시물 삭제
            	removePosting();
            	break;
            case "5": // 게시물 수정
            	modifyPosting();
            	break;
            case "9":
                System.out.println("처음으로 이동합니다.");
                return;
            default:
                System.out.println("알 수 없는 메뉴입니다.");
            }
        }
	}
	
    private void modifyPosting() {
		// 
        System.out.println();
        System.out.println("---------------------");
        System.out.println("게시물 수정");
        System.out.println("---------------------");
        
        System.out.print("게시물 번호 : ");
        String postingId = scanner.nextLine();
        
        // 해당 게시물 제목 및 내용을 보여주기
        printPosting(postingId);
        
        System.out.print("게시물 제목 : ");
        String title = scanner.nextLine();
        
        System.out.print("게시물 내용 : ");
        String contents = scanner.nextLine();
        
        Posting posting = new Posting();
        posting.setPostingId(postingId);
        posting.setBoardId(boardId);
        posting.setTitle(title);
        posting.setContents(contents);
        
        boardService.modifyPosting(posting);
	}

	/**
     * 4. 게시물 삭제
     */
    private void removePosting() {
		// 
        System.out.println();
        System.out.println("---------------------");
        System.out.println("게시물 삭제");
        System.out.println("---------------------");	
		
        System.out.print("게시물 번호 : ");
        String postingId = scanner.nextLine();
        
        boardService.removePosting(postingId);
        System.out.println("게시물이 삭제되었습니다.");
	}

	/**
     * 1. 게시물 목록
     * @return
     */
    private boolean showPostingList() {
        //
        System.out.println();
        System.out.println("---------------------");
        System.out.println("게시물 목록");
        System.out.println("---------------------");
        
        Board board = boardService.findBoard(boardId);
        List<Posting> postings = board.getPostings();
        if (postings != null && !postings.isEmpty()) {
            
            for (Posting posting : postings) {
                System.out.printf("%s. %s (%s) \n",
                    posting.getPostingId(),
                    posting.getTitle(),
                    posting.getAuthorName()
                );
            }
            return true;
        }
        
        return false;
    }
    
    /**
     * 2. 게시물 조회
     */
    private void showPostingDetail() {
        //
        System.out.println();
        System.out.println("---------------------");
        System.out.println("게시물 조회");
        System.out.println("---------------------");
        
        System.out.print("게시물 번호 : ");
        String postingId = scanner.nextLine();
        
        printPosting(postingId);
    }

	private void printPosting(String postingId) {
		//
		Posting posting = boardService.findPosting(postingId);
        if (posting != null) {
            System.out.println(" 게시물 번호 : " + posting.getPostingId());
            System.out.println(" 게시물 제목 : " + posting.getTitle());
            System.out.println(" 작성자 이름 : " + posting.getAuthorName());
            System.out.println(" 게시물 내용 : " + posting.getContents());
        } else {
            System.out.println("해당 게시물이 없습니다.");
        }
	}
    
    /**
     * 3. 게시물 등록
     */
    private void registerPosting() {
        //
        System.out.println();
        System.out.println("---------------------");
        System.out.println("게시물 등록");
        System.out.println("---------------------");
        
        System.out.print("게시물 제목 : ");
        String title = scanner.nextLine();
        
        System.out.print("작성자 이름 : ");
        String authorName = scanner.nextLine();
        
        System.out.print("게시물 내용 : ");
        String contents = scanner.nextLine();
        
        Posting posting = new Posting();
        posting.setBoardId(boardId);
        posting.setTitle(title);
        posting.setAuthorName(authorName);
        posting.setContents(contents);
        
        boardService.registerPosting(posting);
    }	

}
