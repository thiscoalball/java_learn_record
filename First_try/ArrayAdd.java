import java.util.Scanner;
class ArrAdd1
{
	public static void main(String[] args)
	{
		//��̬�ĸ��������Ԫ��   ʵ�ֶ����������
		//���ӵ�Ԫ��ֱ�ӷ���������� �û� ����ѡ���Ƿ���� ���
		//��ӳɹ��Ƿ���� y/n ???
		
		//˼· �����µ����� ����Ϊarr.length + 1  
		//���� Ȼ��ֵ��arrNew ����Ҫ��ֵ������ ��ֵ��arrNew[arrNew.length - 1] = ����
		//��arrָ��arrNew      ԭ���鱻��������
		
		Scanner myScanner =  new Scanner(System.in);
		int[] arr = {1, 2, 3};
		//���������ı�    �Ż��������һ��
		do
		{
			int[] arrNew = new int[arr.length + 1];
			for(int i = 0; i < arr.length; i++)
			{
				arrNew[i] = arr[i];
			}
			System.out.println("��������Ҫ��ӵ�Ԫ�أ�");

			int addNum = myScanner.nextInt();
			arrNew[arrNew.length - 1] = addNum;
 
			arr = arrNew;

			for(int i = 0; i < arr.length ; i++)
			{
				System.out.print(arr[i] + " ");
			}

			System.out.println("�Ƿ������� ������y/n");

			char key = myScanner.next().charAt(0);
			if(key == 'n')
			{
				break;
			}
			
		}while(true);

	}
}