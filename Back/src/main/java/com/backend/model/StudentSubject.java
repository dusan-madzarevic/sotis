package com.backend.model;

import javax.persistence.*;

@Entity
public class StudentSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentId")
    private Student studentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subjectId")
    private Subject subjectId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "knowledgeStateId")
    private KnowledgeState knowledgeStateId;

    public StudentSubject() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    public KnowledgeState getKnowledgeStateId() {
        return knowledgeStateId;
    }

    public void setKnowledgeStateId(KnowledgeState knowledgeStateId) {
        this.knowledgeStateId = knowledgeStateId;
    }
}
