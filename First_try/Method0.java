class Test
{
	public static void main(String[] args)
	{
		//������������䷽��
		Person p1 = new Person();
		p1.speak();
		p1.cal02(5);
		int sum = p1.getSum(1,1);
		System.out.println("getSum�������صļ�����Ϊ" + sum);

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
		System.out.println("������Ϊ" + res);
	}

	public int getSum(int n,int i)
	{
		int res = n + i;
		return res;
	}
}