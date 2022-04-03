import java.io.*;
import java.util.Vector;

@SuppressWarnings({"all"})

//记录类
public class Recorder {

    //设置击毁敌方坦克数量
    private static int enemyTankNum = 0;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recoder = "src\\myRecord.txt";
    //定义坦克数组
    private static Vector<Tank> tanks = null;
    //定义node的Vector 保存信息
    private static Vector<Node> nodes = new Vector<>();

    public static void setNodes(Vector<Node> nodes) {
        Recorder.nodes = nodes;
    }

    public static String getRecoder() {
        return recoder;
    }

    public static void setEnemyTanks(Vector<Tank> tanks) {
        Recorder.tanks = tanks;
    }

    public static int getEnemyTankNum() {
        return enemyTankNum;
    }

    public static void setEnemyTankNum(int enemyTankNum) {
        Recorder.enemyTankNum = enemyTankNum;
    }

    //用于读取recordFile 恢复相关信息
    public static Vector<Node> getNodesAndNum() throws IOException{
        br = new BufferedReader(new FileReader(recoder));
        enemyTankNum = Integer.parseInt(br.readLine());
        String line = "";
        while((line = br.readLine())!= null){
            String[] s1 = line.split(" ");
            Node node = new Node((Integer.parseInt(s1[0])),
                    (Integer.parseInt(s1[1])),
                    (Integer.parseInt(s1[2])));
            nodes.add(node);
        }
        br.close();
        return nodes;
    }
    //当击杀坦克时++
    public static void addEnemyTankNum(){
        Recorder.enemyTankNum++;
    }

    //游戏退出时 保存num到文件里
    public static void keepRecord() throws IOException {
        bw = new BufferedWriter(new FileWriter(recoder));
        bw.write(enemyTankNum+"");
        bw.newLine();
        for (int i = 0; i < tanks.size(); i++) {
            Tank tank = tanks.get(i);
            if(tank.isLive()){
                String record = tank.getX() +" "+ tank.getY() + " "
                       + tank.getDirect();
                bw.write(record);
                bw.newLine();
            }

        }

        bw.close();
    }
}
