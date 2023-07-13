package lf.repository;

import lf.entity.QuestionTag;
import lf.entity.QuestionTagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionTagRepository extends JpaRepository<QuestionTag, QuestionTagId> {
}
