//���������İ�

import java.util.Scanner;	

public class TryScanner
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);//����һ��ɨ��������  System.in ������ǴӼ�������
		System.out.println("����������:");
		String name = input.next();
		System.out.println("����������:");
		int age = input.nextInt();
		System.out.println("������ɼ�:");
		double score = input.nextDouble();

		System.out.println("name:"+name);
		System.out.println("age:"+age);
		System.out.println("score:"+score);



	}
}