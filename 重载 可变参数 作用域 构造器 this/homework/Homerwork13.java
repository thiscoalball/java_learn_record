class Homerwork13
{
	public static void main(String[] args) 
	{
		Circle c = new Circle();
		PassObject po = new PassObject();
		po.printAreas(c,5);
	}
}
class Circle
{
	double r;
	public Circle()     

	{
		this.r = r;
	}
	public Circle(double r)
	{
		this.r = r;
	}
	public double findArea()
	{
		double s;
		return s = (this.r)*(this.r)*Math.PI;
	}
	public void setR(double r)
	{
		this.r = r;
	}
}
class PassObject
{
	public void printAreas(Circle c, int times)
	{
			System.out.println("r      \tarea");
		for(int i = 1; i <= times; i++)
		{
			c.setR(i);
		
			
			System.out.println(i + "\t" + c.findArea());
		}
	}
}