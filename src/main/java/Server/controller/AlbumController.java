package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.*;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/MusicSite/Album")
@RestController
public class AlbumController {
    AlbumDAO albumDAO = new AlbumDAO();
    SongDAO songDAO = new SongDAO();
    AlbumCategoryMusicDAO albumCategoryMusicDAO = new AlbumCategoryMusicDAO();
    SongSingerDAO songSingerDAO = new SongSingerDAO();
    SingerDAO singerDAO = new SingerDAO();
    AlbumSingerDAO albumSingerDAO = new AlbumSingerDAO();
    AlbumSongDAO albumSongDAO = new AlbumSongDAO();
    APIAccountDAO apiAccountDAO = new APIAccountDAO();
	UserDAO userDAO = new UserDAO();
    ImageDAO imageDAO = new ImageDAO();

    @RequestMapping(value = "/Post",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody AlbumInDTO albumInDTO, @RequestHeader String userToken) {
        if (albumInDTO == null || apiAccountDAO.checkToken(albumInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(albumInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        AlbumEntity albumEntity = albumDAO.save(albumInDTO.getAlbumEntity());
        return new ResponseEntity<>(albumEntity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> put(@RequestBody AlbumInDTO albumInDTO, @PathVariable("id") long id, @RequestHeader String userToken) {
        if (albumInDTO == null || apiAccountDAO.checkToken(albumInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(albumInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        if (id == albumInDTO.getAlbumEntity().getId())
            albumDAO.save(albumInDTO.getAlbumEntity());
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> get(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        AlbumEntity albumEntity = albumDAO.getByID(id);

        //albumEntity.setIndex(albumEntity.getIndex() + 1);
        // albumDAO.save(albumEntity);
        if (albumEntity != null)
            return new ResponseEntity<>(getAlbumDTO(albumEntity), HttpStatus.OK);
        else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") long id, @RequestBody APIAccountDTO apiAccountDTO, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        albumDAO.delete(id);
//        List<AlbumCategorymusicEntity> albumCategoryMusicEntity = albumCategoryMusicDAO.getId("albumid", entity.getId() + "");
//        for (AlbumCategorymusicEntity item : albumCategoryMusicEntity) {
//            albumCategoryMusicDAO.delete(item.getId());
//        }
        return new ResponseEntity<>("Delete completed", HttpStatus.OK);
    }

    //    @RequestMapping(value ="/PostToCategory/{idCategoryMusic}",method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<?>updateToCategoryMusic(@RequestBody AlbumDTO entity, @PathVariable("idCategoryMusic") Long id){
//        List<AlbumCategorymusicEntity> albumCategoryMusicEntity = albumCategoryMusicDAO.GetId("categoryid",id+"");
//        for ( AlbumCategorymusicEntity item : albumCategoryMusicEntity) {
//            if(item.getAlbumid()!=entity.getAlbumEntity().getId()){
//                AlbumCategorymusicEntity newItem = new AlbumCategorymusicEntity();
//                newItem.setAlbumid(entity.getAlbumEntity().getId());
//                newItem.setCatagoryid(id);
//                albumCategoryMusicDAO.Save(newItem);
//            }
//        }
//        return new ResponseEntity<>("Update Completed",HttpStatus.OK);
//    }
    @RequestMapping(value = "/PostToCategory/{idCategoryMusic}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> updateToCategoryMusic(@RequestBody AlbumInDTO albumInDTO, @PathVariable("idCategoryMusic") Long id,
                                                   @RequestHeader String userToken) {
        if (albumInDTO == null || apiAccountDAO.checkToken(albumInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(albumInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        AlbumCategorymusicEntity albumCategorymusicEntity = new AlbumCategorymusicEntity();
        albumCategorymusicEntity.setAlbumid(albumInDTO.getAlbumEntity().getId());
        albumCategorymusicEntity.setCatagoryid(id);
        albumCategoryMusicDAO.save(albumCategorymusicEntity);
        return new ResponseEntity<>("Update Completed", HttpStatus.OK);
    }

    @RequestMapping(value = "/DeleteToCategory/{idCategoryMusic}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> updateToCategoryMusic(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("idCategoryMusic") Long id,
                                                   @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        albumCategoryMusicDAO.delete(id);
        return new ResponseEntity<>("Update Completed", HttpStatus.OK);
    }

    @RequestMapping(value = "/Count",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> count(@RequestBody APIAccountDTO apiAccountDTO, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(albumDAO.count(), HttpStatus.OK);
    }

    @RequestMapping(value = "/GetAllHasPage{itemOnpage}/{page}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getPage(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("page") int page,
                                     @PathVariable("itemOnpage") int itemOnpage, @RequestHeader String userToken) {
        try {
            if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
                return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
            }
            Criteria criteria = new Criteria();
            criteria.setClazz(AlbumEntity.class);
            criteria.setItemPerPage(itemOnpage);
            criteria.setCurrentPage(page);
            List<AlbumEntity> albumEntityList = albumDAO.loadDataPagination(criteria);
            List<AlbumDTO> albumDTOList = new ArrayList<>();
            for (AlbumEntity a : albumEntityList
            ) {
                albumDTOList.add(getAlbumDTO(a));
            }
            return new ResponseEntity<>(albumDTOList, HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/GetTop{item}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getTop(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("item") int item,
                                    @RequestHeader String userToken) {
        try {
            if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
                return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
            }
            Criteria criteria = new Criteria();
            criteria.setClazz(AlbumEntity.class);
            criteria.setTop(item);
            List<AlbumEntity> albumEntityList = albumDAO.getTop10(criteria);
            if (!albumEntityList.isEmpty()) {
                List<AlbumDTO> albumDTOList = new ArrayList<>();
                for (AlbumEntity ite : albumEntityList
                ) {
                    if (ite != null)
                        albumDTOList.add(getAlbumDTO(ite));
                }
                return new ResponseEntity<>(albumDTOList, HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    private AlbumDTO getAlbumDTO(AlbumEntity albumEntity) {
        List<SongEntity> songEntityList = new ArrayList<>();
        List<ImageEntity> imgList = imageDAO.getId("album", albumEntity.getId());
        ImageEntity imageEntity = new ImageEntity();
        if (!imgList.isEmpty()) {
            imageEntity = imgList.get(0);
        }
        List<AlbumSongEntity> albumSongEntityList = albumSongDAO.getId("albumid", albumEntity.getId() + "");
        if (!albumSongEntityList.isEmpty()) {
            for (AlbumSongEntity item : albumSongEntityList
            ) {
                SongEntity songEntity = songDAO.getByID(item.getSongid());
                if (songEntity != null)
                    songEntityList.add(songEntity);
            }
        }
        List<SingerEntity> singerEntityList = new ArrayList<>();
        List<AlbumSingerEntity> albumSingerEntityList = albumSingerDAO.getId("albumid", albumEntity.getId() + "");
        for (AlbumSingerEntity item : albumSingerEntityList) {
            SingerEntity singerEntity = singerDAO.getByID(item.getSingerid());
            if (singerEntity != null)
                singerEntityList.add(singerEntity);
        }
        List<AlbumCategorymusicEntity> albumCategorymusicEntities = albumCategoryMusicDAO.getId("albumid", albumEntity.getId() + "");
        List<CategorysongEntity> categorysongEntities = new ArrayList<>();
        CategorySongDAO categorySongDAO = new CategorySongDAO();
        if (albumCategorymusicEntities != null) {
            for (AlbumCategorymusicEntity item : albumCategorymusicEntities) {
                CategorysongEntity categorysongEntity = categorySongDAO.getByID(item.getCatagoryid());
                categorysongEntities.add(categorysongEntity);
            }
        }
        AlbumDTO albumDTO = new AlbumDTO(albumEntity, imageEntity, songEntityList, singerEntityList, categorysongEntities);
        return albumDTO;
    }
}
