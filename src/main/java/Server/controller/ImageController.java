package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.ImageDAO;
import Server.model.DB.ImageEntity;
import Server.model.DTO.APIAccountDTO;
import Server.model.DTO.ImageInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("Image")
@RestController
public class ImageController {
    @Autowired
    ImageDAO imageDAO;
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "/Post",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody ImageInDTO imageInDTO) {
        if (imageInDTO == null || imageInDTO.getApiToken() == null
                || imageInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(imageInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        ImageEntity entity = imageDAO.save(imageInDTO.getImageEntity());
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody ImageInDTO imageInDTO, @PathVariable("id") Long id) {
        if (imageInDTO == null || imageInDTO.getApiToken() == null
                || imageInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(imageInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (id == imageInDTO.getImageEntity().getId()) {
            ImageEntity image = imageDAO.save(imageInDTO.getImageEntity());
            return new ResponseEntity<>("Update completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id) {
        if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (imageDAO.getByID(id) != null) {
            imageDAO.delete(id);
            return new ResponseEntity<>("Deleted completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Deleted Fail", HttpStatus.BAD_REQUEST);
    }
}
