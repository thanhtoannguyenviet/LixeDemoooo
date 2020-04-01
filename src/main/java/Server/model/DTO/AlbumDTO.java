package Server.model.DTO;

import Server.model.DB.*;

import java.util.ArrayList;
import java.util.List;

public class AlbumDTO {
    private AlbumEntity albumEntity = new AlbumEntity();
    private List<SongEntity> songDTOList = new ArrayList<>();
     private List<SingerEntity> singerEntity = new ArrayList<>();
    private Criteria criteria = new Criteria();
    public  AlbumDTO(){}

    public AlbumDTO(AlbumEntity albumEntity, List<SongEntity> songDTOList, List<SingerEntity> singerEntity, Criteria criteria) {
        this.albumEntity = albumEntity;
        this.songDTOList = songDTOList;
       this.singerEntity = singerEntity;
        this.criteria = criteria;
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

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }
}
