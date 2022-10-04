package com.velocity.anjana;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuizApplication {

	public static void main(String args[]) throws SQLException {
		QuizApplication app = new QuizApplication();
		app.getMenu();

	}

	private static char getGrade(int correctAnsCount) {
		char grade = 0;
		if (correctAnsCount >= 8) {
			grade = 'A';
		} else if (correctAnsCount >= 5 && correctAnsCount < 8) {
			grade = 'B';
		} else if (correctAnsCount < 5) {
			grade = 'C';
		}

		return grade;
	}

	public static void saveQuestions() {
		Dao dao = new Dao();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter question");
		String question = sc.nextLine();
		Question q1 = new Question();
		q1.setQuestion(question);
		System.out.println("Enter option A");
		String optionA = sc.nextLine();
		q1.setOption1("A." + optionA);

		System.out.println("Enter option B");

		String optionB = sc.nextLine();
		q1.setOption2("B." + optionB);
		
		System.out.println("Enter option C");

		String optionC = sc.nextLine();
		q1.setOption3("C." + optionC);
		System.out.println("Enter option D");

		String optionD = sc.nextLine();
		q1.setOption4("D." + optionD);

		System.out.println("Enter answer");

		String answer = sc.nextLine();
		q1.setAnswer(answer);

		dao.saveQuestion(q1);

	}

	public void getMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t Welcome to Quiz!!");
		System.out.println("\n\t\t\t Menu");
		System.out.println("             A.Attempt Quiz                           B.View Score");
		System.out.println("             C.View Score of All Students             D.Add Question");
		
		System.out.println("Enter your choice >>");
		char input = sc.next().charAt(0);

		switch (input) {
		case 'A':
		case 'a':
			attemptQuiz();
			break;
		case 'B':
		case 'b':
			System.out.println("Enter your name");
			String name = sc.next();
			viewScore(name);
			break;
		case 'C':
		case 'c':
			viewScoreOfAllStudents();

			break;
		case 'D':
		case 'd':
			addQuestion();
			break;
		default:
			System.out.println("Choice is not correct");
		}
		System.out.println("Do you want to continue");
		String i = sc.next();
		if (i.equalsIgnoreCase("yes") || i.equalsIgnoreCase("y")) {
			getMenu();
		} else {
			System.out.println("********** Exit *************");
		}
	}

	private static void addQuestion() {
		saveQuestions();
	}

	private static void viewScoreOfAllStudents() {
		Dao dao = new Dao();
		System.out.println("Scores of all the students");
		List<Student> studentList = dao.getStudents();

		for (Student s : studentList) {
			System.out.println(" name :  " + s.getName() + " score: " + s.getScore());
		}

	}

	private static void viewScore(String name) {
		Dao dao = new Dao();
		System.out.println("Your score is " + dao.findStudentScore(name));

	}

	private static void attemptQuiz() {
		Dao dao = new Dao();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name ");
		String name = sc.next();

		List<Question> questionList = getQuestions();
		System.out.println("Please attempt below "+ questionList.size()+ "questions");
		Collections.shuffle(questionList);

		Character ans;
		int correctAnsCount = 0;

		for (Question q : questionList) {
			System.out.println("Q--" + q.getQuestion());
			System.out.println(q.getOption1());
			System.out.println(q.getOption2());
			System.out.println(q.getOption3());
			System.out.println(q.getOption4());

			System.out.println("Enter your Option in Upper case");
			ans = sc.next().charAt(0);

			int res = Character.compare(ans, q.getAnswer().charAt(0));

			if (res == 0) {
				System.out.println("Answer is correct \n");
				correctAnsCount++;
			} else {
				System.out.println("Answer is wrong \n");
			}
		}

		char grade = getGrade(correctAnsCount);

		Student s1 = new Student();
		// s1.setId(id);
		s1.setName(name);
		s1.setScore(correctAnsCount);
		s1.setGrade(grade);
		try {
			dao.saveStudent(s1);
		} catch (SQLException e) {
			}


	}

	private static List<Question> getQuestions() {
		Dao dao = new Dao();

		return dao.getQuestions();

	}
}
