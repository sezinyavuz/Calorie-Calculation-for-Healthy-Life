public class People {
    private String name, id, gender;
    private double  weight, height;
    private int caloritaken, caloriburned, age;

    public People(String id, String name, String gender, double weight, double height, int age){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.caloriburned = 0;
        this.caloritaken = 0;
    }

    public int getCaloriburned() {
        return caloriburned;
    }

    public void setCaloriburned(int caloriburned) {
        this.caloriburned = caloriburned;
    }

    public int getCaloritaken() {
        return caloritaken;
    }

    public void setCaloritaken(int caloritaken) {
        this.caloritaken = caloritaken;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int dailyneeds(){

        if (this.gender.equals("female")){
            return (int) Math.round(665 + (9.6 * this.weight) + (1.7 * this.height) - (4.7 * this.age));
        }
        else {
            return (int) Math.round(66 + (13.75 * this.weight) + (5 * this.height) - (6.8 * this.age));
        }

    }

}
