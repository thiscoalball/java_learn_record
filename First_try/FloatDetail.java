public class FloatDetail
{
	public static void main(String[] args)
	{
		//浮点数使用陷阱: 2.7 和 8.1 / 3  比较
		//看看一段代码
		double num11 = 2.7;
		double num12 = 8.1/3;	//8.1 / 3; //2.7
		System.out.println(num11);//2.7
		System.out.println(num12);//接近2.7的一个小数，而不是2.7
		//得到一个重要的使用点: 当我们对运算结果是小数的进行相等判断是，要小心
		//应该是以两个数的差值的绝对值，在某个精度范围类判断
		if( num11 == num12)
		{
			System.out.println("num11 == num12 相等");
		}
		//正确做法
		if(Math.abs(num11 - num12) < 0.000001 ) 
		{
			System.out.println("差值非常小，到我的规定精度，认为相等...");
		}
	}
}