package lf.dao;

import lf.dto.TagDto;
import lf.entity.Question;
import lf.entity.Tag;
import lf.exception.BusinessException;
import lf.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TagDao {

    @Autowired
    private TagRepository tagRepository;

    public Long add(Tag tag) {
       return tagRepository.save(tag).getId();
    }

    public void update(TagDto tagDto) {
        tagRepository.setTagInfoById(tagDto.getTagName(),tagDto.getId());
    }

    public Tag findById(Long id) {
        Optional<Tag> tag = tagRepository.findById(id);
        return tag.orElseThrow(() -> new BusinessException(String.format("No tag present for Id %s", id)));
    }

    public void deleteById(Long id) {
        try {
            tagRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            System.out.println(emptyResultDataAccessException.getMessage());
            throw new BusinessException(String.format("No tag found for Id %s", id));
        }
    }
}
