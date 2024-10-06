package com.study.testcode.programmers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static com.study.testcode.common.constant.DateConstants.CONFIG_TEST;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = CONFIG_TEST)
public class DataAnalysisNonCheatingNonCheatingTest {
    @Autowired
    private DataAnalysisNonCheating dataAnalysisNonCheating;

    /**
     * data = [[1, 20300104, 100, 80], [2, 20300804, 847, 37], [3, 20300401, 10, 8]]
     * ext = "date"
     * val_ext = 20300501
     * sort_by = "remain"
     * result = [[3,20300401,10,8],[1,20300104,100,80]]
     *
     * data는 아래 표처럼 나타낼 수 있습니다.
     *
     * code | date      | maximum  |remain
     * ------------------------------------
     * 1    | 20300104  | 100      | 80
     * 2    | 20300804  | 847      | 37
     * 3    | 20300401  | 10       | 8
     */
    @Test
    void dataAnalysisTest() {
        // given
        // 정렬한 데이터들이 담긴 이차원 정수 리스트 data
        int[][] data = {
                {1, 20300104, 100, 80},
                {2, 20300804, 847, 37},
                {3, 20300401, 10, 8}
        };

        // 어떤 정보를 기준으로 데이터를 뽑아낼지를 의미하는 문자열 ext
        String ext = "date";

        // 뽑아낼 정보의 기준값을 나타내는 정수 val_ext
        int val_ext = 20300501;

        // 정보를 정렬할 기준이 되는 문자열 sort_by
        String sort_by = "remain";

        // 예상되는 결과 배열
        int[][] expectedResult = {
                {3,20300401,10,8},
                {1,20300104,100,80}
        };

        //when
        int[][] result = dataAnalysisNonCheating.solution(data, ext, val_ext, sort_by);

        // then
        for (int i = 0; i < result.length; i++) {
            assertArrayEquals(expectedResult[i], result[i]);
        }
    }
}
