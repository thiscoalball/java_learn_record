public class Recursion
{
	//递归  首先要理解栈  先进后出的机制  进栈后一个个出栈
	//出栈时伴随着调用  
	public static void main(String[] args)
	{
		Recur r = new Recur();
		r.test1(10);
		int res = r.factorial(5); 
		System.out.println("5的阶乘 res =" + res);
	}
}


class Recur
{
	//1 打印问题
	public void test1(int n)
	{
		if(n > 2)
		{
			test1(n - 1);
		}
		System.out.println("n = " + n);    //这里不能加else
	}
	//2 阶乘问题
	//谁调用的 结果就返回给谁  这点很重要 
	//所以这里直到n为1后开始返回  1 * 2 * 3 * 4 * 5从栈顶向下不断弹出 返回
	public  int factorial(int n)
	 {
		if (n == 1)
		{
			return 1;
		} 
		else
		{
			return factorial(n - 1) * n;
		}
	}
}