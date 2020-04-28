package Server.controller;

import Server.model.DAO.CategorySongDAO;
import Server.model.DAO.LogDAO;
import Server.model.DAO.SignalDAO;
import Server.model.DB.CategorysongEntity;
import Server.model.DB.LogEntity;
import Server.model.DTO.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/MusicSite/Category")
@RestController
public class CategorySongController {
    @Autowired
    CategorySongDAO categorySongDAO;
    SignalDAO signalDAO;
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
    @RequestMapping(value = "/GetDetail/{id}" , method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getDetail(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(categorySongDAO.getByID(id),HttpStatus.OK);
    }
    @RequestMapping(value = "/GetAll/" , method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAll()
    {
        return new ResponseEntity<>(categorySongDAO.getAll(),HttpStatus.OK);
    }
    @RequestMapping(value = "/GetTop10/" , method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getTop10()
    {
        try{
        Criteria criteria = new Criteria();
        criteria.setClazz(CategorysongEntity.class);
        criteria.setTop(10);
        return new ResponseEntity<>(categorySongDAO.getTop10(criteria),HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value ="/GetAllHasPage/{page}", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getPage (@PathVariable("page") int page){
        try{
        Criteria criteria = new Criteria();
        criteria.setClazz(CategorysongEntity.class);
        criteria.setCurrentPage(page);
        return new ResponseEntity<>(categorySongDAO.loadDataPagination(criteria),HttpStatus.OK);
        } catch (Exception e) {
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
            return new ResponseEntity<>(categorySongDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            new LogDAO().save(new LogEntity(e));
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
}
