package com.velocity.anjana;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {

	public static Connection connection() throws ClassNotFoundException, SQLException {
		// method for establishing connection to database
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/velocity", "root", "root");
		return conn;
	}

	public void saveStudent(Student s1) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = connection();

			stmt = con.prepareStatement("insert into Student(name,score,grade)values(?,?,?)");
			stmt.setString(1, s1.getName());
			stmt.setInt(2, s1.getScore());
			stmt.setString(3, String.valueOf(s1.getGrade()));

			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			stmt.close();

		}
	}

	public List<Student> getStudents() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/velocity", "root", "root");

			PreparedStatement stmt = con.prepareStatement("select * from student");
			List<Student> studentList = new ArrayList<>();
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				Student s1 = new Student();
				s1.setId(resultSet.getObject("id").toString());
				s1.setGrade(resultSet.getObject("grade").toString().charAt(0));
				s1.setName(resultSet.getObject("name").toString());
				s1.setScore(Integer.parseInt(resultSet.getObject("score").toString()));
				studentList.add(s1);
			}
			return studentList;

		} catch (Exception e) {
		System.out.println("Exception occured");

			return null;

		}

	}

	public void saveQuestion(Question ques) {
		
		try {
			Connection con = connection();

			PreparedStatement stmt = con.prepareStatement(
					"insert into question(question,option1,option2 ,option3 ,option4,answer)values(?,?,?,?,?,?)");
			stmt.setString(1, ques.getQuestion());
			stmt.setString(2, ques.getOption1());
			stmt.setString(3, ques.getOption2());
			stmt.setString(4, ques.getOption3());
			stmt.setString(5, ques.getOption4());
			stmt.setString(6, ques.getAnswer());

			int i = stmt.executeUpdate();
			System.out.println("Question saved.");

			con.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Question> getQuestions() {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			 con = connection();
			//Class.forName("com.mysql.jdbc.Driver");
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/velocity", "root", "root");

			PreparedStatement stmt = con.prepareStatement("select * from question");
			List<Question> questionList = new ArrayList<>();
			ResultSet resultSet = stmt.executeQuery();
			// fetching data from question table-- id,question,option1,option2 ,option3
			// ,option4,answer
			while (resultSet.next()) {
				Question q1 = new Question();
				q1.setSerialNo(Integer.parseInt(resultSet.getObject("serialNo").toString()));
				q1.setQuestion(resultSet.getObject("question").toString());
				q1.setOption1(resultSet.getObject("option1").toString());
				q1.setOption2(resultSet.getObject("option2").toString());
				q1.setOption3(resultSet.getObject("option3").toString());
				q1.setOption4(resultSet.getObject("option4").toString());
				q1.setAnswer(resultSet.getObject("answer").toString());
				questionList.add(q1);

			}

			return questionList;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;

		}
	}

	public int findStudentScore(String name) {
		int score = 0;
		Connection con = null;
		try {
			 con = connection();
			//Class.forName("com.mysql.jdbc.Driver");
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/velocity", "root", "root");

			PreparedStatement stmt = con.prepareStatement("select * from student where name = ?");
			stmt.setString(1, name);
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				score = Integer.parseInt(resultSet.getObject("score").toString());

			}

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Student name does not exist in our record");

		}
		return score;
	}

}
