package Server.model.DTO;

import Server.model.DB.DirectorEntity;

import java.util.ArrayList;
import java.util.List;

public class DirectorDTO {
    private DirectorEntity directorEntity = new DirectorEntity();
    private List<FilmDTO> filmDTOList = new ArrayList<>();
    public DirectorDTO(){}
    public DirectorDTO(DirectorEntity directorEntity, List<FilmDTO> filmDTOList) {
        this.directorEntity = directorEntity;
        this.filmDTOList = filmDTOList;
    }
}
