//导入这个类的包

import java.util.Scanner;	

public class TryScanner
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);//创建一个扫描器对象  System.in 代表的是从键盘输入
		System.out.println("请输入姓名:");
		String name = input.next();
		System.out.println("请输入年龄:");
		int age = input.nextInt();
		System.out.println("请输入成绩:");
		double score = input.nextDouble();

		System.out.println("name:"+name);
		System.out.println("age:"+age);
		System.out.println("score:"+score);



	}
}