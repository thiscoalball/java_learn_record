public class FloatDetail
{
	public static void main(String[] args)
	{
		//������ʹ������: 2.7 �� 8.1 / 3  �Ƚ�
		//����һ�δ���
		double num11 = 2.7;
		double num12 = 8.1/3;	//8.1 / 3; //2.7
		System.out.println(num11);//2.7
		System.out.println(num12);//�ӽ�2.7��һ��С����������2.7
		//�õ�һ����Ҫ��ʹ�õ�: �����Ƕ���������С���Ľ�������ж��ǣ�ҪС��
		//Ӧ�������������Ĳ�ֵ�ľ���ֵ����ĳ�����ȷ�Χ���ж�
		if( num11 == num12)
		{
			System.out.println("num11 == num12 ���");
		}
		//��ȷ����
		if(Math.abs(num11 - num12) < 0.000001 ) 
		{
			System.out.println("��ֵ�ǳ�С�����ҵĹ涨���ȣ���Ϊ���...");
		}
	}
}