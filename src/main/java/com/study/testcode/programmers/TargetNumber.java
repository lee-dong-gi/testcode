package com.study.testcode.programmers;

import org.springframework.stereotype.Service;

@Service
public class TargetNumber {
    public int dfsRecursive(int[] numbers, int target, int idx, int sum) {
        if (idx == numbers.length) {
            return target == sum ? 1 : 0;
        }

        int result = 0;

        // 배열 크기 횟수 만큼 더하고 빼고를 재귀적으로 모든 경우의 수를 수행
        // 경우의 수 마다 +와 -값으로 분기되어 배열의 크기 깊이 만큼 탐색을 수행
        result = result + dfsRecursive(numbers, target, idx + 1, sum + numbers[idx]);
        result = result + dfsRecursive(numbers, target, idx + 1, sum - numbers[idx]);

        return result;
    }
}
