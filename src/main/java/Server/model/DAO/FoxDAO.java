package Server.model.DAO;

import Server.model.DTO.FoxDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FoxDAO {
    public List<FoxDTO> getAll() {
        List<FoxDTO> list = new ArrayList<FoxDTO>();
        for (int i = 0; i <= 5; i++) {
            FoxDTO f = new FoxDTO("0" + i, "Username0" + i, "abc@123");
            list.add(f);
        }
        return list;
    }

    public FoxDTO createOne(FoxDTO fox) {
        FoxDTO f = null;
        if (fox != null) {
             f = new FoxDTO(
                    fox.getKey() + "_Successful",
                    fox.getUsername() + "_Successful",
                    fox.getPassword() + "_Successful"
            );
        }
        return f;
    }
}
