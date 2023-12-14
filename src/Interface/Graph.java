package Interface;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    public String[] land;
    public String tralvist;
    public String[] info;
    public int vexnum;
    public int[][] arcs;
    public int[][] distance;
    public String[][] path;

    public Graph() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\旅游景点\\景点.txt", StandardCharsets.UTF_8));
        vexnum = Integer.parseInt(br.readLine());
        land = new String[vexnum];
        info    =   new String[vexnum];
        for(int i=0;i<vexnum;i++){
            String s = br.readLine();
            String[] s1 = s.split(":");
            land[i]=s1[0];
            info[i]=s1[1];
        }
        arcs = new int[vexnum][vexnum];
        for (int p = 0; p < vexnum; p++) {
            String s = br.readLine();
            String[] s1 = s.split(" ");
            for (int q = 0; q < vexnum; q++) {
                arcs[p][q] = Integer.parseInt(s1[q]);
            }
        }
        tralvist = land[0];
        br.close();
    }
//增加
    public void add(String s, String s0,String s1) throws IOException {
        vexnum++;
        land = Arrays.copyOf(land, land.length + 1);
        info = Arrays.copyOf(info, info.length + 1);
        land[vexnum - 1] = s;
        info[vexnum - 1] = s0;
        String[] a = s1.split(" ");
        int[][] temp = new int[vexnum][vexnum];
        for (int i = 0; i < vexnum - 1; i++) {
            for (int j = 0; j < vexnum - 1; j++) {
                temp[i][j] = arcs[i][j];
            }
        }
        for (int i = 0; i < vexnum; i++) {
            temp[vexnum - 1][i] = Integer.parseInt(a[i]);
            temp[i][vexnum - 1] = Integer.parseInt(a[i]);
        }
        arcs = temp;
        updata();
        System.out.println("----------添加成功");
    }
//删除
    public void delete(int index) throws IOException {
        if (index==vexnum-1){
            vexnum--;
            updata();
            return;
        }
        for (int i = index + 1; i < vexnum; i++) {
            land[i - 1] = land[i];
        }
        land = Arrays.copyOf(land, land.length - 1);
        for (int i = index + 1; i < vexnum; i++) {
            arcs[i - 1] = arcs[i];
        }
        for (int i = index + 1; i < vexnum; i++) {
            for (int j = 0; i < vexnum; j++) {
                arcs[i - 1][j] = arcs[i][j];
            }
        }
        vexnum--;
        updata();
        System.out.println("-------删除成功");
    }
//    找到用户输入位置的节点
    public void search(int i){
        System.out.println("-------景点名称"+land[i-1]);
        System.out.println("-------景点介绍:"+info[i-1]);
    }
//    通过节点名称找位置
    public int search0(String s){
        int i=0;
        for(String s1 : land){
            if(s.equals(s1)){
                return i;
            }
            i++;
        }
        return -1;
    }
//    更改景点介绍
    public void reset(int index,String s) throws IOException {
            info[index-1] = s;
            updata();
    }
//    找两点的最短路径并输出
    public void searchshortestpath(int index,int end){
            folyd();
            String s = land[index];
            String s1 = path[index][end];
            System.out.print("到"+land[end]+"的最短路径为："+s);
            while (s1!=land[end]){
                System.out.print("--->"+s1);
                int index1=search0(s1);
                s1=path[index1][end];
            }
            System.out.println("--->"+land[end]);
            System.out.println("-----距离："+distance[index][end]+"km");
    }
//    简单路径
    public String searchshortestpath1(int index,int end){
        folyd();
        String s = land[index];
        String s1 = path[index][end];
        while (s1!=land[end]){
            s+=" "+s1;
            int index1=search0(s1);
            s1=path[index1][end];
        }
        return s;
    }
//    数据更新
    public void updata() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\旅游景点\\景点.txt",StandardCharsets.UTF_8));
        String w = "";
        String m = String.valueOf(vexnum)+"\n";
        bw.write(m);
        for (int i = 0; i < vexnum; i++) {
            w = land[i]+":"+info[i]+ "\n";
            bw.write(w);
        }
        for (int i = 0; i < vexnum; i++) {
            w = "";
            for (int j = 0; j < vexnum; j++) {
                w += arcs[i][j];
                w +=" ";
            }
            w += "\n";
            bw.write(w);
        }
        bw.close();
    }
//    弗洛伊德找所有点的最短路径和距离
    public void folyd() {
        distance=new int[vexnum][vexnum];
        path =   new String[vexnum][vexnum];
//        初始化路径和距离
        for (int i=0;i<vexnum;i++){
            for (int j=0;j<vexnum;j++){
                distance[i][j]  =   arcs[i][j];
                path[i][j]=land[j];
            }
        }
        for (int i = 0; i < vexnum; i++) {
//            i 作为中转节点
            for (int j=0;j<vexnum;j++){
//                j 作为起始节点
                for (int k=0; k < vexnum; k++) {
//                    k 作为终止节点
                    if (distance[j][i] != -1 && distance[i][k] != -1) {
//                        如中转点无法到达起始和终点
                        int newDistance = distance[j][i] + distance[i][k];
                        if (newDistance < distance[j][k] || distance[j][k] == -1) {
                            distance[j][k] = newDistance;
                            path[j][k] =  path[j][i];
                        }
                    }
                }
            }
        }
    }

    public void DFS0(int start,int end){
        boolean[] flag = new boolean[vexnum];
        String path0 = land[start];
        int dt =0;
        System.out.println("-----到"+land[end]+"的所有简单路径如下：");
        DFS(start,end,flag,path0,dt);
    }
    public void DFS(int start,int end,boolean[] flag,String path0,int dt){
        if (start==end){
            System.out.println(path0);
            System.out.println("-----距离："+dt+"km");
        }
        flag[start]=true;
        for (int i=0;i<vexnum;i++){
            if (arcs[start][i]>0&&!flag[i]){
                DFS(i,end,flag,path0+"-->"+land[i],dt+arcs[start][i]);
            }
        }
        flag[start]=false;
    }
    public void perm(int[] br, int start, int end, ArrayList<int[]> path){
        if (start==end){
            path.add(Arrays.copyOf(br, br.length));
        }
        else {
            int temp;
            for (int i = start; i <= end; i++) {
                temp = br[i];
                br[i] = br[start];
                br[start] = temp;
                perm(br, start + 1, end, path);
                temp = br[i];
                br[i] = br[start];
                br[start] = temp;
            }
        }
    }
}
