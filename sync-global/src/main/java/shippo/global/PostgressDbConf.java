package shippo.global;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;

public class PostgressDbConf {
    private static EbeanServer authDb;
    private static String authDbName;
    static {
        loadConfig();
    }

    private static void loadConfig() {
        authDbName = ConfigLoader.getInstance().getProperties().getProperty("auth.db.name");
    }

    public static EbeanServer getAuthDb() {
        return Ebean.getServer(authDbName);
    }
}
