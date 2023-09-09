package lf.controller;

import lf.dto.QuestionDto;
import lf.dto.QuestionSearchCriteriaDto;
import lf.dto.QuestionSearchResponseDto;
import lf.dto.TagQuestionDto;
import lf.entity.Question;
import lf.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SearchService searchService;

    @PostMapping("/v1/findQuestionsBySearchCriteria")
    public List<QuestionDto> questionSearch(@RequestBody QuestionSearchCriteriaDto searchCriteria){
        return searchService.questionSearch(searchCriteria);
    }

    @GetMapping("/v1/findQuestionCountByAllTags")
    public List<TagQuestionDto> findQuestionCountByAllTags(){
        return searchService.findQuestionCountByAllTags();
    }
}
