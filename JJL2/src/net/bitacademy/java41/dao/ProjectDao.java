package net.bitacademy.java41.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.bitacademy.java41.util.DBConnectionPool;
import net.bitacademy.java41.vo.Project;

public class ProjectDao {
	DBConnectionPool conPool;
	
	public ProjectDao(DBConnectionPool conPool) {
		this.conPool = conPool;
	}
	
	public Project getProject(int pno) throws Exception {
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"select * from SPMS_PRJS where PNO = "+ pno + ";");
			rs = stmt.executeQuery();
			if (rs.next()) {
				
				Project project = new Project();
				project.setPno(rs.getInt("PNO"));
				project.setTitle(rs.getString("TITLE"));
				project.setContent(rs.getString("CONTENT"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setTag(rs.getString("TAG"));
				return project;
			}else{
				return null;
			}
			
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
		
	}
	
	
	
	
	public ArrayList<Project> getProject(String email) throws Exception {
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"select * from SPMS_MEMBS, SPMS_PRJMEMB, SPMS_PRJS where SPMS_MEMBS.EMAIL = SPMS_PRJMEMB.EMAIL and SPMS_PRJMEMB.PNO = SPMS_PRJS.PNO and SPMS_MEMBS.EMAIL = '"+ email + "';"); // ? -> in-parameter
			rs = stmt.executeQuery();
			Project project = null;
			ArrayList<Project> list = new ArrayList<Project>();
			
			while (rs.next()) {
				
				project = new Project();
				project.setPno(rs.getInt("PNO"));
				project.setTitle(rs.getString("TITLE"));
				project.setContent(rs.getString("CONTENT"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setTag(rs.getString("TAG"));
				project.setLevel(rs.getInt("LEVEL"));
				list.add(project);
			} 
			
			return list;
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
		
	}
	
	public ArrayList<Project> getProject() throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"select * from SPMS_PRJS"); // ? -> in-parameter
			rs = stmt.executeQuery();
			Project project = null;
			ArrayList<Project> list = new ArrayList<Project>();
			
			while (rs.next()) {
				
				project = new Project();
				project.setPno(rs.getInt("PNO"));
				project.setTitle(rs.getString("TITLE"));
				project.setContent(rs.getString("CONTENT"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setTag(rs.getString("TAG"));
				list.add(project);
			} 
			
			return list;
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}		
	}
	/*
	public int add(Project project) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"insert into SPMS_MEMBS("
				+ " EMAIL,MNAME,PWD,TEL,"
				+ " BLOG,REG_DATE,UPDATE_DATE,DET_ADDR,TAG)"
				+ " values(?,?,?,?,?,now(),now(),?,?)");
			stmt.setString(1, project.getEmail());
			stmt.setString(2, project.getName());
			stmt.setString(3, project.getPassword());
			stmt.setString(4, project.getTel());
			stmt.setString(5, project.getBlog());
			stmt.setString(6, project.getDetailAddress());
			stmt.setString(7, project.getTag());
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}
/*
	public List<Project> list() throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Project> list = new ArrayList<Project>();

		try {
			con = conPool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(
					"select MNAME,PHONE,EMAIL from MEMBERS order by MNAME");
			
			Project m = null;
			while(rs.next()) {
				m = new Project();
				m.setName(rs.getString("MNAME"));
				m.setPhone(rs.getString("PHONE"));
				m.setEmail(rs.getString("EMAIL"));
				list.add(m);
			}
			
			return list;
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}

	

	public Project get(String email) throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(
					"select MNAME,PHONE,EMAIL,BLOG,AGE,REG_DATE"
					+ " from MEMBERS"
					+ " where EMAIL='" + email + "'");
			
			if (rs.next()) {
				Project project = new Project();
				project.setName(rs.getString("MNAME"));
				project.setPhone(rs.getString("PHONE"));
				project.setEmail(rs.getString("EMAIL"));
				project.setBlog(rs.getString("BLOG"));
				project.setAge(rs.getInt("AGE"));
				project.setRegDate(rs.getDate("REG_DATE"));
				return project;
				
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}

	public int change(Project project) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.prepareStatement(
				"update MEMBERS set"
				+ " MNAME=?,PHONE=?,BLOG=?,AGE=?,REG_DATE=now()"
				+ " where EMAIL=?");
			stmt.setString(1, project.getName());
			stmt.setString(2, project.getPhone());
			stmt.setString(3, project.getBlog());
			stmt.setInt(4, project.getAge());
			stmt.setString(5, project.getEmail());
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}

	public int remove(String email) throws Exception {
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = conPool.getConnection();
			stmt = con.createStatement();
			
			return stmt.executeUpdate(
				"delete from MEMBERS"
				+ " where EMAIL='" + email + "'");
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {stmt.close();} catch(Exception e) {}
			if (con != null) {
				conPool.returnConnection(con);
			}
		}
	}
*/

	
}




