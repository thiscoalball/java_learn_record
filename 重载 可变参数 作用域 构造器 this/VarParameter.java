public class VarParameter
{
	public static void main(String[] args)
	{
		T ttt = new T();
		int res = ttt.sum(1,2,3,4,5);//���Դ����������������
		System.out.println(res);
	
		int[] arr = {1,2,3};
		//�ɱ������ʵ�ο���Ϊ����
		System.out.println(ttt.sum(arr));

	}
}
class T
{
	//ʹ�ÿɱ���� ���Ե���������ʹ��
	public int sum(int... nums)
	{
		int res = 0;
		for(int i = 0; i< nums.length; i++)
		{
			res += nums[i];
		}
		return res;
	}
	//�ɱ�������Ժ���ͨ���͵Ĳ�������һ�� ���Ǳ��뱣֤�ɱ����������� ���� ��һ��Ҳ����ֱ�Ӵ�double
	public int sum(String str,double... nums)
	{

	}
	//һ���β��б���ֻ�ܳ���һ���ɱ����,�� ���������ǲ������
	public int sum(int...nnnn,double... nums)
	{

	}
}