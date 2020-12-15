package ucll.project.ui.controller;

import ucll.project.domain.model.Rol;

import javax.servlet.http.HttpServletRequest;

public class Utility {
    public static void checkRoles(HttpServletRequest request, Rol[] roles) {
        Rol actualRole = null;
        String rol = (String) request.getSession().getAttribute("rol");
        for (Rol role : roles) {
            if (rol.equals(role.getStringValue())) {
                actualRole = role;
                break;
            }
        }
        if (actualRole == null) throw new NotAuthorizedException();
    }
}
