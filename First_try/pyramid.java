import java.util.Scanner;

public class pyramid
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.print("����������Ҫ�Ľ���������");
		int ROW = input.nextInt();
		for(int i = 1; i <= ROW; i++)//��ӡ����
		{
		//��ӡ * ֮ǰ�Ŀո�
			for(int k = 1; k<=ROW - i; k++)//���ϵ��� �ո�Ϊ4 3 2 1 Ҳ�����ܲ�����һ �����û�пո� ѭ��Ϊ0 ����
			{
				System.out.print(" ");
			}
			for(int j = 1; j <= 2 * i - 1; j++)//����ÿһ�� * �ĸ������ϵ���Ϊ 1 3 5 7 9
			{
				if(j == 1 || j == 2 * i - 1 || i == ROW) //��������������Ϊ���һ�б���ȫ�����Ǻ�
				{
					System.out.print("*");
				}
				else
				{
					System.out.print(" ");
				}
			}
			System.out.println("");
			 //��ӡ��һ�����
		}
	}
}