

public class RecursionExercise
{
	public static void main(String[] args)
	{
		T t1 = new T();
		int res = t1.fibonacci(7);
		System.out.println("����Ӧ����13 ��ôʵ����" + res);
	}
}


class T
{
	//�ݹ鷽����쳲��������� 1 1 2 3 5 8 13 ����һ������n ��� ���Ƕ���
	//n = 1  1
	//n = 2  1
	//n = 3  ��ǰ�������ĺ�
	//�����һ���ݹ��˼·     �õ�13 Ҫ�õ� 5 8 �õ� 8 Ҫ�õ� 5 3 �õ� 5 Ҫ�õ� 3 2 ���ϵݹ�
	public int fibonacci(int n)
	{
		if(n == 1 || n == 2)
		{
			return 1;
		}
		else
		{
			return fibonacci(n - 1) + fibonacci(n - 2);
		}
	}
}