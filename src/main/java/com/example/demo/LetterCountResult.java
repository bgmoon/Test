package com.example.demo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author bgmoon
 */
public final class LetterCountResult {

    private Map<String,Map<Character, Long>> result = new HashMap<>();

    public LetterCountResult(final Map<String, String> map) {
        countLetters(map);
    }

    private void countLetters(final Map<String, String> map) {
        // extract each paragraph, text pair
        map.forEach((String key, String value) -> {
            // only count if there are letters found in the text
            if (!value.isEmpty()) {
                final Map<Character,Long> paragraphCount = new HashMap<>();
                value = value.trim().toUpperCase().replaceAll("[^\\p{L}]", "");
                // for each letter, count it.
                for (int i = 0; i < value.length(); i++) {
                    final char c = value.charAt(i);
                    if (paragraphCount.containsKey(c)) {
                        paragraphCount.put(c, paragraphCount.get(c) + 1);
                    } else {
                        paragraphCount.put(c, 1L);
                    }
                }
                result.put(key,paragraphCount);
            }
        });
    }

    public Map<String, Map<Character, Long>> getResult() {
        return result;
    }

    public void setResult(Map<String, Map<Character, Long>> result) {
        this.result = result;
    }
}
