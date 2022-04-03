public class Homework01
{
	public static void main(String[] args) 
	{
		double[] arr = {1,2,3,4,56,5};
		Max_Array max = new Max_Array();
		max.max(arr);
	}
}
class Max_Array
{
	public void max(double[] array)
	{
		double maxNum = 0;
		for(int i = 0; i < array.length; i++)
		{
			if(maxNum < array[i])
			{
				maxNum = array[i];
			}
		}
		System.out.println("最大值为：" + maxNum);
	}
}