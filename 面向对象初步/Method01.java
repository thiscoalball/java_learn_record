class Method01
{
	public static void main(String[] args)
	{
		int [][] map = {{0,0,0},{1,1,1},{2,2,2}};
		MyTool01 tool = new MyTool01();
		tool.printArr(map);
		MethodDetail1 a = new MethodDetail1();
		System.out.println();
		System.out.println("���������õ���һ������������������ һ���� һ����");

		int[] res = a.getSumAndSub(1,1);
		System.out.println("��Ϊ" + res[0]);
		System.out.println("��Ϊ" + res[1]);
		System.out.println();

		System.out.println("���������õ���ͬһ�����еķ�������");
		A aaa = new A();
		aaa.sayOk();

		System.out.println();
		System.out.println("���������õĵ��Ǵ�A�����B�෽��");
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
//ͬһ�����еķ������� ֱ�ӵ��ü���
	public void MyPrint(int n)
	{
		System.out.println("MyPrint()���������� n = " + n);
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
		System.out.println("B��ķ�����A��ɹ�����");
	}
}