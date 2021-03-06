package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sectionId")
    private Section sectionId;
    @Column(name = "questionText")
    private String questionText;
    @Column(name = "score")
    private Integer score;

    @Lob
    @Column(name = "questionImage")
    private byte[] questionImage;

    @OneToMany(mappedBy = "questionId",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Answer> answers;

    @ManyToMany(mappedBy = "questions")
    @JsonIgnore
    private Set<Problem> problems;

    public Question() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Section getSectionId() {
        return sectionId;
    }

    public void setSectionId(Section sectionId) {
        this.sectionId = sectionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public byte[] getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(byte[] questionImage) {
        this.questionImage = questionImage;
    }

    public Set<Problem> getProblems() {
        return problems;
    }

    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }
}
