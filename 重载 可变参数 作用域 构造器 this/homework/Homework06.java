class Homework06
{
	public static void main(String[] args) 
	{
		Cale cal = new Cale(2,0);
		System.out.println("ºÍÎª " + cal.Add());
		System.out.println("²îÎª " + cal.Sub());
		System.out.println("³ËÎª " + cal.Mul());
		Double divRes = cal.Div();
		if(divRes != 0)
		{
		System.out.println("³ýÎª " + cal.Sub());
		}
	}
}

class Cale
{
	double num1;
	double num2;
	public Cale(double num1,double num2)
	{
		this.num1 = num1;
		this.num2 = num2;
	}
	public double Add()
	{
		return num1 + num2;
	}
	public double Sub()
	{
		return num1 - num2;
	}
	public double Mul()
	{
		return num1 * num2;
	}
	public Double Div()
	{
		if(num2 == 0)
		{
			System.out.println("³ýÊý²»ÄÜÎª0");
			return null;
		}
		return num1 / num2;
	}
}