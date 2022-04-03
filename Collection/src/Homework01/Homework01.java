package Homework01;

import java.util.ArrayList;

/**
 * 封装一个新闻类 构造只包含标题 重写toString只要标题
 * 实例化两个对象 1：新冠肺炎确诊超千万，数百万印度信徒赴恒河“圣浴”引民众担忧
 *              2：男子突然想起两个月前钓的鱼还在网兜，捞起一看赶紧放生
 * 将对象添加到ArrayList集合中并倒序遍历
 * 遍历集合中对新闻标题进行处理，只保留前15个字 并在后面添加...
 * 在控制台打印遍历的新闻标题
 * */
public class Homework01 {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new News("新冠肺炎确诊超千万，数百万印度信徒赴恒河\"圣浴\"引民众担忧"));
        arrayList.add(new News("男子突然想起两个月前钓的鱼还在网兜，捞起一看赶紧放生"));

        int size = arrayList.size();
        for (int i = size - 1; i >= 0; i--) {//倒序遍历法
            News news = (News) arrayList.get(i);//取出并强转
            System.out.println(processTitle(news.getTitle()));
        }
    }
    public static String processTitle(String title){
        if(title == null){
            return  null;
        }
        if(title.length() > 15){
            return title.substring(0,15) + "..."; //[0,15)
        }
        else{
            return title;
        }
    }
}

class News{
    private String title;
    private String content;

    public News(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                '}';
    }
}