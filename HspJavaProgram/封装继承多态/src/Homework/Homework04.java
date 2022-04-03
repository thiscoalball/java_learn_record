package Homework;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

public class Homework04 {
    public static void main(String[] args) {
        Manager jack = new Manager("jack", 1000, 30);
        Worker marry = new Worker("marry", 1000, 30);

        jack.printSalary();
        marry.printSalary();

    }
}
