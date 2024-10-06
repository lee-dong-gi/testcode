package com.study.testcode.programmers;

import org.springframework.stereotype.Service;
import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/120956
@Service
public class BabblingNonCheating {
    public int solution(String[] babbling) {
        Set<String> set = Set.of("aya", "ye", "woo", "ma");
        int answer = 0;
        for (String target : babbling) {
            if (set.contains(target)) {
                answer++;
            } else {
                for (String val : set) {
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
