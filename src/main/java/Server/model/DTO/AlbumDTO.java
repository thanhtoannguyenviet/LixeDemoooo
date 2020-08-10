package Server.model.DTO;

import Server.model.DB.AlbumEntity;
import Server.model.DB.ImageEntity;
import Server.model.DB.SingerEntity;
import Server.model.DB.SongEntity;

import java.util.ArrayList;
import java.util.List;

public class AlbumDTO {
    private AlbumEntity albumEntity = new AlbumEntity();
    private ImageEntity imageEntity = new ImageEntity();
    private List<SongEntity> songDTOList = new ArrayList<>();
    private List<SingerEntity> singerEntity = new ArrayList<>();

    public AlbumDTO(AlbumEntity albumEntity, ImageEntity imageEntity, List<SongEntity> songDTOList, List<SingerEntity> singerEntity) {
        this.albumEntity = albumEntity;
        this.imageEntity = imageEntity;
        this.songDTOList = songDTOList;
        this.singerEntity = singerEntity;
    }

    public ImageEntity getImageEntity() {
        return imageEntity;
    }

    public void setImageEntity(ImageEntity imageEntity) {
        this.imageEntity = imageEntity;
    }

    public AlbumDTO() {
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
