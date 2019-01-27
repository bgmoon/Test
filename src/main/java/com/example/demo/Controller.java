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

import java.util.Date;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * The controller class holds the REST interfaces for this simple application.
 *
 * @author bgmoon
 */
@RestController
@RequestMapping("/")
public class Controller {

    /**
     * Generic exception handler
     * 
     * @param ex the exception created during processing
     * @param request the request from the user
     * @return a string description of the error
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<String> handleAllExceptions(Exception ex, WebRequest request) {
        final String errorDetails = new Date() +  ex.getMessage() + request.getDescription(false);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Count words in each sentence. Test with this command:
     *
     * curl --header "Content-Type: application/json" --request POST
     * --data'{"paragraph2":"a sentence. or two."}'
     * http://localhost:8080/wordCountPerSentence
     *
     * @param map a map of paragraphs, each paragraph has a name and then a
     * string that contains zero or more sentences.
     * @return a JSON Map of paragraphs, each paragraph is a MAP of sentences
     * with their associated word counts
     */
    @PostMapping(value = "/wordCountPerSentence", consumes = "application/json", produces = "application/json")
    public WordCountResult wordCountPerSentence(@RequestBody Map<String, String> map) {
        return new WordCountResult(map);
    }

    /**
     * Count the number of each letter in text.
     *
     * curl --header "Content-Type: application/json" --request POST
     * --data'{"paragraph2":"a sentence. or two."}'
     * http://localhost:8080/totalLetterCount
     *
     * @param map a map of paragraphs. Each paragraph has a name with an
     * associated string that contains zero or more characters.
     * @return a map of letters with a count of each letter detected
     */
    @PostMapping(value = "/totalLetterCount", consumes = "application/json", produces = "application/json")
    public LetterCountResult totalLetterCount(@RequestBody Map<String, String> map) {
        return new LetterCountResult(map);
    }
}
