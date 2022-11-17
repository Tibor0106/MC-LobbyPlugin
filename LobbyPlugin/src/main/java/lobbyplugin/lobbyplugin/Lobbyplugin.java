package lobbyplugin.lobbyplugin;

import lobbyplugin.lobbyplugin.chat_manager.chat_listener;
import lobbyplugin.lobbyplugin.coin_manager.coin;
import lobbyplugin.lobbyplugin.coin_manager.coin_giveaway;
import lobbyplugin.lobbyplugin.database.mysql_database;
import lobbyplugin.lobbyplugin.lobby_items.fun_gun;
import lobbyplugin.lobbyplugin.lobby_items.lobby_commands;
import lobbyplugin.lobbyplugin.lobby_items.lockitems;
import lobbyplugin.lobbyplugin.tab_completer.lobby_tab_completer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import java.sql.*;
import java.util.ArrayList;


public class Lobbyplugin extends JavaPlugin implements Listener {
    Plugin plugin = this;


    public lobbyplugin.lobbyplugin.lobby_items.item_manager item_manager;


    @Override
    public void onEnable() {
        Bukkit.getLogger().info("§eLobby plugin loaded.");
        getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new lockitems(), this);
        Bukkit.getPluginManager().registerEvents(new join_message_manager(), this);
        Bukkit.getPluginManager().registerEvents(new fun_gun(), this);
        Bukkit.getPluginManager().registerEvents(new chat_listener(), this);
        getServer().getPluginCommand("lobbyplugin").setExecutor(new lobby_commands());
        getServer().getPluginCommand("coin").setExecutor(new coin());
        getServer().getPluginCommand("coin_giveaway").setExecutor(new coin_giveaway());
        ArrayList<String> df_c = new ArrayList<String>();
        df_c.add("news, and infos");
        this.getConfig().addDefault("information_item", df_c);
        this.getConfig().addDefault("join_messages", df_c);
        this.getConfig().addDefault("join-message-enable", true);
        getCommand("lobbyplugin").setTabCompleter(new lobby_tab_completer());
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        saveDefaultConfig();




    }
    @Override
    public void onLoad(){
        for (Player p : Bukkit.getOnlinePlayers()){
            item_manager.refresh_items(p, plugin);
        }

    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



    @EventHandler
    public void OnDtah(PlayerDeathEvent e) {
        e.setKeepInventory(true);
        e.getDrops().clear();


    }
    @EventHandler
    public void setDataBase(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        //add lobby items
        p.getInventory().clear();
        lobbyplugin.lobbyplugin.lobby_items.item_manager.refresh_items(p, plugin);
        //database
        final String username = "root";
        final String password = "";
        final String url = "jdbc:mysql://localhost:3306/minecraft";
        Connection connection = null;
        try {

            connection = DriverManager.getConnection(url, username, password);


        } catch (SQLException eq) {
            eq.printStackTrace();

        }
        try {


            String q1 = "SELECT `player_name`,`uuid`,`coin_amount` FROM player_coin WHERE player_name='" + p.getName() + "';";
            PreparedStatement stmt = connection.prepareStatement(q1);
            ResultSet rs = stmt.executeQuery(q1);
            if (rs.next()) {

            } else {
                //no registed
                String sql_1 = "INSERT INTO player_coin (player_name,uuid,coin_amount) VALUES ('" + p.getName() + "', '" + p.getUniqueId() + "', 0);";
                try{
                    PreparedStatement st = connection.prepareStatement(sql_1);
                    st.executeUpdate();
                    p.sendMessage("§c§l[LOBBY] §aDataBase success insert data.");

                } catch (SQLException c){
                    c.printStackTrace();


                }


            }

            } catch (SQLException b){



        }
    }


}


