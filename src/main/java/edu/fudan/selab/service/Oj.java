package edu.fudan.selab.service;

import java.util.Scanner;

public class Oj {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String s1 = in.next();
        String s2 = in.next();
        System.out.println(cal(s1,s2));
    }
    public static String cal(String s1,String s2){
        int[] res = new int[s1.length()+s2.length()];
        for(int i =0;i <s1.length();i ++){
            int a1 = s1.charAt(i) - '0';
            for(int j = 0;j < s2.length();j ++){
                int a2 = s2.charAt(j) - '0';
                res[i + j + 1] += a1 * a2;
            }
        }
        for(int k = res.length - 1;k >0;k --){

                res[k - 1] += res [k] /10;
                res[k] = res[k] % 10;

        }
        StringBuffer s = new StringBuffer();
        for(int i = 0;i < res.length; i++){
            if(i == 0 && res[i] == 0)
                continue;
            s.append(String.valueOf(res[i]));
        }
        return s.toString();
    }
}
