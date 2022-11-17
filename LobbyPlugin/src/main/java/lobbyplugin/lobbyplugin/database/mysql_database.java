package lobbyplugin.lobbyplugin.database;

import lobbyplugin.lobbyplugin.Lobbyplugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mysql_database {

    private Connection connection;


    public static Connection getConnection(){
        Plugin plugin = Lobbyplugin.getPlugin(Lobbyplugin.class);
        final String username = plugin.getConfig().getString("db_username");
        final String password= plugin.getConfig().getString("db_password");
        final String table = plugin.getConfig().getString("db_table");
        final String address = plugin.getConfig().getString("db_address");
        final Integer port = plugin.getConfig().getInt("db_port");


        final String url = "jdbc:mysql://"+address+":"+port+"/"+table+"";
        Connection connection = null;
        try {

            connection = DriverManager.getConnection(url, username, password);


        } catch (SQLException eq) {
            eq.printStackTrace();
            Bukkit.getLogger().info("§cSQL ERROR !");

        }

        return connection;
    }
}
