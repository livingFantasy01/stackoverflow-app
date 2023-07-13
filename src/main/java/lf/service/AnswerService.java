package lf.service;

import lf.dao.AnswerDao;
import lf.dto.AnswerDto;
import lf.entity.Answer;
import lf.entity.Question;
import lf.util.AssertUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private ModelMapper modelMapper;
    public Long add(AnswerDto answerDto) {
        answerDto.verify();
        Answer answer = answerDto.dtoToEntity();
       Long answerId = answerDao.add(answer);
       return answerId;
    }

    public void update(AnswerDto answerDto) {
        AssertUtil.notNull(answerDto.getId(), "answer id can not be null");
        answerDao.update(answerDto);

    }

    public void deleteById(Long id) {
        AssertUtil.notNull(id, "answer id can not be null");
        answerDao.deleteById(id);
    }
}
