package com.study.testcode.programmers;

import org.springframework.stereotype.Service;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/42748
// 배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구함
@Service
public class KthNumber {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int i = 0; i < commands.length; i++) {
            int[] targetArr = Arrays.copyOfRange(array, commands[i][0] - 1, commands[i][1]);
            Arrays.sort(targetArr);
            answer[i] = targetArr[commands[i][2] - 1];
        }

        return answer;
    }
}
