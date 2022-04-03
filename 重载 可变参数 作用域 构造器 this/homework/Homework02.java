public class Homework02
{
	public static void main(String[] args) 
	{
		String[] strs = {"jack", "TOM", "Marry"};
		A02 a02 = new A02();
		int index = a02.find("jjj",strs);
		if(index != -1)
		{
			System.out.println("找到了 下标在" + index);
		}
		else
		{
			System.out.println("找不到");
		}
	}
}
//实现查找某字符串是否在字符数组中

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