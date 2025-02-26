package com.quizapp.quiz_service.controller;


import com.quizapp.quiz_service.model.QuestionWrapper;
import com.quizapp.quiz_service.model.QuizDto;
import com.quizapp.quiz_service.model.Response;
import com.quizapp.quiz_service.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizservice;
   @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto)
   {
       return quizservice.createQuiz(quizDto.getCaegoryName(),quizDto.getNumQuestions(),quizDto.getTitle())   ;
   }
   @GetMapping("getquiz/{id}")
    public ResponseEntity<List<QuestionWrapper>>getQuiz(@PathVariable int id )
   {
       return quizservice.getQuiz(id);
   }
   @PostMapping("submitquiz/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id , @RequestBody List<Response> responses)
   {
       return quizservice.calculateResult(id,responses);
   }

}
