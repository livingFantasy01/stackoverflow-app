package lf.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lf.entity.Answer;
import lf.entity.Question;
import lf.entity.QuestionTag;
import lf.entity.Tag;
import lf.enums.QuestionStatus;
import lf.enums.UpdateType;
import lf.exception.BaseException;
import lf.exception.BusinessException;
import lf.util.AssertUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDto implements Serializable {

    private Long id;
    private Long userId;
    private String title;
    private String body;
    private Timestamp createTime;
    private Timestamp updateTime;

    private UpdateType updateType;

    private Long voteCount;

    @JsonProperty("status")
    private QuestionStatus questionStatus;

    @JsonProperty("answers")
    private List<AnswerDto> answerDtoList;

    @JsonProperty("tags")
    private List<TagDto> tagDtoList;

    public void verify() {
        AssertUtil.hasText(title, "title field can not be empty.");
        AssertUtil.hasText(body, "body field can not be empty.");
        AssertUtil.notNull(userId, "userId can not be null.");
    }

    public Question dtoToEntity() {
        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setBody(body);
        question.setCreateTime(createTime);
        question.setUpdateTime(updateTime);

        /*if (answerDtoList != null) {
            List<Answer> answers = answerDtoList.stream().map(answerDto -> {
                Answer answer = new Answer();
                answer.setId(id);
                answer.setTitle(title);
                answer.setBody(body);
                answer.setCreateTime(createTime);
                answer.setUpdateTime(updateTime);
                return answer;
            }).collect(Collectors.toList());
            question.setAnswers(answers);
        }

        if (tagDtoList != null) {
            List<QuestionTag> questionTags = tagDtoList.stream().map(tagDto -> {
                QuestionTag questionTag = new QuestionTag();
                questionTag.setQuestion(question);

                Tag tag = new Tag();
                tag.setId(tagDto.getId());
                tag.setTagName(tag.getTagName());
                tag.setCreateTime(tagDto.getCreateTime());
                tag.setUpdateTime(tagDto.getUpdateTime());
                tag.setUserId(tagDto.getUserId());
                questionTag.setTag(tag);

                return questionTag;
            }).collect(Collectors.toList());
            question.setQuestionTagList(questionTags);
        }*/

        return question;
    }

}
