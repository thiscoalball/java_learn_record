public class Recursion
{
	//�ݹ�  ����Ҫ���ջ  �Ƚ�����Ļ���  ��ջ��һ������ջ
	//��ջʱ�����ŵ���  
	public static void main(String[] args)
	{
		Recur r = new Recur();
		r.test1(10);
		int res = r.factorial(5); 
		System.out.println("5�Ľ׳� res =" + res);
	}
}


class Recur
{
	//1 ��ӡ����
	public void test1(int n)
	{
		if(n > 2)
		{
			test1(n - 1);
		}
		System.out.println("n = " + n);    //���ﲻ�ܼ�else
	}
	//2 �׳�����
	//˭���õ� ����ͷ��ظ�˭  ������Ҫ 
	//��������ֱ��nΪ1��ʼ����  1 * 2 * 3 * 4 * 5��ջ�����²��ϵ��� ����
	public  int factorial(int n)
	 {
		if (n == 1)
		{
			return 1;
		} 
		else
		{
			return factorial(n - 1) * n;
		}
	}
}