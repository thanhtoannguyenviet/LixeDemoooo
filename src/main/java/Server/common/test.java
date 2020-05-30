package Server.common;

import Server.model.DAO.APIAccountDAO;

public class test {
    public static void main(String[] args) {
        APIAccountDAO apiAccountDAO = new APIAccountDAO();
        System.out.println(apiAccountDAO.checkToken("ASFASASDFASD"));
    }
}
