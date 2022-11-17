package lobbyplugin.lobbyplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class join_message_manager implements Listener {
    Plugin plugin = lobbyplugin.lobbyplugin.Lobbyplugin.getPlugin(lobbyplugin.lobbyplugin.Lobbyplugin.class);
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        //Max 20 line
        Player p = e.getPlayer();
        int counter = 0;
        List<String> main = new ArrayList<>();
        for (String i : plugin.getConfig().getStringList("join_messages")){
            main.add(i);
            counter++;

        }
        if (counter > 20){
            if (p.hasPermission("lobby.admin"))
            p.sendMessage("§cPlease remove one line, because max line count be like 20.");
            return;
        }
        //check enbaled
        boolean check = false;
        try{
            check = plugin.getConfig().getBoolean("join-message-enable");
        }catch (Exception eg){
            p.sendMessage("§cPlease check the config.yml file, because detect error.");

        }
        if (!check){
            return;
        } else {
            for (String i : main){
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',i));
        }

        }
    }
}
