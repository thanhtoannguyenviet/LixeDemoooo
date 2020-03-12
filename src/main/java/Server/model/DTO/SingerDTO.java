package Server.model.DTO;

import Server.model.DB.*;

import java.util.ArrayList;
import java.util.List;

public class SingerDTO {
    private SingerEntity singerEntity = new SingerEntity();
    private List<SongDTO> songDTOList = new ArrayList<>();
    private Criteria criteria = new Criteria();
    public SingerDTO(){}

    public SingerDTO(SingerEntity singerEntity, List<SongDTO> songDTOList, Criteria criteria) {
        this.singerEntity = singerEntity;
        this.songDTOList = songDTOList;
        this.criteria = criteria;
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
