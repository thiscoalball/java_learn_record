public class VarParameter
{
	public static void main(String[] args)
	{
		T ttt = new T();
		int res = ttt.sum(1,2,3,4,5);//可以传入任意个数的整形
		System.out.println(res);
	
		int[] arr = {1,2,3};
		//可变参数的实参可以为数组
		System.out.println(ttt.sum(arr));

	}
}
class T
{
	//使用可变参数 可以当作数组来使用
	public int sum(int... nums)
	{
		int res = 0;
		for(int i = 0; i< nums.length; i++)
		{
			res += nums[i];
		}
		return res;
	}
	//可变参数可以和普通类型的参数放在一起 但是必须保证可变参数放在最后 如下 第一个也可以直接传double
	public int sum(String str,double... nums)
	{

	}
	//一个形参列表中只能出现一个可变参数,如 底下这样是不允许的
	public int sum(int...nnnn,double... nums)
	{

	}
}