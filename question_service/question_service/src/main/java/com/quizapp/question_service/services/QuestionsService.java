package com.quizapp.question_service.services;



import com.quizapp.question_service.Repository.QuestionRepo;
import com.quizapp.question_service.model.Question;
import com.quizapp.question_service.model.QuestionWrapper;
import com.quizapp.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionsService {
    @Autowired
    QuestionRepo repo ;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        }
        catch (Exception E)
        {
            E.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST );
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory( String category) {
      try {
          return new ResponseEntity<>(repo.findByCategory(category),HttpStatus.OK);
      }catch (Exception e)
      {
          e.printStackTrace();
      }
      return new ResponseEntity<>(repo.findByCategory(category),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try
        {
              repo.save(question);
             return new ResponseEntity<>("Success",HttpStatus.CREATED);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Somthing Went Wrong",HttpStatus.BAD_REQUEST);
    }

    public String updateQuestion(Question question) {
        repo.save(question);
        return "Update Successfull";
    }

    public String deleteQuestion(Long id) {
        repo.deleteById(id);

        return "Deletion Successfull";
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numQ) {
        List<Integer> questions= repo.findRandomQuestionsByCategory(category,numQ);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(List<Integer> questionIds) {
        List<QuestionWrapper> qw = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        for(long id :questionIds){
            questions.add(repo.findById(id).get());
        }
        for(Question question :questions){
             QuestionWrapper qwrapper = new QuestionWrapper();
             qwrapper.setId(question.getId());
             qwrapper.setQuestionTitle(question.getQuestionTitle());
             qwrapper.setOption1(question.getOption1());
             qwrapper.setOption2(question.getOption2());
             qwrapper.setOption3(question.getOption3());
             qwrapper.setOption4(question.getOption4());
             qw.add(qwrapper);
        }
        return new ResponseEntity<>(qw,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(List<Response> responses) {
        int score=0;
        for(Response response :responses){
            Question question = repo.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                score++;
            }
        }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
