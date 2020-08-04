package Server.model.DTO;

import Server.model.DB.SongEntity;

public class SongPostDTO {
    SongEntity songEntity = new SongEntity();
    String keyword;

    public SongPostDTO(SongEntity songEntity, String keyword) {
        this.songEntity = songEntity;
        this.keyword = keyword;
    }
    public SongPostDTO(){}

    public SongEntity getSongEntity() {
        return songEntity;
    }

    public void setSongEntity(SongEntity songEntity) {
        this.songEntity = songEntity;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
