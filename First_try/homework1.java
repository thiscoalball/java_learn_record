class homework1
{
	public static void main(String[] args)
	{
		double money = 100000;
		double cash = money;
		int count = 0;
		while(true)
		{
			if(cash > 50000)
			{
				cash *= 0.95;
				count++;
			}
			else if(cash > 50000 && cash >= 1000)
			{
				cash -= 1000;
				count++;
			}
			else
			{
				break;
			}
		}
		System.out.println(money + "元钱可以过"+count +"次路口" );
	}
}

class homework2
{
	public static void main(String[] args)
	{
		double sum = 0;
	//奇数为+，偶数为-
	//输出（1 - 1/2 + 1/3 - 1/4 + 1/5 - 1/6............1/100)的值  
		for(int i = 1; i <= 100; i++)
		{
			if(i % 2 != 0)
			{
				sum += 1.0/i; //1.0很重要 因为是double
			}
			else
			{
				sum -= 1.0/i;
			}
		}
		System.out.println("sum+=" + sum);
	}
	
}

class homework3
{
	public static void main(String[] args)
	{
		int sum = 0;
		//计算1+（1+2）+（1+2+3）+（1+2+3+4).........(1+....100)   1*100 + 2*99 + 3*97 .... 100* 1
		for(int i = 1; i<=100; i++)
		{
			for(int j = 1; j<=101 - i; j++)
			{
				sum+= j;
			}
			//for(int j = 1; j<=i; j++)  这样也可以
			//{
			//	sum+= j;
			//}
		}
		System.out.println("sum=" + sum);
	}
}