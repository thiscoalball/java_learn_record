class Homerwork12
{

}

class Employee
{
	//1 ���� �Ա� ����  ְλ нˮ
	//2 ���� �Ա� ����  3 ְλ нˮ
	//Ҫ�������ĸ����Ը�   
	String name;
	char gender;
	int age;
	String job;
	double sal;
	public Employee(String name, char gender, int age)
	{
		this.name = name;
		this.gender = gender;
		this.age = age;
	}
	public Employee(String job, double sal)
	{
		this.job = job;
		this.sal = sal;
	}
	public Employee(String name, char gender, int age, String job, double sal)
	{
		this(name,gender,age);
		//this(job,sal); �����Ǵ��   ������ڵ�һ��
		this.job = job;
		this.sal = sal;
	}
}