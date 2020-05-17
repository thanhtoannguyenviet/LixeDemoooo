package Server.model.DTO;

import Server.model.DB.CategoryfilmEntity;

import java.util.ArrayList;
import java.util.List;

public class CategoryFilmDTO {
    private CategoryfilmEntity categoryfilmEntity = new CategoryfilmEntity();
    private List<FilmDTO> filmDTOList = new ArrayList<>();

    public CategoryfilmEntity getCategoryfilmEntity() {
        return categoryfilmEntity;
    }

    public void setCategoryfilmEntity(CategoryfilmEntity categoryfilmEntity) {
        this.categoryfilmEntity = categoryfilmEntity;
    }

    public List<FilmDTO> getFilmDTOList() {
        return filmDTOList;
    }

    public void setFilmDTOList(List<FilmDTO> filmDTOList) {
        this.filmDTOList = filmDTOList;
    }

    public CategoryFilmDTO(CategoryfilmEntity categoryfilmEntity, List<FilmDTO> filmDTOList) {
        this.categoryfilmEntity = categoryfilmEntity;
        this.filmDTOList = filmDTOList;
    }
    public CategoryFilmDTO(){}

}
