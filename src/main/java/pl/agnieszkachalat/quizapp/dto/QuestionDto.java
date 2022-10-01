package pl.agnieszkachalat.quizapp.dto;

import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class QuestionDto extends BaseDto {
    
    private Long qstId;
    private String question;
    private String description;
    private List<AnswerDto> answers;
    private boolean multipleAnswers;
    private String explanation;
    private String tip;
    private List<TagDto> tags;
    private String category;
    private String difficulty;

    public Long getQstId() {
        return qstId;
    }

    public void setQstId(Long qstId) {
        this.qstId = qstId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }

    public boolean isMultipleAnswers() {
        return multipleAnswers;
    }

    public void setMultipleAnswers(boolean multipleAnswers) {
        this.multipleAnswers = multipleAnswers;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    
    @Override
    public int hashCode() {
        Long idToCompare = this.qstId != null ? this.qstId : getId();
        return new HashCodeBuilder().append(idToCompare).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(!(obj instanceof QuestionDto)) {
            return false;
        }
        final QuestionDto other = (QuestionDto) obj;
        Long idToCompare = this.qstId != null ? this.qstId : getId();
        Long otherIdToCompare = other.getQstId() != null ? other.getQstId() : other.getId();
        return new EqualsBuilder().append(idToCompare, otherIdToCompare).isEquals();
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", getId())
                                        .append("question", question)
                                        .append("description", description)
                                        .append("answers", answers)
                                        .append("multipleAnswers", multipleAnswers)
                                        .append("explanation", explanation)
                                        .append("tip", tip)
                                        .append("tags", tags)
                                        .append("category", category)
                                        .append("difficulty", difficulty)
                                        .toString();
    }
}
