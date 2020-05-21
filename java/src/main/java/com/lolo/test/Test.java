package com.lolo.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        ArrayList<Integer> ods = new ArrayList<>();
        ArrayList<Integer> even = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        Scanner sc1 = new Scanner(System.in);
        String line = sc1.nextLine();

        String[] nums = line.split(" ");

        for (String s : nums) {
            int num = Integer.parseInt(s);
            if (num % 2 == 0) {
                ods.add(num);
            }
            else {
                even.add(num);
            }
        }

        int[] odsNum = new int[ods.size()];
        int[] evenNum = new int[even.size()];
        for (int i = 0; i < ods.size(); i++) {
            odsNum[i] = ods.get(i);
        }
        for (int i = 0; i < even.size(); i++) {
            evenNum[i] = even.get(i);
        }

        Arrays.sort(odsNum);
        Arrays.sort(evenNum);

        int[] odsDiff = new int[ods.size()-1];
        int[] evenDiff = new int[even.size()-1];

        int odsMax = odsNum[0];
        int evenMax = evenNum[0] - 1;
        int max;

        if (100 - odsNum[odsNum.length - 1] > odsMax) {
            odsMax = 100 - odsNum[odsNum.length - 1];
        }

        for (int i = 0; i < odsNum.length - 1; i++) {
            for (int j = i+1; j < odsNum.length; j++) {
                if (j - i >= 2)
                    break;
                odsDiff[i] = odsNum[j] - odsNum[i];
                if (odsDiff[i] > odsMax) {
                    odsMax = odsDiff[i];
                }
            }
        }

        for (int i = 0; i < evenNum.length - 1; i++) {
            for (int j = i+1; j < evenNum.length; j++) {
                if (j - i >= 2)
                    break;
                evenDiff[i] = evenNum[j] - evenNum[i];
                if (evenDiff[i] > evenMax) {
                    evenMax = evenDiff[i];
                }
            }
        }


        if (evenMax > odsMax){
            max = evenMax;
        }
        else {
            max = odsMax;
        }

//        System.out.println(odsMax);
//        System.out.println(evenMax);
//        System.out.println(max);

        System.out.println(max / 2);
    }
}
