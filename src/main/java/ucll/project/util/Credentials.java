package ucll.project.util;

import java.util.Properties;

/*
Create a class that extends this abstract class and name it Secret
 */
public abstract class Credentials {
    static public void setPass(Properties dbProperties) {
        dbProperties.setProperty("user", "je studentnr");
        dbProperties.setProperty("password", "dagadegijniebepalen!");
    }
}
