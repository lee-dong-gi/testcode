package com.study.testcode.programmers;

import org.springframework.stereotype.Service;

// https://school.programmers.co.kr/learn/courses/30/lessons/12981
@Service
public class EnglishWordChain {

    public int[] solution(int n, String[] words) {
        int count = 1; // 차례
        int num = 1; // 번호
        String prev = String.valueOf(words[0].charAt(0)); // 첫단어는 패스하도록 세팅
        for (int i = 0; i < words.length; i++) {
            String target = words[i];
            boolean result = checkWordChain(words, prev, target, i);

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
                if (num == n) {
                    num = 1;
                } else {
                    num++;
                }

                if ((i + 1) % n == 0) {
                    count++;
                }
            }
        }

        return new int[]{num, count};
    }

    private static boolean checkWordChain(String[] words, String prev, String now, int i) {
        if (i == 0) {
            return true;
        }

        if (!prev.substring(prev.length() - 1).equals(now.substring(0 , 1))) {
            return false;
        }

        for (int j = i - 1; j >= 0; j--) { // 현 단어 기준 이전 단어들만 검사
            if (words[j].equals(now)) {
                return false;
            }
        }

        return true;
    }
}
