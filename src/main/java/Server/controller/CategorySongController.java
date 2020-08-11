package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.*;
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
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "/Post",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> postCategorySong(@RequestBody CategorysongInDTO categorysongInDTO) {
        if (categorysongInDTO == null || categorysongInDTO.getApiToken() == null
                || categorysongInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(categorysongInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        CategorysongEntity categorySongEntity = categorySongDAO.save(categorysongInDTO.getCategorysongEntity());
        return new ResponseEntity<>(categorySongEntity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateCategorySong(@RequestBody CategorysongInDTO categorysongInDTO, @PathVariable Long id) {
        if (categorysongInDTO == null || categorysongInDTO.getApiToken() == null
                || categorysongInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(categorysongInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (id == categorysongInDTO.getCategorysongEntity().getId()) {
            categorySongDAO.save(categorysongInDTO.getCategorysongEntity());
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@RequestBody String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (categorySongDAO.getByID(id) != null) {
            categorySongDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/GetDetail/{id}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getDetail(@RequestBody String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        CategorysongEntity categorysongEntity = categorySongDAO.getByID(id);
        if (categorysongEntity != null) {
            CategorySongDTO categorySongDTO = getCategorySongDTO(categorysongEntity);
            return new ResponseEntity<>(categorySongDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/GetAll", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getAll(@RequestBody String apiToken) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        List<CategorysongEntity> categorysongEntityList = categorySongDAO.getAll();
        List<CategorySongDTO> categorySongDTOList = new ArrayList<>();
        if (!categorysongEntityList.isEmpty()) {
            for (CategorysongEntity item : categorysongEntityList) {
                categorySongDTOList.add(getCategorySongDTO(item));
            }
            return new ResponseEntity<>(categorySongDTOList, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/GetTop{iTop}/", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getTop10(@RequestBody String apiToken, @PathVariable("iTop") int iTop) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            Criteria criteria = new Criteria();
            criteria.setClazz(CategorysongEntity.class);
            criteria.setTop(iTop);
            List<CategorysongEntity> categorysongEntityList = categorySongDAO.getTop10(criteria);
            List<CategorySongDTO> categorySongDTOList = new ArrayList<>();
            if (!categorysongEntityList.isEmpty()) {
                for (CategorysongEntity item : categorysongEntityList) {
                    categorySongDTOList.add(getCategorySongDTO(item));
                }
                return new ResponseEntity<>(categorySongDTOList, HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/GetAllHasPage{itemOnPage}/{page}", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getPage(@RequestBody String apiToken, @PathVariable("page") int page, @PathVariable("itemOnPage") int itemOnPage) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            Criteria criteria = new Criteria();
            criteria.setClazz(CategorysongEntity.class);
            criteria.setCurrentPage(page);
            criteria.setItemPerPage(itemOnPage);
            List<CategorysongEntity> categorysongEntityList = categorySongDAO.loadDataPagination(criteria);
            List<CategorySongDTO> categorySongDTOList = new ArrayList<>();
            if (!categorysongEntityList.isEmpty()) {
                for (CategorysongEntity item : categorysongEntityList) {
                    categorySongDTOList.add(getCategorySongDTO(item));
                }
                return new ResponseEntity<>(categorySongDTOList, HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/Count", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> count(@RequestBody String apiToken) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(categorySongDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            new LogDAO().save(new LogEntity(e));
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/GetRandom{iRandom}/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public ResponseEntity<?> getRandom(@RequestBody String apiToken, @PathVariable("iRandom") int iRandom) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            Criteria criteria = new Criteria();
            criteria.setTop(iRandom);
            criteria.setClazz(CategorysongEntity.class);
            List<CategorysongEntity> categorysongEntityList = categorySongDAO.getTopRandom(criteria);
            if (!categorysongEntityList.isEmpty()) {
                List<CategorySongDTO> categorySongDTOList = new ArrayList<>();
                for (CategorysongEntity item : categorysongEntityList
                ) {
                    CategorySongDTO categorySongDTO = getCategorySongDTO(item);
                    if (categorySongDTO != null)
                        categorySongDTOList.add(categorySongDTO);
                }
                return new ResponseEntity<>(categorySongDTOList, HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    private CategorySongDTO getCategorySongDTO(CategorysongEntity categorysongEntity) {
        List<SongCategorysongEntity> songCategorysongEntityList = songCategorySongDAO.getId("categoryid", categorysongEntity.getId() + "");
        List<SongDTO> songDTOList = new ArrayList<>();
        if (!songCategorysongEntityList.isEmpty()) {
            for (SongCategorysongEntity item : songCategorysongEntityList) {
                SongDTO songDTO = songSiteDAO.getSongDTOById(item.getSongid());
                if (songDTO != null)
                    songDTOList.add(songDTO);
            }
        }
        return new CategorySongDTO(categorysongEntity, songDTOList);
    }
}
