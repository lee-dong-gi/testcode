package com.study.testcode.programmers;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MostReceivedGiftNonCheating {
    public int solution(String[] friends, String[] gifts) {
        Map<String, Map<String, Integer>> giveAndTake = makeGiveAndTake(friends, gifts);
        Map<String, Integer> jisu = makeJisu(friends, gifts);
        Map<String, Integer> nextMonth = calcGiveNextMonth(giveAndTake, jisu, friends);
        return getGreatestNumber(nextMonth);
    }

    private Integer getGreatestNumber(Map<String, Integer> nextMonth) {
        int max = 0;
        for (Map.Entry<String, Integer> entry : nextMonth.entrySet()) {
            String key = entry.getKey();
            Integer count = entry.getValue();
            if (max < count) {
                max = count;
            }
        }

        return max;
    }

    private Map<String, Integer> calcGiveNextMonth(
            Map<String, Map<String, Integer>> giveAndTake,
            Map<String, Integer> jisu,
            String[] friends
    ) {
        Map<String, Integer> result = new HashMap<>();
        for (String gifter : friends) {
            result.putIfAbsent(gifter, 0);

            for (String innerFriend : friends) {
                result.putIfAbsent(innerFriend, 0);

                if (innerFriend.equals(gifter)) {
                    continue;
                }

                Integer gifterToFriend = getCountByPair(giveAndTake, gifter, innerFriend);
                Integer friendToGifter = getCountByPair(giveAndTake, innerFriend, gifter);

                if (gifterToFriend > friendToGifter) {
                    result.put(gifter, result.get(gifter) + 1);
                } else if ((gifterToFriend == 0 && friendToGifter == 0)
                        || gifterToFriend.equals(friendToGifter)) {

                    Integer gifterJisu = jisu.get(gifter);
                    Integer friendJisu = jisu.get(innerFriend);

                    if (gifterJisu > friendJisu) {
                        result.put(gifter, result.get(gifter) + 1);
                    }
                }
            }
        }

        return result;
    }

    private Integer getCountByPair(Map<String, Map<String, Integer>> giveAndTake, String gifter, String gifted) {
        Integer result = 0;
        for (Map.Entry<String, Map<String, Integer>> entry : giveAndTake.entrySet()) {
            String key = entry.getKey();
            if (!key.equals(gifter)) {
                continue;
            }

            Map<String, Integer> gifteds = entry.getValue();

            for (Map.Entry<String, Integer> giftedsEntry : gifteds.entrySet()) {
                String giftedName = giftedsEntry.getKey();
                if (!giftedName.equals(gifted)) {
                    continue;
                }

                result = giftedsEntry.getValue();
            }
        }
        return result;
    }

    private Map<String, Map<String, Integer>> makeGiveAndTake(String[] friends, String[] gifts) {
        Map<String, Map<String, Integer>> giveAndTake = new HashMap<>();

        for (String gift : gifts) {
            String[] giftSplit = gift.split(" ");
            String gifter = giftSplit[0];
            String gifted = giftSplit[1];

            Map<String, Integer> target = giveAndTake.get(gifter);
            if (target == null) {
                target = new HashMap<>();
                giveAndTake.put(gifter, target);
            }

            Integer targetCount = target.get(gifted);
            target.put(gifted, targetCount == null ? 1 : targetCount++);
        }

        return giveAndTake;
    }

    private Map<String, Integer> makeJisu(String[] friends, String[] gifts) {
        Map<String, Integer> jugo = new HashMap<>();
        Map<String, Integer> batun = new HashMap<>();
        for (String gift : gifts) {
            String[] giftSplit = gift.split(" ");
            String gifter = giftSplit[0];
            String gifted = giftSplit[1];

            if (jugo.get(gifter) == null) {
                jugo.put(gifter, 1);
            } else {
                jugo.put(gifter, jugo.get(gifter) + 1);
            }

            if (batun.get(gifted) == null) {
                batun.put(gifted, 1);
            } else {
                batun.put(gifted, batun.get(gifted) + 1);
            }
        }

        Map<String, Integer> jisu = new HashMap<>();
        for (String friend : friends) {
            int calcJisu = 0;
            try {
                calcJisu = jugo.get(friend) - batun.get(friend);
            } catch (Exception ignore) {
            }

            if (calcJisu == 0 && jugo.get(friend) == null && batun.get(friend) != null) {
                jisu.put(friend, -batun.get(friend));
            } else {
                jisu.put(friend, calcJisu);
            }

        }

        return jisu;
    }
}
