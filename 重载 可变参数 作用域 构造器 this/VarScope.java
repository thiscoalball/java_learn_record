class Cat
{
	//全局变量 也就是属性 作用域为整个类体 Cat类：cry，eat等方法使用属性
	//全局变量（属性）可以不赋值 因为有默认值
	int age = 10;  //指定为10
	double weight; //默认变0.0

	public void cry()
	{
		//局部变量一般是在成员方法中定义的变量
		//n 和 name就是局部变量 n和name作用域在该方法中  类似c语言里函数内部的那种
		//局部变量必须赋值了才能使用
		int n = 10;
		String.name = "jack"
	}
}
class Person
{
//属性和局部变量可以重名  访问的时候遵顼就近原则
	String.name = "jack";
	public void say()
	{
		String name = "king";
	}
}



