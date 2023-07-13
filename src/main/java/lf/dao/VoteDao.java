package lf.dao;

import lf.entity.QuestionVote;
import lf.entity.QuestionVoteId;
import lf.repository.QuestionVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VoteDao {

    @Autowired
    private QuestionVoteRepository questionVoteRepository;

    public void vote(QuestionVote questionVote) {
        Optional<QuestionVote> optionalDBQuestionVote = questionVoteRepository.findById(questionVote.getId());
        if (optionalDBQuestionVote.isPresent()) {
            QuestionVote dbQuestionVote = optionalDBQuestionVote.get();
            if ((questionVote.getVote() == 1 && dbQuestionVote.getVote() == -1) || (questionVote.getVote() == -1 && dbQuestionVote.getVote() == 1)) {
                questionVoteRepository.deleteVote(questionVote.getId().getQuestionId(), questionVote.getId().getUserId());
            }
        } else {
            questionVoteRepository.save(questionVote);
        }
    }
}
