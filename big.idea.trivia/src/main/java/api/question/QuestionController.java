package api.question;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import question.model.Question;

import java.sql.SQLException;
import java.util.List;

@RestController
public class QuestionController {

    private QuestionRepository repository = new QuestionRepository(new QuestionSqlContext());

    @RequestMapping(value = "/question" , method = RequestMethod.GET)
    public List<Question> getQuestion(@RequestParam(value = "categoryId") int categoryId, @RequestParam(value = "difficulty") String difficulty) throws SQLException, ClassNotFoundException {
        return repository.getQuestions(categoryId, difficulty);
    }

    @RequestMapping(value = "question/check", method = RequestMethod.GET)
    public boolean checkAnswer(@RequestParam(value = "questionId") int questionId, @RequestParam(value = "answer") String answer){
        return repository.checkAnswer(questionId, answer);
    }
}


