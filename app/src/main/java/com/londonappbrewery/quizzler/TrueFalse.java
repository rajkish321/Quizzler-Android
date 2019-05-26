package com.londonappbrewery.quizzler;

public class TrueFalse {

    private int questionID;
    private boolean answer;

    public TrueFalse(int question, boolean bool) {

        questionID = question;
        answer = bool;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
