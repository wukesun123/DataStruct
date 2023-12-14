package Interface;

import java.util.Scanner;

public class Input {

    public static String validInput(String... strings) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String str = "";
            if (scanner.hasNextLine())
                str = scanner.nextLine();
            else safeExit();
            str = str.trim();
            for (String data : strings) {
                if (data.equals(str)) {
                    return data;
                }
            }
            System.out.print("输入无效,请重新输入,回车确认：");
        }
    }
    public static String validInput(int maxRange) throws InterruptedException {
        String[] strings = new String[maxRange +3];
        int i;
        for (i = 0; i < maxRange; ++i) {
            strings[i] = "" + (i+1);
        }
        strings[maxRange] = "*";
        strings[maxRange +1] = "#";
        strings[maxRange+2] = "a";
        return validInput(strings);
    }
    public static String excludeInput(int maxRange, String... invalidStrings) throws InterruptedException {
        String[] strings = new String[maxRange +2];
        int i;
        for (i = 0; i < maxRange; ++i) {
            strings[i] = "" + i;
        }
        strings[maxRange] = "*";
        strings[maxRange +1] = "#";
        Scanner scanner = new Scanner(System.in);
        outer:
        while (true) {
            String str = "";
            if (scanner.hasNextLine())
                str = scanner.nextLine();
            else safeExit();
            str = str.trim();
            for (String invalidStr : invalidStrings) {
                if (str.equals(invalidStr)) {
                    System.out.print("输入无效,请重新输入,回车确认：");
                    continue outer;
                }
            }
            for (String data : strings) {
                if (data.equals(str)) {
                    return data;
                }
            }
            System.out.print("输入无效,请重新输入,回车确认：");
        }
    }

    public static String contentInput(String str) throws InterruptedException {
        System.out.println("请输入" + str + "内容  :");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine())
            str = scanner.nextLine();
        else safeExit();
        str = str.trim();
        if (str.isEmpty())  str = "未检测到输入内容";
        return str;
    }
    private static void safeExit() throws InterruptedException {
        System.out.println("--------正在退出");
            Thread.sleep(1500);
            System.out.println("-----感谢您的使用");
            System.exit(0);
            System.out.println();
    }
}
