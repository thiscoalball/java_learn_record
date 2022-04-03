class BubbleSort
{
	public static void main(String[] args)
	{
			int temp = 0;
			int[] arr = {24,69,80,57,13};
			for(int i = 0; i < 4; i++)
			{
				for(int j = 0; j < 4 - i; j++)
				{
					if(arr[j] > arr[j+1]) // 如果第一个数大于后面的那个数就交换
					{
						temp = arr[j];
						arr[j] = arr[j+1];
						arr[j+1] = temp;
					}
				}
			}
			for(int a = 0; a < arr.length ; a++)
			{
				System.out.print(arr[a] + " ");
			}
		
	}
}