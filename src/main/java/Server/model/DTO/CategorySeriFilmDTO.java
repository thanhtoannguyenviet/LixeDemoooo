package Server.model.DTO;

import Server.model.DB.CategoryfilmEntity;

import java.util.ArrayList;
import java.util.List;

public class CategorySeriFilmDTO {
    private CategoryfilmEntity categoryfilmEntity= new CategoryfilmEntity();
    private List<SeriFilmDTO> seriFilmDTOList = new ArrayList<>();
    public CategorySeriFilmDTO(){}
    public CategorySeriFilmDTO(CategoryfilmEntity categoryfilmEntity, List<SeriFilmDTO> seriFilmDTOList) {
        this.categoryfilmEntity = categoryfilmEntity;
        this.seriFilmDTOList = seriFilmDTOList;
    }

    public CategoryfilmEntity getCategoryfilmEntity() {
        return categoryfilmEntity;
    }

    public void setCategoryfilmEntity(CategoryfilmEntity categoryfilmEntity) {
        this.categoryfilmEntity = categoryfilmEntity;
    }

    public List<SeriFilmDTO> getSeriFilmDTOList() {
        return seriFilmDTOList;
    }

    public void setSeriFilmDTOList(List<SeriFilmDTO> seriFilmDTOList) {
        this.seriFilmDTOList = seriFilmDTOList;
    }
}
