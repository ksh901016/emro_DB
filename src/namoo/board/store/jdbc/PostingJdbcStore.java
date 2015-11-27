package namoo.board.store.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import namoo.board.comm.JDBCUtil;
import namoo.board.entity.Board;
import namoo.board.entity.Posting;
import namoo.board.store.PostingStore;

public class PostingJdbcStore implements PostingStore {

	@Override
	public String create(Posting article) {
		String insertPostSQL = "INSERT into posting(postingId,title,authorName,regDate,contents,boardId) VALUES(?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement stmt = null;
//		String postingId = "";
//		if (retrieveMaxPostingId() != null) {
//			postingId = String.format("%04d", Integer.parseInt(retrieveMaxPostingId()) + 1);
//		} else {
//			postingId = String.format("%04d", 1);
//		}
		try {
			con = JDBCUtil.getConnection();
			stmt = con.prepareStatement(insertPostSQL);
			stmt.setString(1, article.getPostingId());
			stmt.setString(2, article.getTitle());
			stmt.setString(3, article.getAuthorName());
			stmt.setDate(4, article.getRegDate());
			stmt.setString(5, article.getContents());
			stmt.setString(6, article.getBoardId());
			stmt.executeUpdate();
			return article.getPostingId();
		} catch (Exception e) {
			System.out.println("retrieveMaxBoardId error : " + e);
		} finally {
			JDBCUtil.close(stmt, con);
		}
		return null;
	}

	@Override
	public Posting retrieve(String articleId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Posting posting = new Posting();
		String selectPostingSQL = "SELECT * FROM posting WHERE postingId=?";
		try {
			con = JDBCUtil.getConnection();
			stmt = con.prepareStatement(selectPostingSQL);
			stmt.setString(1, articleId);
			rst = stmt.executeQuery();
			while (rst.next()) {
				posting.setPostingId(rst.getString("postingId"));
				posting.setTitle(rst.getString("title"));
				posting.setAuthorName(rst.getString("authorName"));
				posting.setRegDate(rst.getDate("regDate"));
				posting.setContents(rst.getString("contents"));
				posting.setBoardId(rst.getString("boardId"));
			}
			return posting;
		} catch (Exception e) {
			System.out.println("post_retrieve error : " + e);
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rst, stmt, con);
		}
		return null;
	}

	@Override
	public List<Posting> retrieveAll(String boardId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		String selectAllPostingSQL = "SELECT * FROM posting WHERE boardId = ?";
		List<Posting> postingList = new ArrayList<Posting>();
		try {
			con = JDBCUtil.getConnection();
			stmt = con.prepareStatement(selectAllPostingSQL);
			stmt.setString(1, boardId);
			rst = stmt.executeQuery();
			while (rst.next()) {
				Posting posting = new Posting();
				posting.setAuthorName(rst.getString("authorName"));
				posting.setPostingId(rst.getString("postingId"));
				posting.setTitle(rst.getString("title"));
				posting.setRegDate(rst.getDate("regDate"));
				posting.setContents(rst.getString("contents"));
				posting.setBoardId(rst.getString("boardId"));
				postingList.add(posting);
			}
			return postingList;
		} catch (Exception e) {
			System.out.println("retrieveMaxBoardId error : " + e);
		} finally {
			JDBCUtil.close(rst, stmt, con);
		}
		return null;
	}

	@Override
	public void update(Posting article) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		String updatePostingSQL = "UPDATE posting SET title = ?, contents = ? WHERE postingId = ? ";
		try {
			con = JDBCUtil.getConnection();
			stmt = con.prepareStatement(updatePostingSQL);
			stmt.setString(1, article.getTitle());
			stmt.setString(2, article.getContents());
			stmt.setString(3, article.getPostingId());
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("retrieveMaxBoardId error : " + e);
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rst, stmt, con);
		}

	}

	@Override
	public void delete(String articleId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		String deletePostingSQL = "DELETE FROM posting WHERE postingId=?";
		try {
			con = JDBCUtil.getConnection();
			stmt = con.prepareStatement(deletePostingSQL);
			stmt.setString(1, articleId);
			stmt.executeQuery();

		} catch (Exception e) {
			System.out.println("postingDelete error : " + e);
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rst, stmt, con);
		}

	}

	@Override
	public String retrieveMaxPostingId() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		String MaxIdSQL = "SELECT max(postingId) AS postingId FROM posting ";
		try {
			con = JDBCUtil.getConnection();
			stmt = con.prepareStatement(MaxIdSQL);
			rst = stmt.executeQuery();
			String maxId = "";
			while (rst.next()) {
				maxId = rst.getString("postingId");
			}
			return maxId;
		} catch (Exception e) {
			System.out.println("retrieveMaxpostingId error : " + e);
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rst, stmt, con);
		}
		return null;
	}

}
