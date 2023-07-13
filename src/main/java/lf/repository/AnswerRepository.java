package lf.repository;

import lf.dto.AnswerDto;
import lf.entity.Answer;
import lf.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Transactional
    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("update Answer a set a.title = :title, a.body = :body where id = :id")
    int setAnswerInfoById(@Param("title") String title, @Param("body") String body, @Param("id") Long id);
}
