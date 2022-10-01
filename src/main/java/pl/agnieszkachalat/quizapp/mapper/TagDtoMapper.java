package pl.agnieszkachalat.quizapp.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import pl.agnieszkachalat.quizapp.client.dto.TagResponseDto;
import pl.agnieszkachalat.quizapp.dto.TagDto;

@Mapper(componentModel = "spring")
public interface TagDtoMapper {
    
    TagDto mapToTagDto(TagResponseDto tagResponseDto);
    
    List<TagDto> mapToTagDtoList(List<TagResponseDto> tagResponseDtoList);
}
