package pl.agnieszkachalat.quizapp.dto;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CriteriaDto extends BaseDto {

    private String category;
    private String difficulty;
    private Integer limit;
    private List<String> tags;
    
    public CriteriaDto() {
        this.limit = 1;
    }

    public CriteriaDto(String category, String difficulty, Integer limit, List<String> tags) {
        this.category = category;
        this.difficulty = difficulty;
        this.limit = limit;
        this.tags = tags;
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
    
        public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("category", category)
                                        .append("difficulty", difficulty)
                                        .append("limit", limit)
                                        .append("tags", tags)
                                        .toString();
    }
}
