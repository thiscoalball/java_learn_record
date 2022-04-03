class Test
{
	public static void main(String[] args)
	{
		//创建对象调用其方法
		Person p1 = new Person();
		p1.speak();
		p1.cal02(5);
		int sum = p1.getSum(1,1);
		System.out.println("getSum方法返回的计算结果为" + sum);

	}
}

class Person
{
	String name;
	int age;
	public void speak()
	{
		System.out.println("I am a good guy");
	}
	public void cal02(int n)
	{
		int res = 0;
		for(int i = 1;i<=n;i++)
		{
			res += i;
		}
		System.out.println("计算结果为" + res);
	}

	public int getSum(int n,int i)
	{
		int res = n + i;
		return res;
	}
}