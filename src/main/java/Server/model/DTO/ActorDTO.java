package Server.model.DTO;

import Server.model.DB.ActorEntity;

import java.util.ArrayList;
import java.util.List;

public class ActorDTO {
    private ActorEntity actorEntity = new ActorEntity();
    private List<FilmDTO> filmDTOList= new ArrayList<>();
    public ActorDTO(){}
    public ActorDTO(ActorEntity actorEntity, List<FilmDTO> filmDTOList) {
        this.actorEntity = actorEntity;
        this.filmDTOList = filmDTOList;
    }

    public ActorEntity getActorEntity() {
        return actorEntity;
    }

    public void setActorEntity(ActorEntity actorEntity) {
        this.actorEntity = actorEntity;
    }

    public List<FilmDTO> getFilmDTOList() {
        return filmDTOList;
    }

    public void setFilmDTOList(List<FilmDTO> filmDTOList) {
        this.filmDTOList = filmDTOList;
    }
}
