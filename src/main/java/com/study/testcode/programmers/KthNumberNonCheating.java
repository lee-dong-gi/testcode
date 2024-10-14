package com.study.testcode.programmers;

import org.springframework.stereotype.Service;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/42748
// 배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구함
@Service
public class KthNumberNonCheating {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int cnt = 0; cnt < commands.length; cnt++) {
            int i = commands[cnt][0] - 1, j = commands[cnt][1] - 1, k = commands[cnt][2] - 1;
            int[] targetArr = new int[j - i + 1];
            int targetArrIdx = 0;
            for (int arrIdx = i; arrIdx <= j; arrIdx++) {
                targetArr[targetArrIdx++] = array[arrIdx];
            }

            Arrays.sort(targetArr);
            answer[cnt] = targetArr[k];
        }

        return answer;
    }
}
