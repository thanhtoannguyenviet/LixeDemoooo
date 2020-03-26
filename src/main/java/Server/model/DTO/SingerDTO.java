package Server.model.DTO;

import Server.model.DB.*;

import java.util.ArrayList;
import java.util.List;

public class SingerDTO {
    private SingerEntity singerEntity = new SingerEntity();
    private List<SongDTO> songDTOList = new ArrayList<>();
    private ImageEntity imageEntity = new ImageEntity();
    private Criteria criteria = new Criteria();
    public SingerDTO(){}

    public SingerDTO(SingerEntity singerEntity, List<SongDTO> songDTOList, ImageEntity imageEntity, Criteria criteria) {
        this.singerEntity = singerEntity;
        this.songDTOList = songDTOList;
        this.imageEntity = imageEntity;
        this.criteria = criteria;
    }

    public ImageEntity getImageEntity() {
        return imageEntity;
    }

    public void setImageEntity(ImageEntity imageEntity) {
        this.imageEntity = imageEntity;
    }

    public SingerEntity getSingerEntity() {
        return singerEntity;
    }

    public void setSingerEntity(SingerEntity singerEntity) {
        this.singerEntity = singerEntity;
    }

    public List<SongDTO> getSongDTOList() {
        return songDTOList;
    }

    public void setSongDTOList(List<SongDTO> songDTOList) {
        this.songDTOList = songDTOList;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }
}
