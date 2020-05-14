package Server.model.DTO;

import Server.model.DB.AlbumEntity;
import Server.model.DB.SingerEntity;
import Server.model.DB.SongEntity;

import java.util.ArrayList;
import java.util.List;

public class AlbumDTO {
    public AlbumEntity albumEntity = new AlbumEntity();
    public List<SongEntity> songDTOList = new ArrayList<>();
    public List<SingerEntity> singerEntity = new ArrayList<>();

    public AlbumDTO() {
    }

    public AlbumDTO(AlbumEntity albumEntity, List<SongEntity> songDTOList, List<SingerEntity> singerEntity) {
        this.albumEntity = albumEntity;
        this.songDTOList = songDTOList;
        this.singerEntity = singerEntity;
    }

    public AlbumEntity getAlbumEntity() {
        return albumEntity;
    }

    public void setAlbumEntity(AlbumEntity albumEntity) {
        this.albumEntity = albumEntity;
    }

    public List<SongEntity> getSongDTOList() {
        return songDTOList;
    }

    public void setSongDTOList(List<SongEntity> songDTOList) {
        this.songDTOList = songDTOList;
    }

    public List<SingerEntity> getSingerEntity() {
        return singerEntity;
    }

    public void setSingerEntity(List<SingerEntity> singerEntity) {
        this.singerEntity = singerEntity;
    }

}
