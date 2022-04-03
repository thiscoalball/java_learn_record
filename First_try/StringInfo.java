public class StringInfo
{
	public static void main(String[] args)
	{
		String s1 = "123";
		int num1 = Integer.parseInt(s1);
		long num2 = Long.parseLong(s1);
		double num3 = Double.parseDouble(s1);
		byte num4 = Byte.parseByte(s1);
		float num5 = Float.parseFloat(s1);
		
		System.out.println(num1);
		System.out.println(num2);
		System.out.println(num3);
		System.out.println(num4);
		System.out.println(num5);

	}
}