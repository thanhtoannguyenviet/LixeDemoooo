package Server.model.DTO;

import Server.model.DB.CategorysongEntity;

import java.util.ArrayList;
import java.util.List;

public class CategorySongDTO {
    private CategorysongEntity categorysongEntity = new CategorysongEntity();
    private List<SongDTO> songDTOList = new ArrayList<>();
    public CategorySongDTO(){}
    public CategorySongDTO(CategorysongEntity categorysongEntity, List<SongDTO> songDTOList) {
        this.categorysongEntity = categorysongEntity;
        this.songDTOList = songDTOList;
    }
}
