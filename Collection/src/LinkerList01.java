public class LinkerList01 {
    public static void main(String[] args) {
        //模拟一个简单的双向链表
        Node jack = new Node("jack");
        Node tom = new Node("tom");
        Node chen = new Node("chen");

        //连接形成双向链表 jack->tom->chen
        jack.next = tom;
        tom.next = chen;
        //chen->tom->jack
        chen.pre = tom;
        tom.pre = jack;

        Node first = jack;    //头节点
        Node last  = chen;    //尾节点

        //演示从头到尾遍历
        System.out.println("=====从头到尾======");
        while(true){
            if(first ==null){
                break;
            }
            System.out.println(first);
            first = first.next;
        }
        System.out.println("=====从尾到头=====");
        //演示从尾到头
        while(true){
            if(last ==null){
                break;
            }
            System.out.println(last);
            last = last.pre;
        }

        //创建对象 1 创建一个节点
        Node smith = new Node("smith");
        smith.next = chen; //操作前后节点
        smith.pre = tom;
        tom.next = smith;
        chen.pre  = smith;

        first = jack;    //因为前面的遍历 first移动了 所以这重置以下
        System.out.println("=====插入一次后遍历======");
        while(true){
            if(first ==null){
                break;
            }
            System.out.println(first);
            first = first.next;
        }
    }
}

class Node{
    public Object item; //数据域
    public Node next;   //指向后一个节点
    public Node pre;    //指向前一个节点

    public Node(Object name){
        this.item = name;
    }

    public String toString(){
        return "Node name = " + item;
    }

}