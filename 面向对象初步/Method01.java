class Method01
{
	public static void main(String[] args)
	{
		int [][] map = {{0,0,0},{1,1,1},{2,2,2}};
		MyTool01 tool = new MyTool01();
		tool.printArr(map);
		MethodDetail1 a = new MethodDetail1();
		System.out.println();
		System.out.println("接下来调用的是一个方法返回两个数据 一个和 一个差");

		int[] res = a.getSumAndSub(1,1);
		System.out.println("和为" + res[0]);
		System.out.println("差为" + res[1]);
		System.out.println();

		System.out.println("接下来调用的是同一个类中的方法调用");
		A aaa = new A();
		aaa.sayOk();

		System.out.println();
		System.out.println("接下来调用的的是从A类调用B类方法");
		aaa.A_use_B();
	}
}
class MyTool01
{
	public void printArr(int[][] map)
	{
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length;j++)
			{
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
class MethodDetail1
{
	public int[] getSumAndSub(int n1,int n2)
	{
		int[] resArr = new int[2];
		resArr[0] = n1 + n2;
		resArr[1] = n1 - n2;
		return resArr;
	}
}

class A
{
//同一个类中的方法调用 直接调用即可
	public void MyPrint(int n)
	{
		System.out.println("MyPrint()方法被调用 n = " + n);
	}
	public void sayOk()
	{
		MyPrint(10);
	}
	public void A_use_B()
	{
		B bbb = new B();
		bbb.hi();
	}

}

class B
{
	public void hi()
	{
		System.out.println("B类的方法被A类成功调用");
	}
}