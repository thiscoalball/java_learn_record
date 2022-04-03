class Homework03
{
	public static void main(String[] args) 
	{
		Book b = new Book("平凡的世界",170.0);
		b.info();
		System.out.println("价格进行修改后");
		b.UpdatePrice();
		b.info();


	}
}
class Book 
{
	String name;
	double price;
	public Book(String name, double price)
	{
		this.name = name;
		this.price = price;
	}
	public void UpdatePrice()
	{
		if(this.price > 150)
		{
			this.price = 150;
		}
		else if(this.price > 100)
		{
			this.price = 100;
		}
	}
	public void info()
	{
		System.out.println("书名为：" + this.name);
		System.out.println("价格为：" + this.price);
	}
}