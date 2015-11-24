package namoo.board.ui.cli;

import java.util.List;
import java.util.Scanner;

import namoo.board.entity.Board;
import namoo.board.service.BoardService;
import namoo.board.service.pojo.BoardLogic;

public class BoardCLI {

    private BoardService boardService;
    private Scanner scanner;
    
    public BoardCLI() {
        //
        this.boardService = new BoardLogic();
        this.scanner = new Scanner(System.in);
    }
    
    public void launch() {
        //
        while(true) {
        	System.out.println("===========================");
        	System.out.println("NamooBoard ver 1.0");
        	System.out.println("===========================");
        	System.out.println("1. 게시판 목록");
        	System.out.println("2. 게시판 등록");
        	System.out.println("3. 게시판 삭제");
        	System.out.println("9. 종료");
        	System.out.println("---------------------------");
        	System.out.print("메뉴선택 > ");
        	
        	String selectedNum = scanner.nextLine();
        	switch(selectedNum) {
        	case "1": // 게시판 목록
        		showBoardList();
        		break;
        	case "2": // 게시판 등록
        		registerBoard();
        		break;
        	case "9": // 종료
        		System.out.println("프로그램을 종료합니다.");
        		return;
    		default:
        		System.out.println("올바른 메뉴를 선택하세요.");
        	}
        }
    }

    private void showBoardList() {
		// 
    	List<Board> boards = boardService.findAllBoards();
    	
    	if (boards != null && !boards.isEmpty()) {
    		
    		for (Board board : boards) {
    			// 1. 자유게시판
    			// 2. 질의응답 게시판
    			System.out.printf("%s. %s\n", 
					board.getBoardId(),
					board.getName()
				);
    		}
    		
    		System.out.print("게시판 선택 > ");
    		String boardId = scanner.nextLine();
    		
    		if (boardService.findBoard(boardId) != null) {
    			//
    			PostingMenu menu = 
					new PostingMenu(boardId, boardService);
    			menu.show();
    			
    		} else {
    			System.out.println("없는 게시판입니다.");
    		}
    		
    	} else {
    		System.out.println("등록된 게시판이 없습니다.");
    	}
		
	}

	private void registerBoard() {
		// 
    	System.out.println("게시판을 생성합니다.");
    	System.out.print("게시판 이름 : ");
    	String boardName = scanner.nextLine();
    	
    	System.out.print("등록자 이름 : ");
    	String creatorName = scanner.nextLine();
    	
    	Board board = new Board();
    	board.setName(boardName);
    	board.setCreatorName(creatorName);
    	
		boardService.registerBoard(board);
	}

	public static void main(String[] args) {
        // 
        new BoardCLI().launch();
    }

}
