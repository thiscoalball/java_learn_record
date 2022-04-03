import java.util.Scanner;
class ArrAdd1
{
	public static void main(String[] args)
	{
		//动态的给数组添加元素   实现对数组的扩容
		//增加的元素直接放在数组最后 用户 可以选择是否继续 添加
		//添加成功是否继续 y/n ???
		
		//思路 定义新的数组 长度为arr.length + 1  
		//遍历 然后赋值到arrNew 将需要赋值的数据 赋值到arrNew[arrNew.length - 1] = 数据
		//让arr指向arrNew      原数组被垃圾回收
		
		Scanner myScanner =  new Scanner(System.in);
		int[] arr = {1, 2, 3};
		//这个方法真的笨    优化方法多的一笔
		do
		{
			int[] arrNew = new int[arr.length + 1];
			for(int i = 0; i < arr.length; i++)
			{
				arrNew[i] = arr[i];
			}
			System.out.println("请输入你要添加的元素：");

			int addNum = myScanner.nextInt();
			arrNew[arrNew.length - 1] = addNum;
 
			arr = arrNew;

			for(int i = 0; i < arr.length ; i++)
			{
				System.out.print(arr[i] + " ");
			}

			System.out.println("是否继续添加 请输入y/n");

			char key = myScanner.next().charAt(0);
			if(key == 'n')
			{
				break;
			}
			
		}while(true);

	}
}