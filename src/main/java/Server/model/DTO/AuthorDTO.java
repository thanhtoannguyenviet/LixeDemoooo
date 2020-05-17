package Server.model.DTO;

import Server.model.DB.AuthorEntity;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO {
    private AuthorEntity authorEntity = new AuthorEntity();
    private List<SongDTO> songDTOList = new ArrayList<>();

    public AuthorDTO(){}
    public AuthorDTO(AuthorEntity authorEntity, List<SongDTO> songDTOList) {
        this.authorEntity = authorEntity;
        this.songDTOList = songDTOList;
    }

    public AuthorEntity getAuthorEntity() {
        return authorEntity;
    }

    public void setAuthorEntity(AuthorEntity authorEntity) {
        this.authorEntity = authorEntity;
    }

    public List<SongDTO> getSongDTOList() {
        return songDTOList;
    }

    public void setSongDTOList(List<SongDTO> songDTOList) {
        this.songDTOList = songDTOList;
    }
}
