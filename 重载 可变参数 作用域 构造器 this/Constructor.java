//���������η�����Ĭ�� Ҳ������ public protected private
//û�з���ֵ
//����������������һ��      �����б�ͳ�Ա�����Ĺ�����һ����
//�������ĵ�����ϵͳ���
//��ʵ����c++��Ĭ�Ϲ��죨ʲô�޲ι��죬�вι���

class Constructor
{
	public static void main(String[] args)
	{
		//newһ������ʱ ֱ��ͨ��������ָ�����ֺ�����
		System.out.println();
		Person p1 = new Person("jason",30);
		System.out.println("p1����Ϣ:" + p1.name +"\t" + "p1������Ϊ:" + p1.age);
		System.out.println();
		Person p2 = new Person("jack");
		System.out.println("p2����Ϣ:" + p2.name );
		//�ⲿ��Ҫ���ù�������     ����c++��Ĭ�Ϲ���������Щ�� ����һ��
		//����Ա��������幹����  �ͻ�����һ��Ĭ���޲ι���
		//���Ƕ����ͻ������вι��� �޲ι��첻����Ч ����ͺ�c++һģһ����
		//�����޲ι��������д��age = 18 ��ôÿ������ʱ�����ʼ��һ��age = 18����
		//��������вΣ������޲�  �ǾͿ���newһ��������ʱ�򴫵ݵĲ���  ��ʲô�͵����ĸ�
	}
}


class Person
{
	String name;
	int age;
	// ���캯��û�з���ֵҲ����дvoid
	public Person(String pName, int pAge)
	{
		System.out.println("������������");
		name = pName;
		age = pAge;
	}
	//ʹ��ϸ�� һ������Զ�������ͬ�Ĺ����� ����ͬ�Ĳ���
	public Person(String pName)
	{
		System.out.println("�ڶ���������������");
		name = pName;
	}


}