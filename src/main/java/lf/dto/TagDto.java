package lf.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lf.entity.Tag;
import lf.enums.UpdateType;
import lf.util.AssertUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagDto implements Serializable {

    private Long id;
    private String tagName;
    private Long userId;
    private Timestamp createTime;
    private Timestamp updateTime;

    private UpdateType updateType;

    @JsonProperty("questions")
    private List<QuestionDto> questionDtoList;

    public Tag dtoToEntity(){
        Tag tag = new Tag();
        tag.setId(id);
        tag.setTagName(tagName);
        tag.setCreateTime(createTime);
        tag.setUpdateTime(updateTime);
        tag.setUserId(userId);
        return tag;
    }

    public void verify() {
        AssertUtil.hasText(tagName, "tagName field can not be empty.");
        AssertUtil.notNull(userId, "userId can not be null.");
    }
}
