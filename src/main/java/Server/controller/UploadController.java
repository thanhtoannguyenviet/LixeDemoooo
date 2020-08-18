package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.APIAccountDTO;
import Server.model.DTO.UploadInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/File")
@RestController
public class UploadController {
    @Autowired
    UploadDAO uploadDAO;
    APIAccountDAO apiAccountDAO = new APIAccountDAO();
    SongDAO songDAO = new SongDAO();
    FilmDAO filmDAO = new FilmDAO();
    AlbumDAO albumDAO = new AlbumDAO();
    SeriFilmDAO serifilmDAO = new SeriFilmDAO();
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> postImage(@RequestBody UploadInDTO uploadInDTO) {
        if (uploadInDTO == null || uploadInDTO.getApiToken() == null
                || uploadInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(uploadInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        UploadEntity entity = uploadDAO.save(uploadInDTO.getUploadEntity());
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateAccount(@RequestBody UploadInDTO uploadInDTO, @PathVariable("id") Long id) {
        if (uploadInDTO == null || uploadInDTO.getApiToken() == null
                || uploadInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(uploadInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (uploadDAO.getByID(id) != null)
            uploadDAO.save(uploadInDTO.getUploadEntity());
        return new ResponseEntity<>("Update Completed", HttpStatus.OK);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteAccount(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id) {
        if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        uploadDAO.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/Get/{id}",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity<?> get(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id) {
        if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(uploadDAO.getByID(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/InsertIndex/{id}/{model}",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity<?> increaseIndex(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id,@PathVariable("model") String model ) {
        if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if(model.equals("song")){
            SongEntity songEntity = songDAO.getByID(id);
            if(songEntity!=null){
                songEntity.setIndex(songEntity.getIndex()+1);
                songDAO.save(songEntity);
            }
        }
        else if(model.equals("film")){
            FilmEntity filmEntity = filmDAO.getByID(id);
            if(filmEntity!= null){
                filmEntity.setIndex(filmEntity.getIndex()+1);
                filmDAO.save(filmEntity);
                }
        }
        else if(model.equals("serifilm")){
            SerifilmEntity serifilmEntity = serifilmDAO.getByID(id);
            if(serifilmEntity!=null){
                serifilmEntity.setIndex(serifilmEntity.getIndex()+1);
                serifilmDAO.save(serifilmEntity);
            }
        }
        else if(model.equals("album")){
            AlbumEntity albumEntity = albumDAO.getByID(id);
            if(albumEntity!=null){
                albumEntity.setIndex(albumEntity.getIndex()+1);
                albumDAO.save(albumEntity);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
