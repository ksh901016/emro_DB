package namoo.board.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {

    private static final long serialVersionUID = -7755173628930202505L;
    
    private String boardId;
    private String name;
    private Date createdDate;
    private String creatorName;
    private List<Posting> postings;

    public String getBoardId() {
        return boardId;
    }
    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public String getCreatorName() {
        return creatorName;
    }
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
    public List<Posting> getPostings() {
        return postings;
    }
    public void setPostings(List<Posting> postings) {
        this.postings = postings;
    }
    
    public void removePosting(Posting posting) {
        //
        if (postings != null) {
            postings.remove(posting);
        }
    }
    
    public void addPosting(Posting posting) {
        //
        if (postings == null) {
            postings = new ArrayList<Posting>();
        }
        
        postings.add(posting);
    }
    
    public String toString() {
    	StringBuilder boardContent = new StringBuilder();
    	boardContent.append(this.boardId);
    	boardContent.append("," + this.name);
    	boardContent.append("," + this.createdDate);
    	boardContent.append("," + this.creatorName);
    	
    	return boardContent.toString();
    }
}
