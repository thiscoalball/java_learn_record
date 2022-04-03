class Homework05
{
	public static void main(String[] args) 
	{
		Circle circle = new Circle(5);
		circle.show();
	}
}
class Circle
{
	double r;
	public Circle(double r)
	{
		this.r = r;
	}
	public void show()
	{
		double length = Math.PI * 2*(this.r);
		double s = (this.r)*(this.r)*Math.PI;
		System.out.println("圆的周长为：" + length);
		System.out.println("圆的面积为：" + s);
	}
}