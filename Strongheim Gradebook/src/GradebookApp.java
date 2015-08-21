// Import required java libraries

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Extend HttpServlet class
@WebServlet("/GradebookApp")
public class GradebookApp extends HttpServlet {
	static Connection conn = null;

	private String message = "";

	public void init() throws ServletException {
		// Do required initialization

		openConnection();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String button = request.getParameter("submit");
		System.out.println(button);
		if (button == null) {
		} else {
			// read form fields
			String studentId = "";
			String assignment = "";
			String type = "";
			String date = "";
			String grade = "";
			studentId = request.getParameter("studentId");
			assignment = request.getParameter("assignment");
			type = request.getParameter("type");
			date = request.getParameter("date");
			grade = request.getParameter("grade");
			
			String sql = "INSERT INTO shgradebook (studentId,assignment,assignmentType,assignmentDate,grade) values('"+studentId+"','"+assignment+"','"+type+"',TO_Date('"+date+"','yyyy-mm-dd'),"+grade+")";
		System.out.println(sql);
			try {
				updateDB(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		getServletContext().getRequestDispatcher("/gradebookapp.jsp").forward(
				request, response);
	}

	public void destroy() {
		// do nothing.
	}

	public static void openConnection() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:testuser/password@localhost";
			Properties props = new Properties();
			props.setProperty("user", "testdb");
			props.setProperty("password", "password");
			conn = DriverManager.getConnection(url, props);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateDB(String sql) throws SQLException {

		PreparedStatement preStatement = conn.prepareStatement(sql);

		preStatement.setQueryTimeout(10);
		preStatement.executeUpdate();

	}

	public static ResultSet getFromDB(String sql) throws SQLException {

		PreparedStatement preStatement = conn.prepareStatement(sql);
		ResultSet result = preStatement.executeQuery();
		return result;
	}
}