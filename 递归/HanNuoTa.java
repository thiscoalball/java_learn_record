//������Ŀ����������⴮������߼�
//??????????????????????????????????????????????????????????
public class HanNuoTa
{
	public static void main(String[] args)
	{
		T tower = new T();
		tower.move(4,'A','B','C');
	}
}

class T
{
	//num ��ʾҪ�ƶ����̵ĸ���1     a b c ��ʾ�������Էŵ�����  ������������Ҫ�ƶ���c  ��ŵ������Ĺ�����԰ٶ�
	public void move(int num,char a,char b, char c)
	{
		//���ֻ��һ���� num = 1
		if(num == 1)
		{
			System.out.println(a + "->" + c);
		}
		else
		{
			//����ж����   ���Կ���������  һ��������� ����  ����������� ������
			//1: ���ƶ��������е��̵� b �м���� c ?????????  ʲô������
			move(num - 1, a, c, b);          // -1����Ϊ�����������Ϊ1 ��ô���������̾�����number-1
			//2: ������������ƶ���c�Ϳ�����
			System.out.println(a + "->" + c);
			//3���ٰ�b���������ƶ���c   ����a
			move(num - 1, b, a, c);
		}
	}
}