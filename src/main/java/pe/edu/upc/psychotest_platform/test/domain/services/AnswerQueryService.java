package pe.edu.upc.psychotest_platform.test.domain.services;

import pe.edu.upc.psychotest_platform.test.domain.model.aggregates.Answer;
import pe.edu.upc.psychotest_platform.test.domain.model.queries.GetAllAnswersByQuestionIdQuery;
import pe.edu.upc.psychotest_platform.test.domain.model.queries.GetAllAnswersQuery;
import pe.edu.upc.psychotest_platform.test.domain.model.queries.GetAnswerByIdQuery;

import java.util.List;
import java.util.Optional;

public interface AnswerQueryService {


    List<Answer> handle(GetAllAnswersQuery query);
    Optional<Answer> handle(GetAnswerByIdQuery query);
    List<Answer> handle(GetAllAnswersByQuestionIdQuery query);
}
