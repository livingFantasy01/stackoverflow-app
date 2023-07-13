package lf.service;

import lf.dao.VoteDao;
import lf.dto.QuestionVoteDto;
import lf.entity.Question;
import lf.entity.QuestionVote;
import lf.entity.QuestionVoteId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class VoteService {

    @Autowired
    private VoteDao voteDao;

    public void vote(QuestionVoteDto questionVoteDto) {
        QuestionVote questionVote = new QuestionVote();
        QuestionVoteId questionVoteId = new QuestionVoteId(questionVoteDto.getQuestionId(), questionVoteDto.getUserId());
        questionVote.setId(questionVoteId);
        questionVote.setVote(questionVoteDto.getVote());
        voteDao.vote(questionVote);
    }
}
