public class ArrayReverse
{
	public static void main(String[] args)
	{
		//��ת����
		int[] arr = {11,22,33,44,55,66};
		//˼·����һ�������һ�� �ڶ����͵����ڶ��� �Դ�����
		//������������ arr.length / 2
		//�������±���� i �� arr.length - 1 - i����
		int temp = 0;
		int len = arr.length; // ��һ���Ż�ʹ���治��ÿ�ζ����� arr.length
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
	//�������������
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
		
		arr = arr2; //��arrָ��arr2�ĵ�ַ ����ԭ��arr�ĵ�ַ �ᱻ�������ջ��ƻ���
		for(int i = 0; i < len ; i++)
		{
			System.out.print(arr[i] + " ");
		}
	}
}