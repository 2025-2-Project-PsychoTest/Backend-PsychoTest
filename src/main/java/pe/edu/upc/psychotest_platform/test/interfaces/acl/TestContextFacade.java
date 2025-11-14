package pe.edu.upc.psychotest_platform.test.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.psychotest_platform.test.domain.model.aggregates.Answer;
import pe.edu.upc.psychotest_platform.test.domain.model.aggregates.Exam;
import pe.edu.upc.psychotest_platform.test.domain.model.aggregates.Question;
import pe.edu.upc.psychotest_platform.test.domain.model.queries.*;
import pe.edu.upc.psychotest_platform.test.domain.services.*;

import java.util.List;
import java.util.Optional;

@Service
public class TestContextFacade {
    private final AnswerCommandService answerCommandService;
    private final AnswerQueryService answerQueryService;
    private final ExamCommandService examCommandService;
    private final ExamQueryService examQueryService;
    private final QuestionCommandService questionCommandService;
    private final QuestionQueryService questionQueryService;

    public TestContextFacade(AnswerCommandService answerCommandService, AnswerQueryService answerQueryService,
                             ExamCommandService examCommandService, ExamQueryService examQueryService,
                             QuestionCommandService questionCommandService, QuestionQueryService questionQueryService) {
        this.answerCommandService = answerCommandService;
        this.answerQueryService = answerQueryService;
        this.examCommandService = examCommandService;
        this.examQueryService = examQueryService;
        this.questionCommandService = questionCommandService;
        this.questionQueryService = questionQueryService;
    }

    public Optional<Exam> getExamById(Long examId) {
        var query = new GetExamByIdQuery(examId);
        return examQueryService.handle(query);
    }

    public List<Question> getQuestionsByExamId(Long examId) {
        var query = new GetAllQuestionsByExamIdQuery(examId);
        return questionQueryService.handle(query);
    }

    public Optional<Answer> getAnswerById(Long answerId) {
        var query = new GetAnswerByIdQuery(answerId);
        return answerQueryService.handle(query);
    }

    // Add other methods as needed
}
