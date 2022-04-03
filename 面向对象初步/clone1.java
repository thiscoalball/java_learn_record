class clone1
{
	public static void main(String[] args)
	{
		Person p = new Person();
		p.name = "hhhh";
		p.age = 100;

		CloneTool tool = new CloneTool();
		Person p1 = tool.clonePerson(p);
		System.out.println("	 p的名字为"+ p.name + "\t" + "p的姓名" + p.age);
		System.out.println("克隆的p1的名字为"+ p1.name + "\t" + "p的姓名" + p1.age);


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
		Person p1 = new Person();//这样重新开辟一块地址  防止像之前那样引用传递导致改了别人的数据
		p1.name = p.name;
		p1.age = p.age;
		return p1;
	}
}