package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.CategoryFilmDTO;
import Server.model.DTO.CategorySongDTO;
import Server.model.DTO.Criteria;
import Server.model.DTO.SongDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/MusicSite/Category")
@RestController
public class CategorySongController {
    @Autowired
    CategorySongDAO categorySongDAO;
    SongCategorySongDAO songCategorySongDAO = new SongCategorySongDAO();
    SongSiteDAO songSiteDAO = new SongSiteDAO();
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> postCategorySong(@RequestBody CategorysongEntity categorySongEntity){
        categorySongDAO.save(categorySongEntity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> updateCategorySong (@RequestBody CategorysongEntity singer, @PathVariable Long id){
        if(id==singer.getId())
        {
            categorySongDAO.save(singer);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(categorySongDAO.getByID(id)!=null){
            categorySongDAO.delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/GetDetail/{id}" , method = RequestMethod.GET,produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getDetail(@PathVariable("id") Long id) {
        CategorysongEntity categorysongEntity = categorySongDAO.getByID(id);
        if (categorysongEntity != null) {
            CategorySongDTO categorySongDTO = getCategorySongDTO(categorysongEntity);
            return new ResponseEntity<>(categorySongDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/GetAll" , method = RequestMethod.GET,produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getAll()
    {
        List< CategorysongEntity> categorysongEntityList = categorySongDAO.getAll();
        List<CategorySongDTO> categorySongDTOList = new ArrayList<>();
        if(!categorysongEntityList.isEmpty()){
            for (CategorysongEntity item: categorysongEntityList){
                categorySongDTOList.add(getCategorySongDTO(item));
            }
            return new ResponseEntity<>(categorySongDTOList,HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/GetTop{iTop}/" , method = RequestMethod.GET,produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getTop10(@PathVariable("iTop") int iTop)
    {
        try{
            Criteria criteria = new Criteria();
            criteria.setClazz(CategorysongEntity.class);
            criteria.setTop(iTop);
            List< CategorysongEntity> categorysongEntityList = categorySongDAO.getTop10(criteria);
            List<CategorySongDTO> categorySongDTOList = new ArrayList<>();
            if(!categorysongEntityList.isEmpty()){
                for (CategorysongEntity item: categorysongEntityList){
                categorySongDTOList.add(getCategorySongDTO(item));
                }
                return new ResponseEntity<>(categorySongDTOList,HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value ="/GetAllHasPage{itemOnPage}/{page}", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public  ResponseEntity<?> getPage (@PathVariable("page") int page,@PathVariable("itemOnPage") int itemOnPage){
        try{
            Criteria criteria = new Criteria();
            criteria.setClazz(CategorysongEntity.class);
            criteria.setCurrentPage(page);
            criteria.setItemPerPage(itemOnPage);
            List<CategorysongEntity> categorysongEntityList = categorySongDAO.loadDataPagination(criteria);
            List<CategorySongDTO> categorySongDTOList = new ArrayList<>();
            if(!categorysongEntityList.isEmpty()){
                for (CategorysongEntity item: categorysongEntityList){
                    categorySongDTOList.add(getCategorySongDTO(item));
                }
                return new ResponseEntity<>(categorySongDTOList,HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value ="/Count", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public  ResponseEntity<?> count (){
        try {
            return new ResponseEntity<>(categorySongDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            new LogDAO().save(new LogEntity(e));
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/GetRandom{iRandom}/",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public ResponseEntity<?> getRandom(@PathVariable("iRandom") int iRandom){
        try{
            Criteria criteria = new Criteria();
            criteria.setTop(iRandom);
            criteria.setClazz(CategorysongEntity.class);
            List<CategorysongEntity> categorysongEntityList = categorySongDAO.getTopRandom(criteria);
            if(!categorysongEntityList.isEmpty()){
                List<CategorySongDTO> categorySongDTOList = new ArrayList<>();
                for ( CategorysongEntity item : categorysongEntityList
                ) {
                    CategorySongDTO categorySongDTO = getCategorySongDTO(item);
                    if(categorySongDTO!=null)
                        categorySongDTOList.add(categorySongDTO);
                }
                return new ResponseEntity<>(categorySongDTOList,HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
    private CategorySongDTO getCategorySongDTO(CategorysongEntity categorysongEntity){
        List<SongCategorysongEntity> songCategorysongEntityList = songCategorySongDAO.getId("categoryid",categorysongEntity.getId()+"");
        List<SongDTO> songDTOList = new ArrayList<>();
        if(!songCategorysongEntityList.isEmpty()){
            for( SongCategorysongEntity item : songCategorysongEntityList){
                SongDTO songDTO = songSiteDAO.getSongDTOById(item.getSongid());
                if(songDTO!=null)
                    songDTOList.add(songDTO);
            }
        }
        return new CategorySongDTO(categorysongEntity,songDTOList);
    }
}
