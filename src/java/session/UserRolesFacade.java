package session;

import entity.Role;
import entity.User;
import entity.UserRoles;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserRolesFacade extends AbstractFacade<UserRoles> {
    
    @EJB private RoleFacade roleFacade;
    
    @PersistenceContext(unitName = "JPTV20BookShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRolesFacade() {
        super(UserRoles.class);
    }
    public String getRoleForUser(User user){
        try {
            List<String> listRoleNamesForUser = 
                  em.createQuery("SELECT ur.role.roleName FROM UserRoles ur WHERE ur.user = :user")
                    .setParameter("user", user)
                    .getResultList();
            if(listRoleNamesForUser.contains("ADMINISTRATOR")){
                return "ADMINISTRATOR";
            }else if(listRoleNamesForUser.contains("MANAGER")){
                return "MANAGER";
            }else if(listRoleNamesForUser.contains("USER")){
                return "USER";
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
