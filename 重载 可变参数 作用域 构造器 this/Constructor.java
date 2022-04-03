//构造器修饰符可以默认 也可以是 public protected private
//没有返回值
//方法名和类名必须一样      参数列表和成员方法的规则是一样的
//构造器的调用由系统完成
//其实就是c++的默认构造（什么无参构造，有参构造

class Constructor
{
	public static void main(String[] args)
	{
		//new一个对象时 直接通过构造器指定名字和年龄
		System.out.println();
		Person p1 = new Person("jason",30);
		System.out.println("p1的信息:" + p1.name +"\t" + "p1的年龄为:" + p1.age);
		System.out.println();
		Person p2 = new Person("jack");
		System.out.println("p2的信息:" + p2.name );
		//外部不要调用构造器了     就是c++的默认构造类型那些的 几乎一样
		//程序员如果不定义构造器  就会生成一个默认无参构造
		//我们定义后就会生成有参构造 无参构造不再生效 （真就和c++一模一样）
		//但是无参构造里比如写个age = 18 那么每次生成时都会初始化一个age = 18的人
		//如果又有有参，又有无参  那就看你new一个变量的时候传递的参数  传什么就调用哪个
	}
}


class Person
{
	String name;
	int age;
	// 构造函数没有返回值也不能写void
	public Person(String pName, int pAge)
	{
		System.out.println("构造器被调用");
		name = pName;
		age = pAge;
	}
	//使用细节 一个类可以定义多个不同的构造器 传不同的参数
	public Person(String pName)
	{
		System.out.println("第二个构造器被调用");
		name = pName;
	}


}