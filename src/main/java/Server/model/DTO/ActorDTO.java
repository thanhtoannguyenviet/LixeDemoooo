package Server.model.DTO;

import Server.model.DB.ActorEntity;
import Server.model.DB.ImageEntity;

import java.util.ArrayList;
import java.util.List;

public class ActorDTO {
    private ActorEntity actorEntity = new ActorEntity();
    private ImageEntity imageEntity = new ImageEntity();
    private List<FilmDTO> filmDTOList= new ArrayList<>();
    public ActorDTO(){}
    public ActorDTO(ActorEntity actorEntity,ImageEntity imageEntity, List<FilmDTO> filmDTOList) {
        this.actorEntity = actorEntity;
        this.filmDTOList = filmDTOList;
        this.imageEntity = imageEntity;
    }

    public ImageEntity getImageEntity() {
        return imageEntity;
    }

    public void setImageEntity(ImageEntity imageEntity) {
        this.imageEntity = imageEntity;
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
