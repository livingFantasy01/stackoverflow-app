package lf.service;

import lf.dao.TagDao;
import lf.dto.QuestionDto;
import lf.dto.TagDto;
import lf.entity.Question;
import lf.entity.Tag;
import lf.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private TagDao tagDao;

    public Long add(TagDto tagDto) {
        Tag tag = tagDto.dtoToEntity();
        return tagDao.add(tag);

    }

    public void update(TagDto tagDto) {
        AssertUtil.notNull(tagDto.getId(), "Id can not be empty.");
        tagDao.update(tagDto);
    }

    public TagDto findById(Long id) {
        AssertUtil.notNull(id,"Id can not be empty.");
        Tag tag = tagDao.findById(id);
        TagDto tagDto = tag.entityToDto();
        return  tagDto;
    }

    public void deleteById(Long id) {
        AssertUtil.notNull(id, "Id can not be empty.");
        tagDao.deleteById(id);
    }
}
