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
@WebServlet("/SearchGradebook")
public class SearchGradebook extends HttpServlet {
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

		String button = request.getParameter("search2");
		// System.out.println(button);
		if (button == null) {
		} else {
			// read form fields
			String studentId = "";
			String type = "";
			String searchmode = "";
			String className = "";
			studentId = request.getParameter("studentsearch");
			type = request.getParameter("typesearch");
			searchmode = request.getParameter("searchmode");
			className = request.getParameter("class");
			String sql = "";

			if (searchmode.equals("allbystudent")) {
				sql = "select studentId, className, assignment,assignmentType,assignmentDate,grade from shgradebook where studentId = '"
						+ studentId + "' and className = '" + className + "'";
				// System.out.println(sql);
			} else if (searchmode.equals("typebyall")) {
				sql = "select studentId, className, assignment,assignmentType,assignmentDate,grade from shgradebook where assignmentType = '"
						+ type + "' and className = '" + className + "'";
			} else if (searchmode.equals("typebystudent")) {
				sql = "select studentId, className, assignment,assignmentType,assignmentDate,grade from shgradebook where assignmentType = '"
						+ type
						+ "' and studentId = '"
						+ studentId
						+ "'"
						+ " and className = '" + className + "'";
			} else if (searchmode.equals("avgbystudent")) {
				sql = "select studentId, className, assignment,assignmentType,assignmentDate,grade from shgradebook where studentId = '"
						+ studentId + "' and className = '" + className + "'";
			} else if (searchmode.equals("avgbyassignment")) {
				sql = "select studentId, className, assignment,assignmentType,assignmentDate,grade from shgradebook where assignmentType ='"
						+ type
						+ "' and studentId ='"
						+ studentId
						+ "'"
						+ " and className = '" + className + "'";
			} else if (searchmode.equals("highandlow")) {
				sql = "select studentId, className, assignment,assignmentType,assignmentDate,grade from shgradebook where assignmentType ='"
						+ type
						+ "' and studentId ='"
						+ studentId
						+ "'"
						+ " and className = '" + className + "'";
			}

			ArrayList<String> studentIdList = new ArrayList<String>();
			ArrayList<String> assignmentList = new ArrayList<String>();
			ArrayList<String> assignmentTypeList = new ArrayList<String>();
			ArrayList<String> assignmentDateList = new ArrayList<String>();
			ArrayList<String> gradeList = new ArrayList<String>();
			ArrayList<String> classList = new ArrayList<String>();
			String assignment = "";
			String assignmentDate = "";
			String grade = "";

			System.out.println(sql);

			try {
				ResultSet result;
				result = getFromDB(sql);

				while (result.next()) {
					studentId = result.getString("studentId");
					assignment = result.getString("assignment");
					type = result.getString("assignmentType");
					assignmentDate = result.getString("assignmentDate");
					grade = result.getString("grade");
					className = result.getString("className");
					studentIdList.add(studentId);
					assignmentList.add(assignment);
					assignmentTypeList.add(type);
					assignmentDateList.add(assignmentDate);
					gradeList.add(grade);
					classList.add(className);

				}
				// System.out.println(assignment);
				message += "<div class=\"container\"><h2>Gradebook</h2><p> History</p> "
						+ "<table class= \"table\"><thead><tr><th>Student Id</th><th>Class</th><th>Assignment</th><th>Type</th><th>Date</th><th>Grade</th></tr></thead><tbody>";
				int total = 0;
				int homeworktotal = 0;
				int projecttotal = 0;
				int testtotal = 0;
				int quiztotal = 0;
				int hw = 0, p = 0, q = 0 , t = 0;
				
				for (int i = 0; i < assignmentList.size(); i++) {
					studentId = studentIdList.get(i);
					assignment = assignmentList.get(i);
					type = assignmentTypeList.get(i);
					assignmentDate = assignmentDateList.get(i);
					grade = gradeList.get(i);
					if (type.equals("Homework")) {
						homeworktotal += Integer.parseInt(grade);
						hw++;
					} else if (type.equals("Project")) {
						projecttotal += Integer.parseInt(grade);
						p++;
					} else if (type.equals("Test")) {
						testtotal += Integer.parseInt(grade);
						t++;
					} else if (type.equals("Quiz")) {
						quiztotal += Integer.parseInt(grade);
						q++;
					}

					total += Integer.parseInt(grade);
					message += "<tr><td>" + studentId + "</td><td>"
							+ "</td><td>" + className + "</td><td>"
							+ assignment + "</td><td>" + type + "</td><td>"
							+ assignmentDate + "</td><td>" + grade
							+ "</td></tr>";

				}
		
				try {

					String sqlavg = "SELECT * from gradeweights ";
					ResultSet result2;
					result2 = getFromDB(sqlavg);
					String homeworkweight = "";
					String projectweight = "";
					String testweight = "";
					String quizweight = "";

					while (result2.next()) {

						homeworkweight = result2.getString("homeworkweight");
						projectweight = result2.getString("projectweight");
						testweight = result2.getString("testweight");
						quizweight = result2.getString("quizweight");
					}

					int parsehwweight =Integer.parseInt(homeworkweight);
					int parsepwweight =Integer.parseInt(projectweight);
					int parsetesteweight =Integer.parseInt(testweight);
					int parsequizweight =Integer.parseInt(quizweight);
					total = (homeworktotal/hw) * parsehwweight + parsepwweight*(projecttotal/p)+ (testtotal/t)* parsetesteweight+ (quiztotal/q)* parsequizweight;
					
					
					
					double avg = total/100 ;
					int size = gradeList.size() - 1;
					Collections.sort(gradeList);

					message += "</tbody></table></div><p></p> Average = " + avg;

					if (searchmode.equals("avgbystudent")) {
						message += "Average = " + avg;
					} else if (searchmode.equals("avgbyassignment")) {
						message += "Average = " + avg;
					} else if (searchmode.equals("highandlow")) {
						String min = gradeList.get(0);

						String max = gradeList.get(size);
						message += " <p> </p> Min = " + min + " Max = " + max;
					}
				

				request.setAttribute("message", message);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		getServletContext().getRequestDispatcher("/searchgradebook.jsp")
				.forward(request, response);
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