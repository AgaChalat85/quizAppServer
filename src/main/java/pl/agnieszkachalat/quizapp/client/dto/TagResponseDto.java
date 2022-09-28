package pl.agnieszkachalat.quizapp.client.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TagResponseDto {
    
    private String name;

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name)
                                        .toString();
    }
}
