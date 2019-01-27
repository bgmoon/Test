/* 
 * Copyright (c) 2019, bgmoon
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.example.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * This class counts the number of words in a set of sentences.  It takes a map
 * with each element's key being the name of a paragraph and the value being an
 * arbitrary number of sentences.  It then creates a map where the key is the paragraph
 * and the value is another map, where each key in that map is one of the sentences
 * in that paragraph and the value is the number of words in that sentence.
 * 
 * A sentences is defined as a group of one or more non-white space letters that 
 * end with either a "." or a "?" or a "!".
 *
 * @author bgmoon
 */
public final class WordCountResult {

    private Map<String, Map<String, Long>> result = new HashMap<>();

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
                    } // end if there is at least 1 word
                } // end for each sentence in paragraph
                // save away the sentence/count map for this paragraph
                result.put(key, sentenceCountMap);
            } // end if there is at least one sentence
        });
    }

    public Map<String, Map<String, Long>> getResult() {
        return result;
    }

    public void setResult(final Map<String, Map<String, Long>> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "WordCountResult{" + "result=" + result + '}';
    }

}
