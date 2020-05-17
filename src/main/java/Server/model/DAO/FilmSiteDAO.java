package Server.model.DAO;

import Server.model.DB.*;
import Server.model.DTO.FilmDTO;
import Server.model.DTO.SeriFilmDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class FilmSiteDAO {
    FilmDAO filmDAO = new FilmDAO() ;
    DirectorDAO directorDAO = new DirectorDAO();
    ActorDAO actorDAO = new ActorDAO();
    ImageDAO imageDAO = new ImageDAO();
    UploadDAO uploadDAO = new UploadDAO();
    CategoryFilmDAO categoryFilmDAO = new CategoryFilmDAO();
    SeriFilmDAO seriFilmDAO = new SeriFilmDAO();
    FilmActorDAO filmActorDAO = new FilmActorDAO();
    FilmCategoryFilmDAO filmCategoryFilmDAO = new FilmCategoryFilmDAO();
    SerifilmFilmDAO serifilmFilmDAO = new SerifilmFilmDAO();
    public FilmDTO getFilmDTOById(FilmEntity filmEntity){
        DirectorEntity directorEntity = directorDAO.getByID(filmEntity.getDirectorid());
        List<FilmActorEntity> filmActorEntityList= filmActorDAO.getListHasCondition("filmid",filmEntity.getId()+"");
        List<ActorEntity> actorEntityList= new ArrayList<>();
        if(!filmActorEntityList.isEmpty()){
            for(FilmActorEntity item : filmActorEntityList){
                ActorEntity actor = actorDAO.getByID(item.getActorid());
                if(actor!=null)
                    actorEntityList.add(actor);
            }
        }
        List<ImageEntity> imageEntityList = imageDAO.getId("film",filmEntity.getId());
        List<UploadEntity> uploadEntityList = uploadDAO.getId("film",filmEntity.getId());
        List<FilmCategoryfilmEntity> filmCategoryfilmEntities = filmCategoryFilmDAO.getId("filmid",filmEntity.getId()+"");
        List<CategoryfilmEntity> categoryfilmEntityList = new ArrayList<>();
        if(!filmCategoryfilmEntities.isEmpty()){
            for (FilmCategoryfilmEntity item : filmCategoryfilmEntities){
                CategoryfilmEntity catfilm = categoryFilmDAO.getByID(item.getCategoryid());
                if(catfilm!=null)
                categoryfilmEntityList.add(catfilm);
            }
        }
        SerifilmEntity serifilmEntity= seriFilmDAO.getByID(filmEntity.getId());
        FilmDTO filmDTO = new FilmDTO(filmEntity,directorEntity, Collections.unmodifiableList(actorEntityList),Collections.unmodifiableList(imageEntityList),
                Collections.unmodifiableList(uploadEntityList),Collections.unmodifiableList(categoryfilmEntityList),serifilmEntity);
        return filmDTO;
    }
    public FilmDTO getFilmDTOById(Long id){
        FilmEntity filmEntity = filmDAO.getByID(id);
        DirectorEntity directorEntity = directorDAO.getByID(filmEntity.getDirectorid());
        List<FilmActorEntity> filmActorEntityList= filmActorDAO.getListHasCondition("filmid",filmEntity.getId()+"");
        List<ActorEntity> actorEntityList= new ArrayList<>();
        if(!filmActorEntityList.isEmpty()){
            for(FilmActorEntity item : filmActorEntityList){
                ActorEntity actor = actorDAO.getByID(item.getActorid());
                if(actor!=null)
                    actorEntityList.add(actor);
            }
        }
        List<ImageEntity> imageEntityList = imageDAO.getId("film",filmEntity.getId());
        List<UploadEntity> uploadEntityList = uploadDAO.getId("film",filmEntity.getId());
        List<FilmCategoryfilmEntity> filmCategoryfilmEntities = filmCategoryFilmDAO.getId("filmid",filmEntity.getId()+"");
        List<CategoryfilmEntity> categoryfilmEntityList = new ArrayList<>();
        if(!filmCategoryfilmEntities.isEmpty()){
            for (FilmCategoryfilmEntity item : filmCategoryfilmEntities){
                CategoryfilmEntity catfilm = categoryFilmDAO.getByID(item.getCategoryid());
                if(catfilm!=null)
                    categoryfilmEntityList.add(catfilm);
            }
        }
        SerifilmEntity serifilmEntity= seriFilmDAO.getByID(filmEntity.getId());
        FilmDTO filmDTO = new FilmDTO(filmEntity,directorEntity, Collections.unmodifiableList(actorEntityList),Collections.unmodifiableList(imageEntityList),
                Collections.unmodifiableList(uploadEntityList),Collections.unmodifiableList(categoryfilmEntityList),serifilmEntity);
        return filmDTO;
    }
    public SeriFilmDTO getSeriFilmDTO(SerifilmEntity serifilmEntity){
        List<SerifilmFilmEntity> serifilmFilmEntityList = serifilmFilmDAO.getId("seriid",serifilmEntity.getId()+"");
        List<FilmDTO> filmDTOList = new ArrayList<>();
        if(!serifilmFilmEntityList.isEmpty()) {
            for (SerifilmFilmEntity item : serifilmFilmEntityList) {
                FilmDTO filmDTO = getFilmDTOById(item.getFilmid());
                if (filmDTO != null)
                    filmDTOList.add(filmDTO);
            }
        }
        return new SeriFilmDTO(serifilmEntity,filmDTOList);
    }
    public SeriFilmDTO getSeriFilmDTO(Long id){
        SerifilmEntity serifilmEntity = seriFilmDAO.getByID(id);
        List<SerifilmFilmEntity> serifilmFilmEntityList = serifilmFilmDAO.getId("seriid",id+"");
        List<FilmDTO> filmDTOList = new ArrayList<>();
        if(!serifilmFilmEntityList.isEmpty()) {
            for (SerifilmFilmEntity item : serifilmFilmEntityList) {
                FilmDTO filmDTO = getFilmDTOById(item.getFilmid());
                if (filmDTO != null)
                    filmDTOList.add(filmDTO);
            }
        }
        return new SeriFilmDTO(serifilmEntity,filmDTOList);
    }
}
