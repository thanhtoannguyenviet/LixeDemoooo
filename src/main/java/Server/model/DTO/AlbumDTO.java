package Server.model.DTO;

import Server.model.DB.*;

import java.util.ArrayList;
import java.util.List;

public class AlbumDTO {
    private AlbumEntity albumEntity = new AlbumEntity();
    private List<SongDTO> songDTOList = new ArrayList<>();
    private ImageEntity imageEntity = new ImageEntity();
    private List<SingerEntity> singerEntity = new ArrayList<>();
    private Criteria criteria = new Criteria();
    public  AlbumDTO(){}

    public AlbumDTO(AlbumEntity albumEntity, List<SongDTO> songDTOList, ImageEntity imageEntity, List<SingerEntity> singerEntity, Criteria criteria) {
        this.albumEntity = albumEntity;
        this.songDTOList = songDTOList;
        this.imageEntity = imageEntity;
        this.singerEntity = singerEntity;
        this.criteria = criteria;
    }

    public AlbumEntity getAlbumEntity() {
        return albumEntity;
    }

    public void setAlbumEntity(AlbumEntity albumEntity) {
        this.albumEntity = albumEntity;
    }

    public List<SongDTO> getSongDTOList() {
        return songDTOList;
    }

    public void setSongDTOList(List<SongDTO> songDTOList) {
        this.songDTOList = songDTOList;
    }

    public ImageEntity getImageEntity() {
        return imageEntity;
    }

    public void setImageEntity(ImageEntity imageEntity) {
        this.imageEntity = imageEntity;
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
