package namoo.board.entity;

import java.io.Serializable;
import java.sql.Date;

public class Posting implements Serializable {

    private static final long serialVersionUID = -4946269376442479040L;
    
    private String postingId;
    private String title;
    private String authorName;
    private Date regDate;
    private String contents;
    
    private String boardId;

    public String toString() {
    	StringBuilder postingContent = new StringBuilder();
    	postingContent.append(this.postingId);
    	postingContent.append("," + this.title);
    	postingContent.append("," + this.authorName);
    	postingContent.append("," + this.regDate);
    	postingContent.append("," + this.contents);
    	postingContent.append("," + this.boardId);
    	
    	return postingContent.toString();
    }
    public String getPostingId() {
        return postingId;
    }
    public void setPostingId(String postingId) {
        this.postingId = postingId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
    public String getBoardId() {
        return boardId;
    }
    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }
}
