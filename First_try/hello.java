
//一个公共私有的类 类名必须和文件名字一样
//一个源文件最多只能有一个public类

public class hello
{
	//main表示主方法 程序入口    是固定的书写格式
	public static void main(String[] args)
	{
		System.out.println("hello world1");
	}
}


//非public类也可以放主方法进去 然后运行。
class Dog
{
	
	public static void main(String[] args)
	{
		System.out.println("hello dog");
	}
}

