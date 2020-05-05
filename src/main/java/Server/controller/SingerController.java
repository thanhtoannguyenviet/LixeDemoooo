package Server.controller;
import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.Criteria;
import Server.model.DTO.SingerDTO;
import Server.model.DTO.SongDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/MusicSite/Singer")
@RestController
public class SingerController {
    @Autowired
    SingerDAO singerDAO;
    SongSingerDAO songSingerDAO;
    SongSiteDAO songSiteDAO;
    @RequestMapping(value = "Post/",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody SingerEntity singer){
        singerDAO.save(singer);
       return new ResponseEntity<>("Post completed",HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> put(@RequestBody SingerEntity singer, @PathVariable("id") Long id){
        if(singerDAO.getByID(id)!=null)
        {
            singerDAO.save(singer);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Singer/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(singerDAO.getByID(id)!=null){
            singerDAO.delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/GetDetail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        return  new ResponseEntity<>(singerDAO.getByID(id),HttpStatus.OK);
    }
    @RequestMapping(value = "/GetTop{itop}" , method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getTop10(@PathVariable("itop") int itop)
    {
        try{
            Criteria criteria = new Criteria();
            criteria.setClazz(SingerEntity.class);
            criteria.setTop(itop);
            List<SingerEntity> singerEntityList =singerDAO.getTop10(criteria) ;
            List<SingerDTO> singerDTOList = new ArrayList<>();
            if(!singerEntityList.isEmpty()){
                for (SingerEntity item : singerEntityList) {
                    singerDTOList.add(getSingerDTO(item));
                }
                return new ResponseEntity<>(singerDTOList,HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value ="/GetAllHasPage{itemOnPage}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getPage (@PathVariable("page") int page,@PathVariable("itemOnPage") int itemOnPage){
        try{
            Criteria criteria = new Criteria();
            criteria.setClazz(SingerEntity.class);
            criteria.setCurrentPage(page);
            criteria.setItemPerPage(itemOnPage);
            List<SingerEntity> singerEntityList =singerDAO.loadDataPagination(criteria) ;
            List<SingerDTO> singerDTOList = new ArrayList<>();
            if(!singerEntityList.isEmpty()){
                for (SingerEntity item : singerEntityList) {
                    singerDTOList.add(getSingerDTO(item));
                }
                return new ResponseEntity<>(singerDTOList,HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value ="/Count", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> count (){
        try {
            return new ResponseEntity<>(singerDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            new LogDAO().save(new LogEntity(e));
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
    private SingerDTO getSingerDTO(SingerEntity singerEntity){
        List<SongSingerEntity> songSingerEntityList = songSingerDAO.getId("singerid",singerEntity.getId()+"");
        List<SongDTO> songDTOList = new ArrayList<>();
        for ( SongSingerEntity item : songSingerEntityList
             ) {
            SongDTO songDTO = songSiteDAO.getSongDTOById(item.getSongid());
            if(songDTO!=null)
                songDTOList.add(songDTO);
        }
        SingerDTO singerDTO = new SingerDTO(singerEntity,songDTOList);
        return singerDTO;
    }
}
