package pl.agnieszkachalat.quizapp.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ParametersDto extends BaseDto {

    private String category;
    private String difficulty;
    private Integer limit;

    public ParametersDto(String category, String difficulty, Integer limit) {
        this.category = category;
        this.difficulty = difficulty;
        this.limit = limit;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("category", category)
                                        .append("difficulty", difficulty)
                                        .append("limit", limit)
                                        .toString();
    }
}
