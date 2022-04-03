//我是真的看不懂这种这串代码的逻辑
//??????????????????????????????????????????????????????????
public class HanNuoTa
{
	public static void main(String[] args)
	{
		T tower = new T();
		tower.move(4,'A','B','C');
	}
}

class T
{
	//num 表示要移动的盘的个数1     a b c 表示三个可以放的柱子  最终所有盘子要移动到c  汉诺塔具体的规则可以百度
	public void move(int num,char a,char b, char c)
	{
		//如果只有一个盘 num = 1
		if(num == 1)
		{
			System.out.println(a + "->" + c);
		}
		else
		{
			//如果有多个盘   可以看成两个：  一：最下面的 二：  上面的所有盘 ？？？
			//1: 先移动上面所有的盘到 b 中间借助 c ?????????  什么东西啊
			move(num - 1, a, c, b);          // -1是因为最下面的盘算为1 那么上面所有盘就属于number-1
			//2: 把最下面的盘移动到c就可以了
			System.out.println(a + "->" + c);
			//3：再把b的所有盘移动到c   借助a
			move(num - 1, b, a, c);
		}
	}
}