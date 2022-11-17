package lobbyplugin.lobbyplugin.messages;

import lobbyplugin.lobbyplugin.Lobbyplugin;
import lobbyplugin.lobbyplugin.lobby_items.lobby_commands;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class messages {

    public static void error_message(CommandSender sender){

        sender.sendMessage("§7( §4§l! §7) §7(§e§lCoin§r§7) §e» §c§lNem megfelelő használat! Használd a: §e§l/lobbyplugin help parancsot.");

    }
    public static void correct_usage(CommandSender sender){
    }
    public static void permission_error(CommandSender sender){
        Plugin plugin = lobbyplugin.lobbyplugin.Lobbyplugin.getPlugin(Lobbyplugin.class);
        String prefix = plugin.getConfig().getString("prefix");
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix+"§c Nincs jogod ezt a parancsot használni!"));
    }
    public static void lobby_help(CommandSender sender){
        sender.sendMessage("§e§lLobbyPlugin Help:\n ");
        sender.sendMessage("   §f- §7/lobbyplugin reload §e» §cPlugin újratöltése.");
        sender.sendMessage("   §f- §7/lobbyplugin news-item addline <text> §e» §cSor hozzáadása az információkhoz.");
        sender.sendMessage("   §f- §7/lobbyplugin news-item setline [line] <text> §e» §cSor szerkesztése.");
        sender.sendMessage("   §f- §7/lobbyplugin check-join-message §e» §cCsatlakozáskor kiírt szöveg tesztelése.\n ");


    }
}
