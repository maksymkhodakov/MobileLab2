package com.mobileapp.mobilelaba2.ui.dashboard.database;

public class StudentCourses {
    private int id;
    private String pib;
    private String name;
    private String grade;
    private String adress;

    public StudentCourses(){
    }

    public StudentCourses(String pib, String name, String grade, String adress) {
        this.pib = pib;
        this.name = name;
        this.grade = grade;
        this.adress = adress;
    }

    public StudentCourses(int Id, String pib, String grade1, String grade2, String adress) {
        this.id = id;
        this.pib = pib;
        this.name = grade1;
        this.grade = grade2;
        this.adress = adress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getName() {
        return name;
    }

    public void setName(String grade1) {
        this.name = grade1;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade2) {
        this.grade = grade2;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
