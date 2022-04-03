
class Test
{
	public static void main(String[] args)
	{
		AA aa = new AA();
		boolean flag = aa.isOdd(5);
		if(flag == true)
		{
			System.out.println("是偶数");
		}
		else
		{
			System.out.println("是奇数");
		}
	}
}

//编写AA类判断是奇数还是偶数
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