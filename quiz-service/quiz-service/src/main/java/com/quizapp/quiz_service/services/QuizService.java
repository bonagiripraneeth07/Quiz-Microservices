package com.quizapp.quiz_service.services;


import com.quizapp.quiz_service.Repository.QuizRepo;
import com.quizapp.quiz_service.feign.QuizInterface;
import com.quizapp.quiz_service.model.QuestionWrapper;
import com.quizapp.quiz_service.model.Quiz;
import com.quizapp.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizRepo quizrepo;
    @Autowired
    QuizInterface quizInterface;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        //basically here we need to connect to the Question db
        // and fetch the question  IDs ,so need to make a hhtp request.
        //This is done by using the Rest template :-https://localhost:8080/question/generatequestion
        //this is not a ideal case so we use different services like Eureka and feign
        // so Eureka helps to discover servers and register for servers (helps is searching)
        //while feigns implifies creating connections to web services by letting you define an interface
        List<Integer> questions =quizInterface.getQuestionsForQuiz(category,numQ).getBody();
       Quiz quiz = new Quiz();
        System.out.println(title);
       quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizrepo.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }


        public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
         Optional<Quiz> quiz = quizrepo.findById(id);
         List<Integer> questionIds = quiz.get().getQuestions();
         ResponseEntity <List<QuestionWrapper>> questions = quizInterface.getQuestionoFromId(questionIds);

          return questions;
    }



    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

      ResponseEntity<Integer> score=quizInterface.getScore(responses);
      return score;
    }
}
