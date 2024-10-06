package com.study.testcode.programmers;

import org.springframework.stereotype.Service;

// https://school.programmers.co.kr/learn/courses/30/lessons/340199
@Service
public class BillFoldingNonCheating {

    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        int[] foldBill = {bill[0], bill[1]};

        while (checkScale(wallet, foldBill)) {
            if (foldBill[0] > foldBill[1]) {
                foldBill[0] = foldBill[0] / 2;
            } else {
                foldBill[1] = foldBill[1] / 2;
            }
            answer++;
        }

        return answer;
    }

    private static boolean checkScale(int[] wallet, int[] foldBill) {
        return getSmallestValue(foldBill) > getSmallestValue(wallet)
                || getLargestValue(foldBill) > getLargestValue(wallet);
    }

    private static int getSmallestValue(int[] target) {
        return Math.min(target[0], target[1]);
    }

    private static int getLargestValue(int[] target) {
        return Math.max(target[0], target[1]);
    }
}
