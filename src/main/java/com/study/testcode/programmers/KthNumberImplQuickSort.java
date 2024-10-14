package com.study.testcode.programmers;

import org.springframework.stereotype.Service;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/42748
// 배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구함
// quick sort 직접구현 참고 -> https://www.youtube.com/watch?v=cWH49IKDIiI
@Service
public class KthNumberImplQuickSort {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int i = 0; i < commands.length; i++) {
            int[] targetArr = Arrays.copyOfRange(array, commands[i][0] - 1, commands[i][1]);
            quickSort(targetArr, 0, targetArr.length - 1);
            answer[i] = targetArr[commands[i][2] - 1];
        }

        return answer;
    }

    private static void quickSort(int[] array, int left, int right) {
        if (left < right) { // left와 right가 같을때 끝나는 조건문, 정복을 담당
            int pivot = partition(array, left, right);

            quickSort(array, left, pivot - 1);
            quickSort(array, pivot + 1, right);
        }
    }

    private static int partition(int[] array, int left, int right) {
        int pivot = array[right]; // 가장 오른쪽을 기준으로잡고
        int i = (left - 1);

        for (int j = left; j <= right - 1; j++) { // j는 가장 왼쪽 부터 시작 피봇 직전까지 순회
            if (array[j] <= pivot) { // 피봇보다 작으면 i와 j를 스왑
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, right);
        return (i + 1);
    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
