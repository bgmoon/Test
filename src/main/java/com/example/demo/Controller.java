package com.example.demo;

import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author bgmoon
 */
@RestController
@RequestMapping("/")
public class Controller {

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
