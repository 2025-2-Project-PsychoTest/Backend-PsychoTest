package pe.edu.upc.psychotest_platform.test.domain.services;

import pe.edu.upc.psychotest_platform.test.domain.model.aggregates.Question;
import pe.edu.upc.psychotest_platform.test.domain.model.queries.GetAllQuestionsByExamIdQuery;
import pe.edu.upc.psychotest_platform.test.domain.model.queries.GetAllQuestionsQuery;
import pe.edu.upc.psychotest_platform.test.domain.model.queries.GetQuestionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface QuestionQueryService {
    List<Question> handle(GetAllQuestionsQuery query);
    Optional<Question> handle(GetQuestionByIdQuery query);
    List<Question> handle(GetAllQuestionsByExamIdQuery query);

}
