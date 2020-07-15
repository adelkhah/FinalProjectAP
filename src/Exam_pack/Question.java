package Exam_pack;

import java.io.Serializable;

public class Question implements Serializable {
    private String question;
    private int point;
    private Exam exam;
    private String answer;
    private int number;
    public void show(boolean showotherq){
        if(this instanceof Tashrihi_Question){
            Tashrihi_Question q = (Tashrihi_Question) this;
            q.show(showotherq);
        }
        if (this instanceof Test_Question) {
            Test_Question q = (Test_Question) this;
            q.show(showotherq);
        }
        if(this instanceof TrueFalse_Question){
            TrueFalse_Question q = (TrueFalse_Question) this;
            q.show(showotherq);
        }
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
