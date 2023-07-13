package lf.repository;

import lf.entity.QuestionVote;
import lf.entity.QuestionVoteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface QuestionVoteRepository extends JpaRepository<QuestionVote, QuestionVoteId> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM Question_Vote WHERE question_id = :questionId AND user_id = :userId")
    void deleteVote(@Param("questionId") Long questionId, @Param("userId")  Long userId);
}
