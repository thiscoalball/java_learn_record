

public class RecursionExercise
{
	public static void main(String[] args)
	{
		T t1 = new T();
		int res = t1.fibonacci(7);
		System.out.println("数据应该是13 那么实际是" + res);
	}
}


class T
{
	//递归方法求斐波那契数列 1 1 2 3 5 8 13 给你一个整数n 求出 它是多少
	//n = 1  1
	//n = 2  1
	//n = 3  是前两个数的和
	//这就是一个递归的思路     得到13 要得到 5 8 得到 8 要得到 5 3 得到 5 要得到 3 2 不断递归
	public int fibonacci(int n)
	{
		if(n == 1 || n == 2)
		{
			return 1;
		}
		else
		{
			return fibonacci(n - 1) + fibonacci(n - 2);
		}
	}
}