package com.study.testcode.programmers;

import org.springframework.stereotype.Service;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/258712
@Service
public class MostReceivedGift {
    public int solution(String[] friends, String[] gifts) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < friends.length; i++) {
            map.put(friends[i], i);
        }
        int[] index = new int[friends.length]; // 선물지수 배열
        int[][] record = new int[friends.length][friends.length]; // 주고받은 선물 배열

        for (String str : gifts) {
            String[] cur = str.split(" ");
            index[map.get(cur[0])]++; // 선물 지수에 대한 계산 : 선물을 준 것
            index[map.get(cur[1])]--; // 선물 지수에 대한 계산 : 선물을 받은 것
            record[map.get(cur[0])][map.get(cur[1])]++; // 주고받은 선물에 대한 계산
        }

        return getAns(friends, record, index);
    }

    private static int getAns(String[] friends, int[][] record, int[] index) {
        int ans = 0;
        for (int i = 0; i < friends.length; i++) { // 다음달 받게될 선물 계산
            int cnt = 0; // 각 사람 별로 루프
            for (int j = 0; j < friends.length; j++) {
                if (i == j) continue; // 본인은 제외
                if (record[i][j] > record[j][i]) cnt++; // 상대방과 주고 받은 선물을 비교해서 상대방 보다 많다면 +1
                else if (record[i][j] == record[j][i] && index[i] > index[j])
                    cnt++; // 상대방과 주고 받은 선물은 같지만 선물지수가 더 높다면 +1
            }
            ans = Math.max(cnt, ans); // ans 에 담긴 값 보다 cnt 가 크면 ans 의 값을 cnt 로 대체
        }
        return ans;
    }
}
