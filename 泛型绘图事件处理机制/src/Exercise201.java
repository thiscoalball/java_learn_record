import java.util.ArrayList;
import java.util.Comparator;


public class Exercise201 {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("aaa",2000,new MyDate(2,14,2002)));
        employees.add(new Employee("aaa",4000,new MyDate(10,1,1945)));
        employees.add(new Employee("aaa",8000,new MyDate(9,1,1971)));

        System.out.println(employees);

        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if(!(o1 instanceof Employee && o2 instanceof Employee))
                {
                    return 0;
                }

                int i = o1.getName().compareTo(o2.getName());
                if( i!=0){
                    return i;
                }

                int i2 = o1.getMydate().getYear() - o2.getMydate().getYear();
                if(i2 != 0){
                    return i2;
                }
                int i3 = o1.getMydate().getMouth() - o2.getMydate().getMouth();
                if(i3 != 0){
                    return i3;
                }

                int i4 = o1.getMydate().getDay() - o2.getMydate().getDay();
                return i4;
            }
        });
        System.out.println("排序后：-------------------");
        System.out.println(employees);
    }
}

class Employee{
    private String name;
    private double sal;
    private MyDate mydate;

    public Employee(String name, double sal, MyDate mydate) {
        this.name = name;
        this.sal = sal;
        this.mydate = mydate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public MyDate getMydate() {
        return mydate;
    }

    public void setMydate(MyDate mydate) {
        this.mydate = mydate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", sal=" + sal +
                ", mydate=" + mydate +
                '}';
    }
}

class MyDate{
    private int mouth,day,year;

    public MyDate(int mouth, int day, int year) {
        this.mouth = mouth;
        this.day = day;
        this.year = year;
    }

    public int getMouth() {
        return mouth;
    }

    public void setMouth(int mouth) {
        this.mouth = mouth;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "mouth=" + mouth +
                ", day=" + day +
                ", year=" + year +
                '}' + '\n';
    }
}