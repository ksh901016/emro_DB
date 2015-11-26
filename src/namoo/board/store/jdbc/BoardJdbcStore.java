package namoo.board.store.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import namoo.board.comm.JDBCUtil;
import namoo.board.entity.Board;
import namoo.board.store.BoardStore;

public class BoardJdbcStore implements BoardStore {

	@Override
	public String create(Board board) {
		String insertBoardSQL = "INSERT into board(boardId,name,createdDate,creatorName) VALUES(?,?,?,?)";
		Connection con = null;
		PreparedStatement stmt = null;
//		String boardId = "";
//		if (retrieveMaxBoardId() != null) {
//			boardId = String.format("%04d", Integer.parseInt(retrieveMaxBoardId()) + 1);
//		} else {
//			boardId = String.format("%04d", 1);
//		}
		try {
			con = JDBCUtil.getConnection();
			stmt = con.prepareStatement(insertBoardSQL);
			stmt.setString(1, board.getBoardId());
			stmt.setString(2, board.getName());
			stmt.setDate(3, board.getCreatedDate());
			stmt.setString(4, board.getCreatorName());
			stmt.executeUpdate();
			return board.getBoardId();
		} catch (Exception e) {
			System.out.println("retrieveMaxBoardId error : " + e);
		} finally {
			JDBCUtil.close(stmt, con);
		}
		return null;
	}

	@Override
	public Board retrieve(String boardId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		String selectBoardSQL = "SELECT * FROM board WHERE boardId = ? ";
		try {
			Board board = new Board();
			con = JDBCUtil.getConnection();
			stmt = con.prepareStatement(selectBoardSQL);
			stmt.setString(1, boardId);
			rst = stmt.executeQuery();
			while (rst.next()) {
				board.setBoardId(rst.getString("boardId"));
				board.setName(rst.getString("name"));
				board.setCreatedDate(rst.getDate("createdDate"));
				board.setCreatorName(rst.getString("creatorName"));
			}
			return board;
		} catch (Exception e) {
			System.out.println("retrieve error : " + e);
		} finally {
			JDBCUtil.close(rst, stmt, con);
		}
		return null;
	}

	@Override
	public void update(Board board) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String boardId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Board> retrieveAll() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		String selectAllBoardsSQL = "SELECT * FROM board";
		List<Board> boardList = new ArrayList<Board>();
		try {
			con = JDBCUtil.getConnection();
			stmt = con.prepareStatement(selectAllBoardsSQL);
			rst = stmt.executeQuery();
			while (rst.next()) {
				Board board = new Board();
				board.setBoardId(rst.getString("boardId"));
				board.setName(rst.getString("name"));
				board.setCreatedDate(rst.getDate("createdDate"));
				board.setCreatorName(rst.getString("creatorName"));
				boardList.add(board);
			}
			return boardList;
		} catch (Exception e) {
			System.out.println("board_retrieveAll error : " + e);
		} finally {
			JDBCUtil.close(rst, stmt, con);
		}
		return null;
	}

	@Override
	public String retrieveMaxBoardId() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		String MaxIdSQL = "SELECT max(boardId) AS boardId FROM board ";
		try {
			con = JDBCUtil.getConnection();
			stmt = con.prepareStatement(MaxIdSQL);
			rst = stmt.executeQuery();
			String maxId = "";
			while (rst.next()) {
				maxId = rst.getString("boardId");
			}
			return maxId;
		} catch (Exception e) {
			System.out.println("retrieveMaxBoardId error : " + e);
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rst, stmt, con);
		}
		return null;
	}

}
