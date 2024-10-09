package com.study.testcode.programmers;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

// https://school.programmers.co.kr/learn/courses/30/lessons/133499
// 기존 옹알이에서 같은 발음이 연속으로 안되는 상황
@Service
public class Babbling2NonCheating {
    public int solution(String[] babbling) {
        Set<String> set = Set.of("aya", "ye", "woo", "ma");

        int answer = 0;
        for (String target : babbling) {
            if (set.contains(target)) {
                answer++;
            } else {
                for (String val : set) {
                    if (target.contains(val + val)) {
                        continue;
                    }

                    target = target.replace(val, " ");
                }

                if (target.trim().isEmpty()) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
