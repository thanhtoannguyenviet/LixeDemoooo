package Server.model.DTO;

import Server.model.DB.*;

import java.util.ArrayList;
import java.util.List;

public class SingerDTO {
    public SingerEntity singerEntity = new SingerEntity();
    public List<SongDTO> songDTOList = new ArrayList<>();
     public SingerDTO(){}

    public SingerDTO(SingerEntity singerEntity, List<SongDTO> songDTOList) {
        this.singerEntity = singerEntity;
        this.songDTOList = songDTOList;
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

  }
