
class Test
{
	public static void main(String[] args)
	{
		AA aa = new AA();
		boolean flag = aa.isOdd(5);
		if(flag == true)
		{
			System.out.println("��ż��");
		}
		else
		{
			System.out.println("������");
		}
	}
}

//��дAA���ж�����������ż��
class AA
{
	public boolean isOdd(int n)
	{
		if(n % 2 == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}