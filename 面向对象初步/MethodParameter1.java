class MethodParameter1
{
	public static void main(String[] args)
	{
		int a = 10;
		int b = 20;
		AA obj = new AA();
		obj.swap1(a,b);

		System.out.println("调用swap1方法后的值为   "+ a + "\t" + b);

	}
}
class AA
{
	//这样传参到外部是无法成功传参的 类似于c语言不用指针一样 形参改变不能影响实参
	//但是如果是类似于数组和对象的 引用 类型的调用   内部改变就会影响外部    引用传递的是地址
	//由于有c语言基础 这里就不再写一遍代码了  等书到了做做题就知道了
	public void swap1(int n1,int n2)
	{
		System.out.println("a和b在swap中交换前的值为"+ n1 + "\t" + n2);
		int temp  = n1;
		n1 = n2;
		n2 = temp;
		System.out.println("a和b在swap后交换后的值为"+ n1 + "\t" + n2);

	}
}