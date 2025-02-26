package com.quizapp.quiz_service.feign;


import com.quizapp.quiz_service.model.QuestionWrapper;
import com.quizapp.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("question/generatequestion")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQ);


    @PostMapping("question/getquestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionoFromId(@RequestBody List<Integer> questionIds);

    @PostMapping("question/getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response>responses);


}
