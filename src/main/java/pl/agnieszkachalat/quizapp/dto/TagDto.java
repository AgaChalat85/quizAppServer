package pl.agnieszkachalat.quizapp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TagDto extends BaseDto {
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getName()).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(!(obj instanceof TagDto)) {
            return false;
        }
        final TagDto other = (TagDto) obj;
        return new EqualsBuilder().append(this.getName(), other.getName()).isEquals();
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", getId())
                                        .append("name", name)
                                        .toString();
    }
}
