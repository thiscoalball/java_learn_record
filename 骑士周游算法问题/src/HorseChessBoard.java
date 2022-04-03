import java.awt.*;
import java.util.ArrayList;
@SuppressWarnings({"All"})
public class HorseChessBoard {


    //骑士周游问题 也叫马踏棋盘算法问题
    private static int X = 6; //col
    private static int Y = 6; //row
    private static int[][] chessBoard = new int[Y][X];
    //记录某个位置是否走过的数组
    private static boolean[] visited = new boolean[X * Y];
    //记录是否遍历完
    private static boolean finished = false;
    public static void main(String[] args) {
        int row = 5;
        int col = 5;

        long start = System.currentTimeMillis();
        traversalChessBoard(chessBoard,row-1,col-1,1);
        long end = System.currentTimeMillis();
        System.out.println("耗时"+ (end-start));
        for(int[] rows : chessBoard){
            for(int step : rows){
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }


    //编写核心算法遍历棋盘 如果遍历成功就把 finished设置为true并且将马走的每一步step写入到棋盘里
    public static void traversalChessBoard(int[][] chessBoard,int row,int col,int step){
        //先把step记录到棋盘中
        chessBoard[row][col] = step;
        //把这个位置设置为已经访问 并记录是第几步走到的
        visited[row * X + col] = true;
        //获取当前位置可以走的下一个位置有哪些
        ArrayList<Point> next = next(new Point(col, row));
        //遍历 如果不为空 就取出来
        while (!next.isEmpty()){
            //从0开始取
            Point point = next.remove(0);
            //判断该位置是不是走过 如果没有走过 就递归遍历
            if(!visited[point.y * X + point.x]){
                //递归遍历 因为已经是下一步了 所以 step + 1
                traversalChessBoard(chessBoard,point.y,point.x,step+1);
            }
        }
        //当退出while时 查看是否遍历成功 如果没有成功就重置对应的值进行回溯
        if(step < X*Y && !finished){
            chessBoard[row][col] = 0;
            visited[row * X + col] = false;
        } else {
            finished = true;
        }
    }



    //编写方法获取到当前位置：可以走的下一步的所有位置
    public static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> points = new ArrayList<>();

        //创建一个点 准备放进去 判断马的八个位置是不是都可以走
        Point point = new Point();
        if( (point.x = curPoint.x-2)>=0 && (point.y = curPoint.y -1)>=0){
            points.add(new Point(point));//这里应该new一个新点放进去，不然点就放到多个位置了
        }
        if( (point.x = curPoint.x-1)>=0 && (point.y = curPoint.y -2)>=0){
            points.add(new Point(point));//这里应该new一个新点放进去，不然点就放到多个位置了
        }
        if( (point.x = curPoint.x+1) < X && (point.y = curPoint.y -2)>=0){
            points.add(new Point(point));//这里应该new一个新点放进去，不然点就放到多个位置了
        }
        if( (point.x = curPoint.x+2) < X && (point.y = curPoint.y -1)>=0){
            points.add(new Point(point));//这里应该new一个新点放进去，不然点就放到多个位置了
        }
        if( (point.x = curPoint.x+2) < X && (point.y = curPoint.y + 1) < Y){
            points.add(new Point(point));//这里应该new一个新点放进去，不然点就放到多个位置了
        }
        if( (point.x = curPoint.x+1) < X && (point.y = curPoint.y + 2) < Y){
            points.add(new Point(point));//这里应该new一个新点放进去，不然点就放到多个位置了
        }
        if( (point.x = curPoint.x-1) >= 0 && (point.y = curPoint.y + 2) < Y){
            points.add(new Point(point));//这里应该new一个新点放进去，不然点就放到多个位置了
        }
        if( (point.x = curPoint.x-2) >= 0 && (point.y = curPoint.y + 1) < Y){
            points.add(new Point(point));//这里应该new一个新点放进去，不然点就放到多个位置了
        }

        return points;
    }
}
