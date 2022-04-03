package 代码块;

public class CodeBlock01
{
    public static void main(String[] args) {
        Movie movie = new Movie("czy");
        Movie hhh = new Movie("hhh", 123);
    }
}
class Movie{
    private String name;
    private double price;
    private String director;

    {//这样后不管调用哪个构造器创建对象 都会先调用代码块
        System.out.println("aaaaaaaaaaaa");
        System.out.println("aaaaaaaaaaaa");
        System.out.println("aaaaaaaaaaaa");
        System.out.println();
    }
    //三个构造器构成重载
    public Movie(String name) {
//        System.out.println("aaaaaaaaaaaa");//假设这三条语句在三个构造器里都要执行 代码就很冗余
//        System.out.println("aaaaaaaaaaaa");//那么就把三个语句放在一个代码块中
//        System.out.println("aaaaaaaaaaaa");
        this.name = name;
    }

    public Movie(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Movie(String name, double price, String director) {
        this.name = name;
        this.price = price;
        this.director = director;
    }
}