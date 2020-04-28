package Server.model.DAO;

import Server.model.DB.*;
import Server.model.DTO.FilmDTO;
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
    public FilmDTO getFilmDTOById(long id){
        FilmEntity filmEntity = filmDAO.getByID(id);
        DirectorEntity directorEntity = directorDAO.getByID(filmEntity.getDirectorid());
        List<FilmActorEntity> filmActorEntityList= filmActorDAO.getListHasCondition("filmid",filmEntity.getId()+"");
        List<ActorEntity> actorEntityList= new ArrayList<>();
        for(FilmActorEntity item : filmActorEntityList){
            actorEntityList.add(actorDAO.getByID(item.getId()));
        }
        List<ImageEntity> imageEntityList = imageDAO.getId("film",filmEntity.getId());
        List<UploadEntity> uploadEntityList = uploadDAO.getId("film",filmEntity.getId());
        List<FilmCategoryfilmEntity> filmCategoryfilmEntities = filmCategoryFilmDAO.getId("filmid",filmEntity.getId()+"");
        List<CategoryfilmEntity> categoryfilmEntityList = new ArrayList<>();
        for (FilmCategoryfilmEntity item : filmCategoryfilmEntities){
            categoryfilmEntityList.add(categoryFilmDAO.getByID(item.getCategoryid()));
        }
        SerifilmEntity serifilmEntity= seriFilmDAO.getByID(filmEntity.getId());
        FilmDTO filmDTO = new FilmDTO(filmEntity,directorEntity, Collections.unmodifiableList(actorEntityList),Collections.unmodifiableList(imageEntityList),
                Collections.unmodifiableList(uploadEntityList),Collections.unmodifiableList(categoryfilmEntityList),serifilmEntity);
        return filmDTO;
    }
}
