package com.quizapp.question_service.controller;


import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.quizapp.question_service.model.Question;
import com.quizapp.question_service.model.QuestionWrapper;
import com.quizapp.question_service.model.Response;
import com.quizapp.question_service.services.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionsService questionsService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return  questionsService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>>getQuestionsByCategory(@PathVariable String category)
    {
        return questionsService.getQuestionsByCategory(category);
    }

    @PostMapping("addQuestion")
    public ResponseEntity<String>addQuestion(@RequestBody Question question)
    {
        return questionsService.addQuestion(question);
    }

    @PutMapping("updateQuestion")
    public String updateQuestion(@RequestBody Question question)
    {
        return questionsService.updateQuestion(question);
    }

    @DeleteMapping("deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable Long id)
    {
        return questionsService.deleteQuestion(id);
    }


    @GetMapping("generatequestion")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,@RequestParam Integer numQ)
{
    return questionsService.getQuestionsForQuiz(category,numQ);
}

@PostMapping("getquestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionoFromId(@RequestBody List<Integer> questionIds)
{
    return questionsService.getQuestionsFromIds(questionIds);
}
@PostMapping("getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response>responses)
{
    return questionsService.calculateScore(responses);
}
}
