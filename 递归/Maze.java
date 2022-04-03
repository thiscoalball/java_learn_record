public class Maze
{
	public static void main(String[] args)
	{
		//----------------------------地图构建-----------------------------------
		//1：先用二维数组创建一个迷宫    8行 7列
		//2：规定 数组的 0 表示没有障碍物  1 表示障碍物
		int [][] map = new int[8][7];

		//3:将上下两行和左右两列置为障碍物
		for(int i = 0; i < 7; i++)
		{
			map[0][i] = 1;       //第一行的第一组元素全部变为1
			map[7][i] = 1;       //最后一行全为1
		}
		for(int i = 0; i < 8; i++)
		{
			map[i][0] = 1;       
			map[i][6] = 1;       
		}
		map[3][1] = 1;
		map[3][2] = 1;
		map[2][2] = 1;

		System.out.println("---------------当前的地图情况为-----------");//	地图检测函数
		for(int i = 0; i < map.length;i++)
		{
			for(int j = 0; j < map[i].length; j++)
			{
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		//---------------------------地图创建完成---------------------------------
		//使用findWay找路---------------------------------------------------
		T t1 = new T();
		t1.findWay(map, 1, 1);
		//-----------------------------------------------------------------
		//查看找路轨迹
		
		System.out.println("----------------找路情况如下----------------------");

		for(int i = 0; i < map.length;i++)
		{
			for(int j = 0; j < map[i].length; j++)
			{
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		//----------------------------------------------------------------
	}
}
class T
{
	//使用递归回溯的思想解决老鼠出迷宫的问题
	//findWay 用于找出迷宫的路径
	//找到 true   没找到返回false
	//参数说明 map为迷宫地图    i和j为走迷宫的初始位置（这里我们假设初始为（1，1）
	//0 表示无碍 1 表示障碍物 2 表示可以走  3 表示走过 但是走不通是死路
	//当 map[6][5] = 2   就说明找到通路 否则就继续
	//先确定老鼠找路策略   下-> 右边 -> 上 -> 左
	
	public boolean findWay(int[][] map, int i,int j) 
	{

		if(map[6][5] == 2)
		{
			return true;
		}
		else
		{
			if(map[i][j] == 0)  //可以走
			{
				//我们假定可以走通
				map[i][j] = 2;
				//使用找路的策略来确定该位置是否真的可以走通
				if(findWay(map,i+1,j)) //朝下走
				{
					return true;
				}
				else if(findWay(map,i,j+1)) //朝右走
				{
					return true;
				}
				else if(findWay(map,i - 1,j+1)) //朝上走
				{
					return true;
				}
				else if(findWay(map,i,j-1)) //朝左走
				{
					return true;
				}
				else //走不通  表示前面的假定是错误的
				{
					map[i][j] = 3;
					return false;
				}
			}
			else //要么等于 1 要么2 要么3
			{
				return false;   //例如地图中 第一次往下走后 死在里面  所以回溯到第一次的位置   因为是false 所以第78行的if不执行，执行底下那个朝右走找路
			}
		}
	}
}