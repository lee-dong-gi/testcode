package com.study.testcode.programmers;

import org.springframework.stereotype.Service;

@Service
public class PuzzleGameNonCheating {
    public int solution(int[] diffs, int[] times, long limit) {
        int maxDiff = 0;
        for (int diff : diffs) {
            maxDiff = Math.max(maxDiff, diff);
        }

        int left = 1;
        int right = maxDiff;
        int answer = maxDiff;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long timeTaken = calculateTimeTaken(diffs, times, mid, limit);

            if (timeTaken <= limit) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }

    private static long calculateTimeTaken(int[] diffs, int[] times, int level, long limit) {
        long timeTaken = 0;
        int prevTime = 0;

        for (int i = 0; i < diffs.length; i++) {
            if (level >= diffs[i]) {
                timeTaken += times[i];
            } else {
                timeTaken += (long)(times[i] + prevTime) * (diffs[i] - level) + times[i];
            }

            if (timeTaken > limit) {
                return timeTaken;
            }

            prevTime = times[i];
        }
        return timeTaken;
    }
}
