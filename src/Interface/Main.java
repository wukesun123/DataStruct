package Interface;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String order;
        Graph g = new Graph();
        menu:
        while(true){//
            Home.menu();
            order=Input.validInput("1", "2", "3", "4", "5");
                switch (order) {
                    case "*":break menu;
                    case "1":
                        menu1:
                        while (true) {
                            Home.jiemian1(g);
                            order = Input.validInput(g.vexnum+1);
                            switch (order) {
                                case "*":break menu;
                                case "#":break menu1;
                                case "a":
                                    order = Input.contentInput("景点");
                                    String ifo = Input.contentInput("介绍");
                                    String dis = Input.contentInput("与各个景点间的距离");
                                    g.add(order,ifo,dis);
                                    break menu1;
                                default:
                                    int index = Integer.parseInt(order);
                                    g.search(index);
                                    System.out.println("---------更改请按1");
                                    System.out.println("---------删除请按2");
                                    System.out.println("---------到达请按3");
                                    System.out.println("---------返回请按#");
                                    order=Input.validInput("1","2","3","#");
                                    if (order.equals("#"))
                                        break menu1;
                                    else if (order.equals("1")){
                                        order = Input.contentInput("更改");
                                        g.reset(index, order);
                                    }
                                    else if (order.equals("2")){
                                            g.delete(index-1);
                                    }
                                    else {
                                        g.tralvist = g.land[index-1];
                                    }
                            }
                        }
                        break;
                    case "2":
                        menu2:
                        while (true){
                            Home.jiemian2(g);
                            order = Input.validInput("#","*");
                            if (order.equals("#"))
                            break menu2;
                            break menu;
                        }
                        break ;
                    case "3":
                        menu3:
                        while (true){
                            Home.jiemian3(g);
                            order = Input.validInput(g.vexnum+1);
                            if (order.equals("#"))
                                break menu3;
                            Home.jiemian31(g,order);
                            System.out.println("-----输入#号返回");
                            order =Input.validInput("#");
                        }
                        break ;
                    case "4":
                        menu4:
                        while (true){
                            Home.jiemian4(g);
                            order = Input.validInput(g.vexnum+1);
                            if (order.equals("#"))
                            break menu4;
                            String end =Input.excludeInput(g.vexnum,order);
                            g.DFS0(Integer.parseInt(order)-1,Integer.parseInt(end)-1);
                            g.searchshortestpath(Integer.parseInt(order)-1,Integer.parseInt(end)-1);
                            System.out.println("-----输入#号返回");
                            order =Input.validInput("#");
                        }
                        break ;
                    case "5":
                        menu5:
                        while (true){
                            Home.jiemian5(g);
                            order =Input.validInput("#");
                            if (order.equals("#"))
                                break menu5;
                        }
                        break ;
                }
            }
        }
    }

