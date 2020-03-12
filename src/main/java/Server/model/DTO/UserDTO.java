package Server.model.DTO;

import Server.model.DB.*;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private UserEntity userEntity = new UserEntity();
    private RoleEntity roleEntity = new RoleEntity();
    private List<ImageEntity> imageEntity = new ArrayList<>();
    private Criteria criteria = new Criteria();

    public UserDTO(){
    }

    public UserDTO(UserEntity userEntity, RoleEntity roleEntity, List<ImageEntity> imageEntity, Criteria criteria) {
        this.userEntity = userEntity;
        this.roleEntity = roleEntity;
        this.imageEntity = imageEntity;
        this.criteria = criteria;
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

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }
}
