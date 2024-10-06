package com.study.testcode.programmers;

import org.springframework.stereotype.Service;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/250121
@Service
public class DataAnalysisNonCheating {
    // data에서 ext 값이 val_ext보다 작은 데이터만 뽑은 후, sort_by에 해당하는 값을 기준으로 오름차순으로 정렬
    // 정렬한 데이터들이 담긴 이차원 정수 리스트 data
    // 어떤 정보를 기준으로 데이터를 뽑아낼지를 의미하는 문자열 ext
    // 뽑아낼 정보의 기준값을 나타내는 정수 val_ext
    // 정보를 정렬할 기준이 되는 문자열 sort_by
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        List<int[]> filterdList = new ArrayList<>();
        int extIdx = Ext.valueOf(ext.toUpperCase()).idx;
        int sortIdx = Ext.valueOf(sort_by.toUpperCase()).idx;
        for (int[] row : data) {
            if (row[extIdx] < val_ext) {
                filterdList.add(row);
            }
        }

        filterdList.sort(Comparator.comparingInt(o -> o[sortIdx]));

        return filterdList.toArray(new int[0][]);
    }

    private enum Ext {
        CODE(0),
        DATE(1),
        MAXIMUM(2),
        REMAIN(3);

        final int idx;

        Ext(int idx) {
            this.idx = idx;
        }
    }
}
