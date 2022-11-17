package lobbyplugin.lobbyplugin.coin_manager;

import lobbyplugin.lobbyplugin.Lobbyplugin;
import lobbyplugin.lobbyplugin.database.mysql_database;
import lobbyplugin.lobbyplugin.lobby_items.item_manager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.sql.*;

public class coin_manager {
    public static int coin;


    public static int getCoin(Player p) {
        int coin_amount_get = 0;
        Connection connection = mysql_database.getConnection();
        try {

            String q1 = "SELECT `player_name`,`uuid`,`coin_amount` FROM player_coin WHERE player_name='" + p.getName() + "';";
            PreparedStatement stmt = connection.prepareStatement(q1);
            ResultSet rs = stmt.executeQuery(q1);
            if (rs.next()) {
                String name = rs.getString("player_name");
                int coin_amount = rs.getInt("coin_amount");
                System.out.println("coin: " + rs.getInt("coin_amount"));
                coin_amount_get = coin_amount;
            }
        } catch (SQLException s) {
        }
        return coin = coin_amount_get;
    }

    public static void setCoin(CommandSender sender, Player p, int c) {
        Plugin plugin = Lobbyplugin.getPlugin(Lobbyplugin.class);
        Connection connection = mysql_database.getConnection();
        String sql_1 = "UPDATE player_coin SET coin_amount= "+c+" WHERE player_name = '"+p.getName()+"'";
        item_manager.refresh_items(p, plugin);
        PreparedStatement stmt;

        try {

            stmt = connection.prepareStatement(sql_1);
            stmt.executeUpdate();

        } catch (SQLException e1) {


            e1.printStackTrace();
        }

        sender.sendMessage("§7(§e§lCoin§r§7) §e» §f§l"+p.getName()+" §eJátékos új egyenlege §c"+c+".");
        item_manager.refresh_items(p, plugin);


    }
}






