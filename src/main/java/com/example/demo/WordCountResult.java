package com.example.demo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author bgmoon
 */
public final class WordCountResult {

    private Map<String, Map<String, Long>> wordCount = new HashMap<>();

    public WordCountResult(final Map<String, String> map) {
        countWords(map);
    }

    private void countWords(final Map<String, String> map) {
        // assume there could be several paragraphs passed to us to process sence
        // we accept a JSON Map for processing
        map.forEach((String key, String value) -> {
            value = value.trim();
            // only count if there are letters found in the sentence
            if (!value.isEmpty()) {
                // allocate a map to hold sentenece and count for each sentence detected
                final Map<String, Long> sentenceCountMap = new HashMap<>();
                // spit the text into sentences as defined by text ending in ".", or "?" or "!"
                final String sentences[] = value.split("\\.|\\?|\\!");
                // for each sentence, count words
                for (final String sentence : sentences) {
                    // trim up each sentence
                    final String trim = sentence.trim();
                    if (!trim.isEmpty()) {
                        // if its not empty, then spit words and count them then store
                        // that value into our sentence count map with the key being the
                        // trimmed sentence and the value being the word count for that
                        // sentence
                        sentenceCountMap.put(trim, new Long(trim.split("\\s+").length));
                    }
                }
                // save away the sentence/count map for this paragraph
                wordCount.put(key, sentenceCountMap);
            }
        });
    }

    public Map<String, Map<String, Long>> getWordCount() {
        return wordCount;
    }

    public void setWordCount(final Map<String, Map<String, Long>> wordCount) {
        this.wordCount = wordCount;
    }
}
