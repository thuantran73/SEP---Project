package com.larten.sep_project;

public class MonHoc {
    int id;
    String Name;
    int Students;
    String Day;
    String Time;
    String Room;

    public MonHoc(int id, String name, int students, String day, String time, String room) {
        this.id = id;
        Name = name;
        Students = students;
        Day = day;
        Time = time;
        Room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getStudents() {
        return Students;
    }

    public void setStudents(int students) {
        Students = students;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }
}
