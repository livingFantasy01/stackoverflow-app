package lf.repository;

import lf.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Transactional
    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("update Question q set q.title = :title, q.body = :body where id = :id")
    int setQuestionInfoById(@Param("title") String title,@Param("body") String body,@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM Question WHERE id = :id")
    void deleteById(@Param("id") Long id);
}
