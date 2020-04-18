package Server.model.DTO;

import Server.model.DB.ImageEntity;
import Server.model.DB.RoleEntity;
import Server.model.DB.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private UserEntity userEntity = new UserEntity();
    private RoleEntity roleEntity = new RoleEntity();
    private List<ImageEntity> imageEntity = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(UserEntity userEntity, RoleEntity roleEntity, List<ImageEntity> imageEntity) {
        this.userEntity = userEntity;
        this.roleEntity = roleEntity;
        this.imageEntity = imageEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }


    public List<ImageEntity> getImageEntity() {
        return imageEntity;
    }

    public void setImageEntity(List<ImageEntity> imageEntity) {
        this.imageEntity = imageEntity;
    }

}
