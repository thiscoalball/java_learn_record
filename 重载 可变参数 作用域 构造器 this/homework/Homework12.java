class Homerwork12
{

}

class Employee
{
	//1 名字 性别 年龄  职位 薪水
	//2 名字 性别 年龄  3 职位 薪水
	//要求构造器的复用性高   
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
		//this(job,sal); 这样是错的   必须放在第一条
		this.job = job;
		this.sal = sal;
	}
}