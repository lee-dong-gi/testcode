package com.study.testcode.programmers;

import java.util.*;

import org.springframework.stereotype.Service;

// https://www.youtube.com/watch?v=0bqfTzpWySY 참고
// Dynamic Programming 이란 중복계산을 없애는 것 소위 기억하는 프로그래밍이라고도 함
@Service
public class IntegerTriangle {
    public int mySolution(int[][] triangle) {
        int[][] triangleCopy = new int[triangle.length][];
        for (int i = 0; i < triangle.length; i++) {
            triangleCopy[i] = new int[triangle[i].length];
        }

        dp(triangle, triangleCopy);
        int[] lastLine = triangleCopy[triangleCopy.length - 1];
        int answer = 0;
        for (int line : lastLine) {
            if (answer < line) {
                answer = line;
            }
        }

        return answer;
    }

    private void dp(int[][] triangles, int[][] trianglesCopy) {
        trianglesCopy[0][0] = triangles[0][0];
        for (int i = 1; i < triangles.length; i++) {
            int[] values = triangles[i];
            int[] prevValues = trianglesCopy[i - 1];

            for (int j = 0; j < values.length - 1; j++) {
                int value0 = values[j] + prevValues[j];
                if (value0 > trianglesCopy[i][j]) {
                    trianglesCopy[i][j] = value0;
                }

                int value1 = values[j + 1] + prevValues[j];
                if (value1 > trianglesCopy[i][j + 1]) {
                    trianglesCopy[i][j + 1] = value1;
                }
            }
        }
    }

    public int bestSolution(int[][] triangle) {
        for (int i = 1; i < triangle.length; i++) {
            triangle[i][0] += triangle[i - 1][0];
            triangle[i][i] += triangle[i - 1][i - 1];
            for (int j = 1; j < i; j++)
                triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
        }

        return Arrays.stream(triangle[triangle.length - 1]).max().getAsInt();
    }
}
