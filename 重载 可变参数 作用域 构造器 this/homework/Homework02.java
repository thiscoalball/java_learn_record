public class Homework02
{
	public static void main(String[] args) 
	{
		String[] strs = {"jack", "TOM", "Marry"};
		A02 a02 = new A02();
		int index = a02.find("jjj",strs);
		if(index != -1)
		{
			System.out.println("�ҵ��� �±���" + index);
		}
		else
		{
			System.out.println("�Ҳ���");
		}
	}
}
//ʵ�ֲ���ĳ�ַ����Ƿ����ַ�������

class A02
{
	public int find(String findStr, String[] strs)
	{
		for(int i = 0; i < strs.length; i++)
		{
			if(findStr.equals(strs[i]))
			{
				return i;
			}
		}
		return -1;
	}
}