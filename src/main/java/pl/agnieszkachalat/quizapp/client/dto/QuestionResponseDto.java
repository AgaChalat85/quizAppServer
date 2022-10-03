package pl.agnieszkachalat.quizapp.client.dto;

import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class QuestionResponseDto {
    
    private Long id;
    private String question;
    private String description;
    private AnswersResponsDto answers;
    private boolean multiple_correct_answers;
    private CorrectAnswersResponseDto correct_answers;
    private String correct_answer;
    private String explanation;
    private String tip;
    private List<TagResponseDto> tags;
    private String category;
    private String difficulty;

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getDescription() {
        return description;
    }

    public AnswersResponsDto getAnswers() {
        return answers;
    }

    public boolean isMultiple_correct_answers() {
        return multiple_correct_answers;
    }

    public CorrectAnswersResponseDto getCorrect_answers() {
        return correct_answers;
    }
    
    public String getCorrect_answer() {
        return correct_answer;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getTip() {
        return tip;
    }

    public List<TagResponseDto> getTags() {
        return tags;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getId()).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof QuestionResponseDto)) {
            return false;
        }
        
        final QuestionResponseDto other = (QuestionResponseDto) obj;
        return new EqualsBuilder().append(this.getId(), other.getId()).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id)
                                        .append("question", question)
                                        .append("description", description)
                                        .append("answers", answers)
                                        .append("multiple_correct_answers", multiple_correct_answers)
                                        .append("correct_answers", correct_answers)
                                        .append("correct_answer", correct_answer)
                                        .append("explanation", explanation)
                                        .append("tip", tip)
                                        .append("tags", tags)
                                        .append("category", category)
                                        .append("difficulty", difficulty)
                                        .toString();
    }
}
