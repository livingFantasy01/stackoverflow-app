package lf.service;

import lf.dao.QuestionDao;
import lf.dto.QuestionDto;
import lf.entity.*;
import lf.enums.UpdateType;
import lf.repository.QuestionRepository;
import lf.repository.QuestionTagRepository;
import lf.repository.TagRepository;
import lf.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private QuestionTagRepository questionTagRepository;

    public Long add(QuestionDto questionDto) {
        questionDto.verify();
        Question question = questionDto.dtoToEntity();
        Question savedQuestion = questionDao.add(question);
        List<QuestionTag> questionTags = questionDto.getTagDtoList().stream().map(tagDto -> {
            Tag tag = tagDto.dtoToEntity();
            QuestionTag questionTag = new QuestionTag();
            questionTag.setQuestion(question);
            questionTag.setTag(tag);
            questionTag.setId(new QuestionTagId(question, tag));
            return questionTag;
        }).collect(Collectors.toList());
        savedQuestion.getQuestionTagList().addAll(questionTags);
        return question.getId();
    }

    public QuestionDto findById(Long id) {
        AssertUtil.notNull(id, "Id can not be empty.");
        Question question = questionDao.findById(id);
        List<Answer> answerList = questionDao.fetchAnswersForQuestion(question, 0, 20);
        QuestionDto questionDto = question.entityToDto();
        if (answerList != null) {
            questionDto.setAnswerDtoList(answerList.stream().map(answer -> answer.entityToDto()).collect(Collectors.toList()));
        }
        return questionDto;
    }

    public void update(QuestionDto questionDto) {
        AssertUtil.notNull(questionDto.getId(), "Id can not be empty.");
        questionDto.verify();
        questionDao.update(questionDto);
        Question question = questionDao.findById(questionDto.getId());
        if (questionDto.getTagDtoList() != null) {
            questionDto.getTagDtoList().forEach(tagDto -> {
                Tag tag = tagDto.dtoToEntity();
                QuestionTag questionTag = new QuestionTag();
                questionTag.setQuestion(question);
                questionTag.setTag(tag);
                questionTag.setId(new QuestionTagId(question, tag));

                if (UpdateType.ADD.equals(tagDto.getUpdateType())) {
                    question.getQuestionTagList().add(questionTag);
                }
                if (UpdateType.REMOVE.equals(tagDto.getUpdateType())) {
                    question.getQuestionTagList().remove(questionTag);
                }
            });
        }

    }

    public void deleteById(Long id) {
        AssertUtil.notNull(id, "Id can not be empty.");
        questionDao.deleteById(id);
    }
}
