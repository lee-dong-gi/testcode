package com.study.testcode.programmers;

import org.springframework.stereotype.Service;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/12981
@Service
public class EnglishWordChainNonCheating {

    public int[] solution(int n, String[] words) {
        Set<String> dupCheckSet = new HashSet<>(); // 중복체크를 위한 HashSet
        int count = 1; // 차례
        int num = 1; // 번호
        String prev = String.valueOf(words[0].charAt(0)); // 첫단어는 패스하도록 세팅
        for (int i = 0; i < words.length; i++) {
            String target = words[i];
            boolean result = checkWordChain(prev, target, dupCheckSet);

            if (result) {
                if (words.length - 1 == i) { // 마지막 순번인데 통과라면 실패한 사람이 없다는 뜻
                    num = 0;
                    count = 0;
                }
                prev = target;
            } else {
                break;
            }

            if (words.length - 1 != i) {
                if (num == n) { // 번호 계산
                    num = 1;
                } else {
                    num++;
                }

                if ((i + 1) % n == 0) { // 차례 계산
                    count++;
                }
            }
        }

        return new int[]{num, count};
    }

    private static boolean checkWordChain(String prev, String now, Set<String> dupCheckSet) {
        // 중복체크
        if (dupCheckSet.contains(now)) {
            return false;
        } else {
            dupCheckSet.add(now);
        }

        // 이전단어 마지막글자와 현단어 첫글자가 다르면 false
        return prev.substring(prev.length() - 1).equals(now.substring(0, 1));
    }
}
