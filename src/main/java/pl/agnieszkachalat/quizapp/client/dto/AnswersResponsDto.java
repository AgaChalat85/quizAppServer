package pl.agnieszkachalat.quizapp.client.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AnswersResponsDto {
    
    private String answer_a;
    private String answer_b;
    private String answer_c;
    private String answer_d;
    private String answer_e;
    private String answer_f;

    public String getAnswer_a() {
        return answer_a;
    }

    public String getAnswer_b() {
        return answer_b;
    }

    public String getAnswer_c() {
        return answer_c;
    }

    public String getAnswer_d() {
        return answer_d;
    }

    public String getAnswer_e() {
        return answer_e;
    }

    public String getAnswer_f() {
        return answer_f;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("answer_a", answer_a)
                                        .append("answer_b", answer_b)
                                        .append("answer_c", answer_c)
                                        .append("answer_d", answer_d)
                                        .append("answer_e", answer_e)
                                        .append("answer_f", answer_f)
                                        .toString();
    }
}
