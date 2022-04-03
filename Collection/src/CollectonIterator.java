import java.util.ArrayList;
import java.util.Iterator;
@SuppressWarnings({"all"})
public class CollectonIterator {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(new Book("三国演义","罗贯中",10.1));
        list.add(new Book("水浒传","施耐庵",5.1));
        list.add(new Book("红楼梦","曹雪芹",50.2));
        list.add(new Book("红楼梦","曹雪芹",50.2));


        //System.out.println(List);不采用这个方法 采用迭代器
        //得到迭代器
        Iterator iterator = list.iterator();
//        //采用while遍历
//        while(iterator.hasNext()){ //hasNext()先进行检查
//            System.out.println(iterator.next());//取出并打印
//        }
        //快捷键生成it 然后ctrl + j  然后itit

        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.println(next);
        }

        //退出while循环后 迭代器指向的是最后一个元素 所以如果再次遍历的话需要重置迭代器
        iterator = list.iterator();
        System.out.println("======重置后第二次遍历=======");
        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.println(next);
        }
        //增强for迭代
        System.out.println("======使用增强for的迭代======");
        for(Object a:list){
            System.out.println(a);
        }
    }
}

class Book{

    private String name;
    private String author;
    private double price;

    public Book(String name, String author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}