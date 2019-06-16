package com.day.lib_java;

public class MyClass {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        StringBuffer sbu = new StringBuffer();

        String str = "sada";
        char[] c = new char[]{'1', '2', '4', '4'};
        char[] toC = new char[]{'0', '0', '0', '0', '0', '0'};
        str.getChars(0, 3, c, 1);
        System.out.print(c);
        System.arraycopy(c, 0, toC, 0, 4);
        System.out.print(toC);

    }
}
