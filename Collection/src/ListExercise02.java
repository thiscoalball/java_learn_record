import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@SuppressWarnings({"all"})
public class ListExercise02 {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(new Book_("红楼梦",100,"曹雪芹"));
        list.add(new Book_("水浒传",40,"施耐庵"));
        list.add(new Book_("西游记",10,"吴承恩"));
        list.add(new Book_("三国志",80,"罗贯中"));

        sortlist(list);
        for(Object o:list){
            System.out.println(o);
        }

    }
    public static void sortlist(List list){
        int lenth = list.size();
        for (int i = 0; i < lenth - 1; i++) {
            for (int j = 0; j < lenth - 1 - i; j++) {
                Book_ book1 = (Book_) list.get(j);
                Book_ book2 = (Book_) list.get(j + 1);
                if(book1.getPrice() > book2.getPrice()){
                    list.set(j,book2);
                    list.set(j+1,book1);
                }
            }
        }
    }
}
@SuppressWarnings({"all"})
class Book_{

    private String name;
    private double price;
    private String author;

    public Book_(String name, double price, String author) {
        this.name = name;
        this.price = price;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "名称：" + name + "\t" + "价格：" + price + "\t" + "作者："+ author;
    }
}