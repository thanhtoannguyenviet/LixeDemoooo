package Server.model.DTO;

import Server.model.DB.DirectorEntity;

import java.util.ArrayList;
import java.util.List;

public class DirectorDTO {
    private DirectorEntity directorEntity = new DirectorEntity();
    private List<FilmDTO> filmDTOList = new ArrayList<>();
    public DirectorEntity getDirectorEntity() {
        return directorEntity;
    }

    public void setDirectorEntity(DirectorEntity directorEntity) {
        this.directorEntity = directorEntity;
    }

    public List<FilmDTO> getFilmDTOList() {
        return filmDTOList;
    }

    public void setFilmDTOList(List<FilmDTO> filmDTOList) {
        this.filmDTOList = filmDTOList;
    }

    public DirectorDTO(){}
    public DirectorDTO(DirectorEntity directorEntity, List<FilmDTO> filmDTOList) {
        this.directorEntity = directorEntity;
        this.filmDTOList = filmDTOList;
    }
}
