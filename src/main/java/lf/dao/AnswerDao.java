package lf.dao;

import lf.dto.AnswerDto;
import lf.entity.Answer;
import lf.exception.BusinessException;
import lf.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class AnswerDao {

    @Autowired
    private AnswerRepository answerRepository;

    public Long add(Answer answer) {
        return answerRepository.save(answer).getId();
    }

    public void deleteById(Long id) {
        try {
            answerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            System.out.println(emptyResultDataAccessException.getMessage());
            throw new BusinessException(String.format("No answer found for Id %s", id));
        }
    }

    public void update(AnswerDto answerDto) {
        int updateCount = answerRepository.setAnswerInfoById(answerDto.getTitle(), answerDto.getBody(), answerDto.getId());
        if (updateCount == 0) {
            throw new BusinessException(String.format("No Answer found for Id %s", answerDto.getId()));
        }
        System.out.println(String.format("%s answer updated successfully.", updateCount));
    }
}
