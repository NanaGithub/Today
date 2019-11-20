package com.day.lib_java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;

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

    /**
     * 集合
     */
    private static void list() {
        ArrayList<String> arrayList = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();

        for (int i = 0; i < arrayList.size(); ++i) {
            System.out.print("\n i = ++i方法\t" + arrayList.get(i) + "\ti=" + i);
        }

        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print("\n i = i++方法\t" + arrayList.get(i) + "\ti=" + i);
        }


        HashSet<String> hashSet = new HashSet<>();
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

        HashMap<String, Object> hashMap = new HashMap();
    }
}
