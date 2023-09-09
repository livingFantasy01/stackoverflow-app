package lf.service;

import lf.dao.SearchDao;
import lf.dto.QuestionDto;
import lf.dto.QuestionSearchCriteriaDto;
import lf.dto.QuestionSearchResponseDto;
import lf.dto.TagQuestionDto;
import lf.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    private SearchDao searchDao;

    public List<QuestionDto> questionSearch(QuestionSearchCriteriaDto questionSearchCriteriaDto){
       List<Question> list = searchDao.questionSearch(questionSearchCriteriaDto);
       return list.stream().map(question -> question.entityToDto()).collect(Collectors.toList());
    }

    public List<TagQuestionDto> findQuestionCountByAllTags() {
       return searchDao.findQuestionCountByAllTags();
    }
}
