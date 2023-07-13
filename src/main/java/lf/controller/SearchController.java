package lf.controller;

import lf.dto.QuestionDto;
import lf.dto.QuestionSearchCriteriaDto;
import lf.dto.QuestionSearchResponseDto;
import lf.entity.Question;
import lf.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/v1/question")
    public List<QuestionDto> questionSearch(@RequestBody QuestionSearchCriteriaDto searchCriteria){
       return searchService.questionSearch(searchCriteria);
    }
}
