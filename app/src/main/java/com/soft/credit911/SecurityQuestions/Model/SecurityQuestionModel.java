package com.soft.credit911.SecurityQuestions.Model;

import java.util.ArrayList;

public class SecurityQuestionModel {
    String id, text;
    ArrayList<AnswersModel> answers;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<AnswersModel> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<AnswersModel> answers) {
        this.answers = answers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}