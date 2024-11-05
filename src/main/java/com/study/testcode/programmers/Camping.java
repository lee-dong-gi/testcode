package com.study.testcode.programmers;

import org.springframework.stereotype.Service;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/1833
@Service
public class Camping {
    public int solution(int n, int[][] data) {
        int answer = 0;

        // 배열을 첫 번째 인덱스 오름차순으로 정렬하고, 첫 번째 인덱스가 같으면 두 번째 인덱스 기준으로 정렬
        Arrays.sort(data, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        // 각 쐐기에 대해 가능한 직사각형을 체크
        for(int i = 0; i < n; i++){
            int r = data[i][0];  // 현재 쐐기의 행
            int c = data[i][1];  // 현재 쐐기의 열

            for(int j = i + 1; j < n; j++){
                int rr = data[j][0];  // 다음 쐐기의 행
                int cc = data[j][1];  // 다음 쐐기의 열

                // 넓이가 0인 경우 직사각형을 형성하지 않으므로 무시
                if(r == rr || c == cc) continue;

                // i와 j 사이에 다른 쐐기가 직사각형 내부에 있는지 확인
                boolean flag = true;
                for(int k = i + 1; k < j; k++){
                    int kr = data[k][0];  // 중간 쐐기의 행
                    int kc = data[k][1];  // 중간 쐐기의 열

                    // 중간 쐐기 위치가 직사각형의 내부에 있는지 검사
                    if((r < kr && kr < rr) && (Math.min(c, cc) < kc && kc < Math.max(c, cc))){
                        flag = false;  // 내부에 쐐기가 있으면 flag를 false로 설정하고 탐색 중단
                        break;
                    }
                }

                // 내부에 쐐기가 없으면 유효한 직사각형이므로 카운트 증가
                if(flag) answer++;
            }
        }

        return answer;
    }
}
