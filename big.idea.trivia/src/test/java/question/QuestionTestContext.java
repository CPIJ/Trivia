package question;

import api.question.IQuestionContext;
import question.model.Answer;
import question.model.Enums.Difficulty;
import question.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionTestContext implements IQuestionContext {

    ArrayList<Question> questions;

    public QuestionTestContext(int categoryId, Difficulty difficulty) {
        this.questions = (ArrayList<Question>) getQuestions(categoryId, difficulty);
    }

    @Override
    public List<Question> getQuestions(int categoryId, Difficulty difficulty) {
        ArrayList<Answer> answers = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                answers.add(new Answer(
                        i,
                        "testTrue",
                        true
                ));
            } else {
                answers.add(new Answer(
                        i,
                        "testFalse",
                        false
                ));
            }
        }


        for (int i = 0; i < 3; i++) {
            this.questions.add(new Question(
                    i,
                    categoryId,
                    "multiple",
                    difficulty.toString(),
                    "test",
                    answers
            ));
        }

        return this.questions;
    }

    @Override
    public Answer getCorrectAnswer(int questionId) {

        for (Question question: questions){
            if (question.getId() == questionId){
                for (Answer answerInList: question.getAnswers()){
                    if (answerInList.isCorrect()){
                        return answerInList;
                    }
                }
            }
        }
        return null;
    }
}

