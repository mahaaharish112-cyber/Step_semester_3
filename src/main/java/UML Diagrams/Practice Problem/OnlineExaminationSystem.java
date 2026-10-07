import java.util.*;

abstract class Question {
    int questionNumber;
    String questionText;

    Question(int questionNumber, String questionText) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
    }

    abstract boolean checkAnswer(String answer);
}

class MCQQuestion extends Question {
    String correctAnswer;

    MCQQuestion(int questionNumber, String questionText, String correctAnswer) {
        super(questionNumber, questionText);
        this.correctAnswer = correctAnswer;
    }

    boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Examination {
    String examName;
    List<Question> questions = new ArrayList<>();

    Examination(String examName) {
        this.examName = examName;
    }

    void addQuestion(Question question) {
        questions.add(question);
    }
}

class Attempt {
    Student student;
    Examination examination;
    Map<Integer, String> answers = new HashMap<>();
    boolean submitted = false;

    Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
    }

    void start() {
        System.out.println(
            "Examination '" + examination.examName +
            "' started by " + student.name + "."
        );
    }

    void answerQuestion(int questionNumber, String answer) {
        if (submitted) {
            System.out.println("Cannot change answer after submission.");
            return;
        }

        answers.put(questionNumber, answer);

        System.out.println(
            "Question " + questionNumber +
            " answered with '" + answer + "'."
        );
    }

    void submit() {
        submitted = true;

        System.out.println(
            "Examination '" + examination.examName +
            "' submitted successfully."
        );

        evaluate();
    }

    void evaluate() {
        int correct = 0;

        for (Question question : examination.questions) {
            String answer = answers.get(question.questionNumber);

            if (answer != null && question.checkAnswer(answer)) {
                correct++;
            }
        }

        System.out.println(
            "Result for '" + examination.examName +
            "' attempt: " + correct + "/" +
            examination.questions.size() + " correct."
        );
    }
}

public class OnlineExaminationSystem {

    public static void main(String[] args) {

        Student student = new Student("Harish");

        Examination exam = new Examination("Math Quiz");

        exam.addQuestion(
            new MCQQuestion(
                1,
                "What is 2 + 2?",
                "A"
            )
        );

        exam.addQuestion(
            new MCQQuestion(
                2,
                "What is 5 + 5?",
                "B"
            )
        );

        Attempt attempt = new Attempt(student, exam);

        attempt.start();

        attempt.answerQuestion(1, "A");
        attempt.answerQuestion(2, "C");

        attempt.submit();
    }
}
