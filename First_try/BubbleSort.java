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
					if(arr[j] > arr[j+1]) // �����һ�������ں�����Ǹ����ͽ���
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