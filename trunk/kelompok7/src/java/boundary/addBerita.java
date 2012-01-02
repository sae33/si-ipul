package boundary;

import entity.DaftarInfoBerita;
import entity.DaftarUser;
import entity.infoBerita;
import entity.members;
import entity.operator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

public class addBerita extends Boundary {



    public addBerita(){
        super();
        setTemplate("/WEB-INF/addBerita.jsp");
    }

    @Override
    protected void process() {

        setMessage("");

        if (getRequest().getParameter("act") != null && getRequest().getParameter("act").equals("add")) {
            try {
                if (validate_field()) {
                        DaftarInfoBerita dib = new DaftarInfoBerita();
                        String pics = getRequest().getParameter("pic");
                        int pic = Integer.parseInt(pics);
                        HttpSession session = getRequest().getSession();
                        String op = (String) session.getAttribute("operator");
                        infoBerita m = dib.getIBOp("operator"); 
                        if (m == null) {
                            m = new infoBerita();
                            m.setOperator(op);
                            m.setInfoBerita(getRequest().getParameter("infoBerita"));
                            m.setPicture(pic);
                            m.setTitle(getRequest().getParameter("title"));                            
                            dib.tambahInfoBerita(m);
                            }
                         
                } else {
                    getResponse().sendRedirect("fieldKosong");
                }
                getResponse().sendRedirect("homeMember");
            } catch (IOException ex) {
                Logger.getLogger(addBerita.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }

    boolean validate_field() {
        
        String username = getRequest().getParameter("username");
        String password = getRequest().getParameter("password");
        String address = getRequest().getParameter("address");
        String handphone = getRequest().getParameter("handphone");
        String email = getRequest().getParameter("email");
        if(username == null||password == null||address == null||handphone == null||email == null){
            return false;
        /*}
        if(username.trim().equals("")||password.trim().equals("")||address.trim().equals("")||handphone.trim().equals("")||email.trim().equals("")){
            return false;*/
        }

        return true;
    }

   
}