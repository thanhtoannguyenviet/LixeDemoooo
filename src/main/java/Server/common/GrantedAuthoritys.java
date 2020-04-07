package Server.common;

import Server.model.DAO.RoleDAO;
import Server.model.DB.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class GrantedAuthoritys {
    @Autowired
    RoleDAO roleDAO;
    private String[] setRoles(){
        ArrayList<String> roleArray = new ArrayList<String>();
        for(RoleEntity lsrole : roleDAO.getAll()){
            roleArray.add(lsrole.getRolename());
        }
        return (String[])roleArray.toArray();
    }

    private String[] roles =  setRoles();

    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;

    }
    public List<GrantedAuthority>  loadAuthority( String rolename){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String role : roles) {
            if(rolename.equals(roles))
                authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
