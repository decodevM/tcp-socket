package com.decodev.additional_part2;

public class Patient {

    private int id;
    private String gender;
    private int age;
    private String[] notes;

    public Patient(int id, String gender, int age, String[] notes) {
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Gender: " + gender + ", Age: " + age + ", Notes: " + getNotesAsString();
    }

    public int getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String[] getNotes() {
        return notes;
    }

    public String getNotesAsString() {
        StringBuilder result = new StringBuilder();
        for (String note : notes) {
            result.append(note).append(" ");
        }
        return result.toString().trim();
    }
}
