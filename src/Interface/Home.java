package Interface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Home {
    public static void menu() throws IOException, InterruptedException {
            System.out.println("--------旅游区景点导游系统 V 1.0------");
            System.out.println("\n----欢迎使用本系统---");
            System.out.println("\n1.---景点信息查询与编辑---");
            System.out.println("\n2.-----当前位置及相邻景点----");
            System.out.println("\n3.--景点最短路径查询--");
            System.out.println("\n4.--景点简单路径查询--");
            System.out.println("\n5.--最佳景点观赏路线--");
            System.out.println("\n-------输入ctr+D退出系统");
            System.out.println("\n\n------请选择：");

    }
    public static void jiemian1(Graph g) throws IOException {
        allspot(g);
        System.out.println("-------请选择要查看和编辑的景点");
        System.out.println("-------添加请按”a“");
        System.out.println("-------返回请按”#“");
        System.out.println("-------退出请按“*”");
    }
    public static void jiemian2(Graph g){
        System.out.print("-----当前景点：");
        System.out.println(g.tralvist);
        System.out.println("相邻景点及距离：");
        int index=g.search0(g.tralvist);
        for (int i=0;i<g.vexnum;i++){
            if (g.arcs[index][i]>0){
                System.out.print(g.land[i]+"------距离:");
                System.out.println(g.arcs[index][i]+"km");
            }
        }
        System.out.println("-----输入#号返回");
    }
    public static void jiemian3(Graph g) throws IOException {
        allspot(g);
        System.out.println("------请选择查询景点");
        System.out.println("-----输入#号返回");
    }
    public static void jiemian31(Graph g,String order){
        int index = Integer.parseInt(order)-1;
        for (int i=0;i<g.vexnum;i++){
            if (i==index)
                continue;
            g.searchshortestpath(index,i);
        }
    }
    public static void jiemian4(Graph g) throws IOException {
        allspot(g);
        System.out.println("------请选择需要查询的两个景点(回车后输入下一个景点）");
        System.out.println("-----输入#号返回");
    }
    public static void jiemian5(Graph g) throws IOException, InterruptedException {
        allspot(g);
        System.out.println("------请选择需要查询的景点");
        System.out.println("-----输入#号返回");
        String order = Input.validInput(g.vexnum+1);
        youlan(g, Integer.parseInt(order)-1);
    }
    public static void youlan(Graph g,int start){
        System.out.println("---------推荐游览路线");
        ArrayList<int[]> path = new ArrayList<>();
        int[] br =new int[g.vexnum];
        for (int i=0;i<g.vexnum;i++){
            br[i]=i;
        }
        g.perm(br,0,g.vexnum-1,path);
        int [] re;
        int minindex=Integer.MAX_VALUE;
        String minpath = "";
        g.folyd();
        for (int i=0;i<path.size();i++){
            re = path.get(i);
            if (re[0]==start) {
                int min = 0;
                String mp = "";
                for (int j = 0, k = j + 1; j < g.vexnum-1; j++) {
                    min += g.distance[re[j]][re[k]];
                    mp += g.searchshortestpath1(re[j], re[k]) + " ";
                }
                if (minindex > min) {
                    minindex = min;
                    minpath = mp + g.land[path.get(i)[g.vexnum - 1]];
                }
            }
        }
        System.out.println("------"+minpath);
        System.out.println("------所需路程"+minindex+"km");
        System.out.println();
        System.out.println();
        System.out.println("-----输入#号返回");
    }
    public static void allspot(Graph g) throws IOException {
        System.out.println("------所有景点-------");
        for (int i=0;i<g.vexnum;i++){
            System.out.print("-------"+(i+1));
            System.out.println("."+g.land[i]);
        }
    }
}
