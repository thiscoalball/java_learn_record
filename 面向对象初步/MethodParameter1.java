class MethodParameter1
{
	public static void main(String[] args)
	{
		int a = 10;
		int b = 20;
		AA obj = new AA();
		obj.swap1(a,b);

		System.out.println("����swap1�������ֵΪ   "+ a + "\t" + b);

	}
}
class AA
{
	//�������ε��ⲿ���޷��ɹ����ε� ������c���Բ���ָ��һ�� �βθı䲻��Ӱ��ʵ��
	//�������������������Ͷ���� ���� ���͵ĵ���   �ڲ��ı�ͻ�Ӱ���ⲿ    ���ô��ݵ��ǵ�ַ
	//������c���Ի��� ����Ͳ���дһ�������  ���鵽���������֪����
	public void swap1(int n1,int n2)
	{
		System.out.println("a��b��swap�н���ǰ��ֵΪ"+ n1 + "\t" + n2);
		int temp  = n1;
		n1 = n2;
		n2 = temp;
		System.out.println("a��b��swap�󽻻����ֵΪ"+ n1 + "\t" + n2);

	}
}