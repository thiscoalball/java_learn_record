import java.util.Arrays;
import java.util.Comparator;

public class ExerciseArray {
    public static void main(String[] args) {
        Book[] books = new Book[4];
        books[0] = new Book("红楼梦",100);
        books[1] = new Book("新金瓶梅",90);
        books[2] = new Book("青年文摘20",5);
        books[3] = new Book("java从入门到放弃",300);
        Arrays.sort(books, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                Book book1 = (Book) o1;
                Book book2 = (Book) o2;
                double result = book1.getPrice() - book2.getPrice();
//                if(result > 0){
//                    return  -1;
//                }else if(result < 0){
//                    return 1;
//                }else{
//                    return 0;
//                }
                int result1 = (int)result;
                return result1;
            }
        });
        Arrays.sort(books, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                Book book1 = (Book) o1;
                Book book2 = (Book) o2;
                double result = book2.getName().length() - book1.getName().length();
//                if(result > 0){
//                    return  -1;
//                }else if(result < 0){
//                    return 1;
//                }else{
//                    return 0;
//                }
                int result1 = (int)result;
                return result1;
            }
        });

        System.out.println(Arrays.toString(books));
    }
}

class Book{
    private String name;
    private double price;

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
