class Yanghui
{
	public static void main(String[] args)
	{
	/*杨辉三角
				1
    	       1 1
		 	  1 2 1
			 1 3 3 1
			1 4 6 4 1
          1 5 10 10 5 1
	*/
          	//规律 n行 有n个元素
          	//第一行的第一个元素和最后一个元素都是1
          	//第三行开始   对于非第一个元素和最后一个元素的值   arr[i][j] = arr[i-1][j] + arr[i-1][j-1]
    	int[][] yanghui = new int[10][];
		for(int i = 0; i < yanghui.length; i++)
		{
			yanghui[i] = new int[i+1]; //第一行一个元素 第二行两个 这样开辟空间
			//给每行的一维数组赋值
			for(int j = 0; j < yanghui[i].length; j++)
			{
				if(j == 0 || j == yanghui[i].length - 1)//每行的第一个元素都是1 
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