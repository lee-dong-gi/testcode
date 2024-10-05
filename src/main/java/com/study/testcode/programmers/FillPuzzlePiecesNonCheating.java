package com.study.testcode.programmers;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 레전드..
 */
// https://school.programmers.co.kr/learn/courses/30/lessons/84021
@Service
public class FillPuzzlePiecesNonCheating {
    // 상하좌우 이동을 위한 방향 배열 (0:오른쪽, 1:아래, 2:왼쪽, 3:위)
    static final int[] dx = {1, 0, -1, 0}; // x축 이동 팩터
    static final int[] dy = {0, 1, 0, -1}; // y축 이동 팩터

    public int solution(int[][] game_board, int[][] table) {
        // table 배열의 폴리곤 리스트 추출, table 배열의 이동가능 값은 1
        List<int[][]> tableZeroPointPolygons = findPolygons(table, 1);

        // game_board 배열의 폴리곤 리스트 추출, game_board 배열의 이동가능 값은 0
        List<int[][]> gameBoardZeroPointPolygons = findPolygons(game_board, 0);

        // 서로 일치하는 폴리곤의 격자 총 개수 세고 반환
        return numberOfPuzzlesFilled(gameBoardZeroPointPolygons, tableZeroPointPolygons);
    }

    // 폴리곤의 격자 개수
    private static int getPolygonGridCount(int[][] target) {
        int count = 0;
        for (int x = 0; x < target.length; x++) {
            for (int y = 0; y < target[0].length; y++) {
                if (target[x][y] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    // game_board 폴리곤과 table 폴리곤이 완전히 일치하는지 확인
    private static int numberOfPuzzlesFilledRotate(List<int[][]> gameBoardPolygons, int[][] rotatedPolygon) {
        int rotatedPolygonCount = getPolygonGridCount(rotatedPolygon);
        int count = 0;

        for (int[][] gameBoardPolygon : gameBoardPolygons) {
            int gameBoardPolygonCount = getPolygonGridCount(gameBoardPolygon);

            if (gameBoardPolygonCount != rotatedPolygonCount) {
                continue;
            }

            boolean exitFlag = false;
            for (int x = 0; x < gameBoardPolygon.length; x++) {
                for (int y = 0; y < gameBoardPolygon[0].length; y++) {
                    if (gameBoardPolygon[x][y] != 1) {
                        continue;
                    }

                    if (gameBoardPolygon[x][y] == 1) {
                        if (rotatedPolygon[x][y] == 1) {
                            count++;
                        } else {
                            count = 0;
                            exitFlag = true;
                            break;
                        }
                    }
                }

                if (exitFlag || count == rotatedPolygonCount) {
                    break;
                }
            }

            if (exitFlag) {
                continue;
            }

            if (count == rotatedPolygonCount) {
                gameBoardPolygons.remove(gameBoardPolygon); // 검사된 완료된 폴리곤은 리스트에서 제거
                break;
            }
        }

        return count == rotatedPolygonCount ? count : 0;
    }

    private static int numberOfPuzzlesFilled(List<int[][]> gameBoardZeroPointPolygons, List<int[][]> tableZeroPointPolygons) {
        int count = 0;
        for (int[][] tableZeroPointPolygon : tableZeroPointPolygons) {
            // 0도 회전
            int innerCount = numberOfPuzzlesFilledRotate(gameBoardZeroPointPolygons, tableZeroPointPolygon);

            if (innerCount == 0) { // 90도 회전
                innerCount = numberOfPuzzlesFilledRotate(gameBoardZeroPointPolygons, adjustToOrigin(rotate90(tableZeroPointPolygon)));
            }

            if (innerCount == 0) { // 180도 회전
                innerCount = numberOfPuzzlesFilledRotate(gameBoardZeroPointPolygons, adjustToOrigin(rotate90(rotate90(tableZeroPointPolygon))));
            }

            if (innerCount == 0) { // 270도 회전
                innerCount = numberOfPuzzlesFilledRotate(gameBoardZeroPointPolygons, adjustToOrigin(rotate90(rotate90(rotate90((tableZeroPointPolygon))))));
            }

            count += innerCount;
        }

        return count;
    }

    // game_board와 table 배열의 폴리곤 리스트 추출하기 위한 메서드
    private static List<int[][]> findPolygons(int[][] target, int movableNumber){
        List<int[][]> polygons = new ArrayList<>();
        int[][] visited = new int[target.length][target[0].length]; // 이미 추출한 폴리곤 중복추출 방지

        for (int x = 0; x < target.length; x++) {
            for (int y = 0; y < target[x].length; y++) {
                if (visited[x][y] == 1) { // 이미 방문하였다면 패스
                    continue;
                }

                int value = target[x][y];

                if (value == movableNumber) { // 방문한 기록이 없고 이동 가능한 경로라면 도형 추출
                    int[][] polygon = findPolygonByBfs(new int[]{x, y}, target, movableNumber, visited);
                    polygons.add(polygon);
                }
            }
        }


        return polygons;
    }

    // bfs를 이용한 도형 추출 메서드
    private static int[][] findPolygonByBfs(int[] start, int[][] table, int movableNumber, int[][] visited) {
        int[][] polygon = new int[table.length][table[0].length];
        int x = start[0];
        int y = start[1];

        // 시작점 방문 처리
        visited[x][y] = 1;
        polygon[x][y] = 1;

        // BFS 탐색을 위한 큐 (x, y 좌표를 저장)
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        // 큐가 빌 때까지 반복 (탐색할 지점이 없을 때까지)
        while (!queue.isEmpty()) {
            // 큐에서 현재 위치를 꺼냄
            int[] current = queue.remove();
            int cX = current[0]; // 현재 x 좌표
            int cY = current[1]; // 현재 y 좌표

            // 상하좌우로 이동하며 탐색
            for (int i = 0; i < 4; i++) {
                int nX = cX + dx[i]; // 새로운 x 좌표
                int nY = cY + dy[i]; // 새로운 y 좌표

                // 새로운 좌표가 테이블을 벗어나면 건너뜀
                if (nX < 0 || nX > table.length - 1 || nY < 0 || nY > table[0].length - 1)
                    continue;

                // 아직 방문하지 않았고, 이동할 수 있는 길일 때
                if (visited[nX][nY] == 0 && table[nX][nY] == movableNumber) {
                    visited[nX][nY] = 1;
                    polygon[nX][nY] = 1;
                    // 큐에 새로운 좌표 추가
                    queue.add(new int[]{nX, nY});
                }
            }
        }

        return adjustToOrigin(polygon); // 0,0에 맞춰 반환
    }

    // 좌표로 이루어진 도형을 90도 회전하는 메서드
    private static int[][] rotate90(int[][] points) {
        int rows = points.length;         // 원래 배열의 행 길이
        int cols = points[0].length;      // 원래 배열의 열 길이
        int[][] rotated = new int[cols][rows];  // 회전된 배열의 크기 (열, 행이 뒤바뀜)

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[j][rows - 1 - i] = points[i][j]; // 새로운 좌표로 회전
            }
        }

        return rotated;
    }

    // 도형의 x 최소값과 y 최소값을 구하고 모든 좌표에 해당 값을 뺌으로서 0,0에 맞추기 위한 요소로 사용됨
    private static int[] finMinCoordinates(int[][] points) {
        int minX = Integer.MAX_VALUE; // 초기값으로 가장 큰 정수 설정
        int minY = Integer.MAX_VALUE; // 초기값으로 가장 큰 정수 설정

        // 이중 반복문을 사용하여 모든 좌표 탐색
        for (int x = 0; x < points.length; x++) {
            for (int y = 0; y < points[x].length; y++) { // 각 점의 x, y 좌표 탐색
                if (points[x][y] == 0) {
                    continue;
                }

                if (x < minX) { // x좌표 일 때
                    minX = x; // 가장 작은 x 값 업데이트
                }

                if (y < minY) { // y좌표 일 때
                    minY = y; // 가장 작은 y 값 업데이트
                }
            }
        }

        return new int[]{minX, minY}; // 최소 x, y 값을 배열로 반환
    }

    // 도형의 x 최소값과 y 최소값을 구하고 모든 좌표에 해당 값을 뺌으로서 0,0에 맞춤
    private static int[][] adjustToOrigin(int[][] points) {
        int[] minCoordinates = finMinCoordinates(points);
        int minX = minCoordinates[0];
        int minY = minCoordinates[1];

        // 좌표 조정
        int[][] adjusted = new int[points.length][points[0].length];
        for (int x = 0; x < points.length; x++) {
            for (int y = 0; y < points[x].length; y++) { // 각 점의 x, y 좌표 탐색
                if (points[x][y] == 1) {
                    adjusted[x - minX][y - minY] = 1;
                }
            }
        }

        return adjusted; // 0,0 기준으로 조정된 도형의 좌표 반환
    }
}
