package com.study.testcode.programmers;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

// 참고 : https://tmdrl5779.tistory.com/216
@Service
public class GameMapShortPath {

    // 상하좌우 이동을 위한 방향 배열 (오른쪽, 아래, 왼쪽, 위)
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    public int solution(int[][] maps) {
        int answer;

        // 방문 여부 및 최단 거리를 기록할 배열
        int[][] visited = new int[maps.length][maps[0].length];

        // BFS 호출
        bfs(maps, visited);

        // 도착 지점까지의 거리 (방문한 경우 거리 값, 방문하지 못했을 경우 0)
        answer = visited[maps.length - 1][maps[0].length - 1];

        // 도착 지점에 도달하지 못했을 경우 -1 반환
        if (answer == 0) {
            answer = -1;
        }

        return answer;
    }

    // BFS 를 이용해 최단 거리를 찾는 함수
    public void bfs(int[][] maps, int[][] visited) {
        int x = 0;
        int y = 0;

        // 시작점(0, 0) 방문 처리 (거리 1)
        visited[x][y] = 1;

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

                // 새로운 좌표가 맵을 벗어나면 건너뜀
                if (nX < 0 || nX > maps.length - 1 || nY < 0 || nY > maps[0].length - 1)
                    continue;

                // 아직 방문하지 않았고, 이동할 수 있는 길(1)일 때
                if (visited[nX][nY] == 0 && maps[nX][nY] == 1) {
                    // 새로운 좌표의 최단 거리는 현재 좌표의 거리 + 1
                    visited[nX][nY] = visited[cX][cY] + 1;
                    // 큐에 새로운 좌표 추가
                    queue.add(new int[]{nX, nY});
                }
            }
        }
    }
}
