package pl.agnieszkachalat.quizapp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AnswerDto extends BaseDto {
    
    private String answer;
    private boolean correct;
    
    public AnswerDto(String answer, boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getId()).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(!(obj instanceof AnswerDto)) {
            return false;
        }
        final AnswerDto other = (AnswerDto) obj;
        return new EqualsBuilder().append(this.getId(), other.getId()).isEquals();
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", getId())
                                        .append("answer", answer)
                                        .append("correct", correct)
                                        .toString();
    }
}
