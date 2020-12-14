package ucll.project.util;

import java.util.Properties;

public class Secret {
     public static void setPass(Properties properties) {

         properties.setProperty("user", "yourHakkatonUser");
         properties.setProperty("password", "password");

    }
}
