package com.study.testcode.programmers;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/42888
// 입력예시 : ["Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"]
// 출력예시 : ["Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."]
@Service
public class OpenChatRoomNonCheating {
    public String[] solution(String[] record) {
        List<UserChatStatus> userChatStatuses = makeUserChatStatus(record);
        return makeUserChatStatusString(userChatStatuses);
    }

    private static List<UserChatStatus> changeNickName(List<UserChatStatus> userChatStatuses) {
        // 상태가 Leave가 아닌 것만 추출
        List<UserChatStatus> changeTargets = userChatStatuses.stream()
                .filter(user -> user.getStatus() != Status.LEAVE)
                .collect(Collectors.toMap(
                        UserChatStatus::getId,
                        user -> user,
                        (oldValue, newValue) -> newValue
                ))
                .values()                   // Map의 값들만 추출
                .stream()                   // 다시 Stream으로 변환
                .collect(Collectors.toList()); // List로 수집

        // 닉네임 변경
        changeTargets.forEach(changeStatus ->
            userChatStatuses.forEach(target -> {
                if (target.getId().equals(changeStatus.getId())) {
                    target.setNickName(changeStatus.getNickName());
                }
            })
        );

        return userChatStatuses;
    }

    // 결과 배열 생성
    private static String[] makeUserChatStatusString(List<UserChatStatus> userChatStatuses) {
        String[] results = new String[userChatStatuses.size()];
        for (int i = 0; i < userChatStatuses.size(); i++) {
            UserChatStatus target = userChatStatuses.get(i);
            results[i] = target.getNickName() + target.getStatus().getText();
        }

        return results;
    }

    // 채팅 상태 메시지 객체 생성
    private static List<UserChatStatus> makeUserChatStatus(String[] records) {
        List<UserChatStatus> userChatStatuses = new ArrayList<>();
        Map<String, String> nickNameMap = new HashMap<>();

        for (String record: records) {
            String[] recordProperties = record.split(" ");
            nickNameMap.computeIfAbsent(recordProperties[1], k -> recordProperties[2]);

            userChatStatuses.add(new UserChatStatus(
                    recordProperties[1],
                    recordProperties.length == 3 ? recordProperties[2] : nickNameMap.get(recordProperties[1]),
                    Status.valueOf(recordProperties[0].toUpperCase())
            ));
        }

        return changeNickName(userChatStatuses).stream()
                .filter(user -> user.getStatus() != Status.CHANGE)
                .collect(Collectors.toList());
    }
}

class UserChatStatus {
    private final String id;
    private String nickName;
    private final Status status;

    UserChatStatus(String id, String nickName, Status status){
        this.id = id;
        this.nickName = nickName;
        this.status = status;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getId() {
        return this.id;
    }

    public String getNickName() {
        return this.nickName;
    }

    public Status getStatus() {
        return this.status;
    }
}

enum Status {
    ENTER("님이 들어왔습니다."),
    LEAVE("님이 나갔습니다."),
    CHANGE("");

    Status(String text) {
        this.text = text;
    }

    private final String text;

    public String getText(){
        return this.text;
    }
}