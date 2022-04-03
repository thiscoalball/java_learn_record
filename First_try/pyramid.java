import java.util.Scanner;

public class pyramid
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.print("请输入你需要的金字塔层数");
		int ROW = input.nextInt();
		for(int i = 1; i <= ROW; i++)//打印层数
		{
		//打印 * 之前的空格
			for(int k = 1; k<=ROW - i; k++)//从上到下 空格为4 3 2 1 也就是总层数减一 第五层没有空格 循环为0 结束
			{
				System.out.print(" ");
			}
			for(int j = 1; j <= 2 * i - 1; j++)//控制每一层 * 的个数从上到下为 1 3 5 7 9
			{
				if(j == 1 || j == 2 * i - 1 || i == ROW) //最后这个条件是因为最后一行必须全部打星号
				{
					System.out.print("*");
				}
				else
				{
					System.out.print(" ");
				}
			}
			System.out.println("");
			 //打印完一层后换行
		}
	}
}