package lf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lf.entity.Answer;
import lf.entity.Question;
import lf.util.AssertUtil;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class AnswerDto {

    private Long id;
    private Long userId;
    private String title;
    private String body;
    private Timestamp createTime;
    private Timestamp updateTime;
    @JsonProperty("question")
    private QuestionDto questionDto;

    public void verify(){
        AssertUtil.notNull(questionDto,"question can not be null.");
        AssertUtil.notNull(questionDto.getId(),"questionId can not be null.");
        AssertUtil.notNull(userId,"userId can not be null.");
        AssertUtil.notNull(title,"title can not be null.");
        AssertUtil.notNull(body,"body can not be null.");
    }

    public Answer dtoToEntity(){
        Answer answer = new Answer();
        answer.setId(id);
        answer.setTitle(title);
        answer.setBody(body);
        answer.setCreateTime(createTime);
        answer.setUpdateTime(updateTime);

        Question question = new Question();
        question.setId(questionDto.getId());
        answer.setQuestion(question);

        return answer;
    }
}
