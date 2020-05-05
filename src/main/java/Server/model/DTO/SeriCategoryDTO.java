package Server.model.DTO;

import Server.model.DB.CategoryfilmEntity;

import java.util.ArrayList;
import java.util.List;

public class SeriCategoryDTO {
    CategoryfilmEntity categoryfilmEntity = new CategoryfilmEntity();
    List<SeriFilmDTO> seriFilmDTOList = new ArrayList<>();
    public SeriCategoryDTO(){}
    public SeriCategoryDTO(CategoryfilmEntity categoryfilmEntity, List<SeriFilmDTO> seriFilmDTOList) {
        this.categoryfilmEntity = categoryfilmEntity;
        this.seriFilmDTOList = seriFilmDTOList;
    }
}
