class Yanghui
{
	public static void main(String[] args)
	{
	/*�������
				1
    	       1 1
		 	  1 2 1
			 1 3 3 1
			1 4 6 4 1
          1 5 10 10 5 1
	*/
          	//���� n�� ��n��Ԫ��
          	//��һ�еĵ�һ��Ԫ�غ����һ��Ԫ�ض���1
          	//�����п�ʼ   ���ڷǵ�һ��Ԫ�غ����һ��Ԫ�ص�ֵ   arr[i][j] = arr[i-1][j] + arr[i-1][j-1]
    	int[][] yanghui = new int[10][];
		for(int i = 0; i < yanghui.length; i++)
		{
			yanghui[i] = new int[i+1]; //��һ��һ��Ԫ�� �ڶ������� �������ٿռ�
			//��ÿ�е�һά���鸳ֵ
			for(int j = 0; j < yanghui[i].length; j++)
			{
				if(j == 0 || j == yanghui[i].length - 1)//ÿ�еĵ�һ��Ԫ�ض���1 
				{ 
					yanghui[i][j] = 1;
				}
				else
				{
					yanghui[i][j] = yanghui[i-1][j] + yanghui[i-1][j-1];
				}
			}
		}
		for(int i = 0; i < yanghui.length; i++)
		{
			for(int j = 0; j < yanghui[i].length; j++)
			{
				System.out.print(yanghui[i][j] + " ");
			}
				System.out.println();

		}
	}
	
}