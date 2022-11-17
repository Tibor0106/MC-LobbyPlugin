package lobbyplugin.lobbyplugin.chat_manager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class chat_listener implements Listener {
    @EventHandler
    public void chat_mute_check(AsyncPlayerChatEvent e){
        if (chat_mute.mute){
            if(e.getPlayer().hasPermission("lobby.mute.bypass")){
                e.getPlayer().sendMessage("§eChat is muted, but you bypassing.");
                return;
            }
            e.getPlayer().sendMessage("§c§lChat is muted. Try agin later.");
            e.setCancelled(true);
        }

    }
}
