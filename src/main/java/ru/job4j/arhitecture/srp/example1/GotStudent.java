package ru.job4j.arhitecture.srp.example1;


/**
 * Example 4
 * Этот класс нарушает принцип Single Responsibility Principle
 * Он представляет сущность, умеет ее сохранять, знает как рассчитывать оценку и здавать экзамен
 */
public class GotStudent {
    private String name;
    private Integer age;
    private Integer finalEstimation;

    public void save() {

    }

    public static GotStudent findByName(String name) {
        return null;
    }

    public void calculateFinalEstimation() {
//        this.finalEstimation =
    }

    public boolean passExam(Exam exam) {
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getFinalEstimation() {
        return finalEstimation;
    }

    public void setFinalEstimation(Integer finalEstimation) {
        this.finalEstimation = finalEstimation;
    }
}
