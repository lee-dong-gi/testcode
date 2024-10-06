package com.study.testcode.programmers;

import org.springframework.stereotype.Service;

// https://school.programmers.co.kr/learn/courses/30/lessons/70129
@Service
public class RepeatBinaryConversionNonCheating {

    public int[] solution(String s) {
        int[] counts = {0, 0};
        binaryConvert(s, counts);
        return counts;
    }

    private static void binaryConvert(String target, int[] counts) {
        char[] targetCharArray = target.toCharArray();
        for (char val : targetCharArray) {
            if ('0' == val) {
                counts[1]++;
            }
        }

        String rmZeroTarget = target.replace("0", "");
        int len = rmZeroTarget.length();
        String binaryString = Integer.toBinaryString(len);

        counts[0]++;

        if ("1".equals(binaryString)) {
            return;
        }

        binaryConvert(binaryString, counts);
    }

}
