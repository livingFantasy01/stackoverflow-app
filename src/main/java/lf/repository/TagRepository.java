package lf.repository;

import lf.entity.Question;
import lf.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Transactional
    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("update Tag t set t.tagName = :tagName where id = :id")
    int setTagInfoById(@Param("tagName") String tagName, @Param("id") Long id);

}
