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
