class clone1
{
	public static void main(String[] args)
	{
		Person p = new Person();
		p.name = "hhhh";
		p.age = 100;

		CloneTool tool = new CloneTool();
		Person p1 = tool.clonePerson(p);
		System.out.println("	 p������Ϊ"+ p.name + "\t" + "p������" + p.age);
		System.out.println("��¡��p1������Ϊ"+ p1.name + "\t" + "p������" + p1.age);


	}
}
class Person
{
	String name;
	int age;
}
class CloneTool
{
	public Person clonePerson(Person p)
	{
		Person p1 = new Person();//�������¿���һ���ַ  ��ֹ��֮ǰ�������ô��ݵ��¸��˱��˵�����
		p1.name = p.name;
		p1.age = p.age;
		return p1;
	}
}