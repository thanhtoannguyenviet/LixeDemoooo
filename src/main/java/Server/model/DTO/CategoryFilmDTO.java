package Server.model.DTO;

import Server.model.DB.CategoryfilmEntity;

import java.util.ArrayList;
import java.util.List;

public class CategoryFilmDTO {
    public CategoryfilmEntity categoryfilmEntity = new CategoryfilmEntity();
    public List<FilmDTO> filmDTOList = new ArrayList<>();

    public CategoryFilmDTO(CategoryfilmEntity categoryfilmEntity, List<FilmDTO> filmDTOList) {
        this.categoryfilmEntity = categoryfilmEntity;
        this.filmDTOList = filmDTOList;
    }
    public CategoryFilmDTO(){}

}
