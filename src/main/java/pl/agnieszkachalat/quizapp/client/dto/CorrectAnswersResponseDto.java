package pl.agnieszkachalat.quizapp.client.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CorrectAnswersResponseDto {
    
    private boolean answer_a_correct;
    private boolean answer_b_correct;
    private boolean answer_c_correct;
    private boolean answer_d_correct;
    private boolean answer_e_correct;
    private boolean answer_f_correct;
    
    public boolean isAnswer_a_correct() {
        return answer_a_correct;
    }

    public boolean isAnswer_b_correct() {
        return answer_b_correct;
    }

    public boolean isAnswer_c_correct() {
        return answer_c_correct;
    }

    public boolean isAnswer_d_correct() {
        return answer_d_correct;
    }

    public boolean isAnswer_e_correct() {
        return answer_e_correct;
    }

    public boolean isAnswer_f_correct() {
        return answer_f_correct;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("answer_a_correct", answer_a_correct)
                                        .append("answer_b_correct", answer_b_correct)
                                        .append("answer_c_correct", answer_c_correct)
                                        .append("answer_d_correct", answer_d_correct)
                                        .append("answer_e_correct", answer_e_correct)
                                        .append("answer_f_correct", answer_f_correct)
                                        .toString();
    }
}
