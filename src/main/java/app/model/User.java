package app.model;

public class User {
    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public int getAge() {
        return age;
    }

    public String getCounty() {
        return county;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public String getFull_address() {
        return full_address;
    }

    public String getSchedule() {
        return schedule;
    }

    private int id;
    private String fullname;
    private int age;
    private String county;
    private String neighbourhood;
    private String full_address;
    private String schedule;

    public User(int id, String fullname, int age, String county, String neighbourhood, String full_address,
                String schedule){
        this.id = id;
        this.fullname = fullname;
        this.age = age;
        this.county = county;
        this.neighbourhood = neighbourhood;
        this.full_address = full_address;
        this.schedule = schedule;
    }
}
