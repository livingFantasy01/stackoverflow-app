package lf.controller;

import lf.dto.QuestionDto;
import lf.dto.QuestionVoteDto;
import lf.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping(value = "/v1/question/vote", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> vote(@RequestBody QuestionVoteDto questionVoteDto) {
         voteService.vote(questionVoteDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
