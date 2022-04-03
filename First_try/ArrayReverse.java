public class ArrayReverse
{
	public static void main(String[] args)
	{
		//反转数组
		int[] arr = {11,22,33,44,55,66};
		//思路：第一个和最后一个 第二个和倒数第二个 以此类推
		//交换次数就是 arr.length / 2
		//交换的下标就是 i 和 arr.length - 1 - i交换
		int temp = 0;
		int len = arr.length; // 这一步优化使后面不用每次都计算 arr.length
		for(int i = 0; i < len/2; i++)
		{
			temp = arr[len - 1 - i];
			arr[len - 1 - i] = arr[i];
			arr[i] = temp;
		}
		for(int i = 0; i < len ; i++)
		{
			System.out.print(arr[i] + " ");
		}
	}
}

class ArrayReverse1
{
	//采用逆序遍历法
	public static void main(String[] args)
	{
		int[] arr = {11,22,33,44,55,66};
		int len = arr.length;
		int[] arr2 = new int[len];
		int j = 0;

		for(int i = len - 1; i  >= 0; i--)
		{
			arr2[j] = arr[i];
			j++;
		}
		
		arr = arr2; //让arr指向arr2的地址 不用原来arr的地址 会被垃圾回收机制回收
		for(int i = 0; i < len ; i++)
		{
			System.out.print(arr[i] + " ");
		}
	}
}