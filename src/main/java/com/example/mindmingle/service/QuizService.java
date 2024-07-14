package com.example.mindmingle.service;


import com.example.mindmingle.dao.QuestionDao;
import com.example.mindmingle.dao.QuizDao;
import com.example.mindmingle.model.Question;
import com.example.mindmingle.model.QuestionWrapper;
import com.example.mindmingle.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;
    @Autowired
    private QuestionDao questionDao;


    private static final Logger LOGGER = Logger.getLogger(QuizService.class.getName());

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        try {
            // Assuming findRandomQuestionsByCategory is correctly implemented in QuestionDao
            List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);

            LOGGER.info("Quiz to be saved: " + quiz);

            quizDao.save(quiz);

            LOGGER.info("Quiz saved successfully");


            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to create quiz", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }
}
