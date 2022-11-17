package lobbyplugin.lobbyplugin.lobby_items;

import lobbyplugin.lobbyplugin.Lobbyplugin;
import lobbyplugin.lobbyplugin.chat_manager.chat_mute;
import lobbyplugin.lobbyplugin.messages.messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class lobby_commands implements CommandExecutor {
    public lobbyplugin.lobbyplugin.messages.messages messages;
    Plugin plugin = Lobbyplugin.getPlugin(Lobbyplugin.class);
    String prefix = plugin.getConfig().getString("prefix");
    public item_manager item_manager;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            if (sender.hasPermission("lobby.usecommand")) {
                error(sender);

            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " §4Hey! You have a no parmission to use this commad!"));
            }

            return true;

        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("lobby.reload")) {
                reloadplugin(plugin, sender);
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + " §4Hey! You have a no parmission to use this commad!"));
            }
        } else if (args[0].equalsIgnoreCase("news-item")) {
            if (!sender.hasPermission("lobby.edit_news_item")) {
                lobbyplugin.lobbyplugin.messages.messages.permission_error(sender);
                return true ;

            }
            if (args.length < 2){
                error(sender);
                return true;
            }
            if (args[1].equalsIgnoreCase("addline")) {
                if (args.length < 2){
                    error(sender);
                    return true;
                }
                if (sender.hasPermission("lobby.edit_news_item")) {
                    ArrayList<String> addline = new ArrayList<>();
                    for (String i : plugin.getConfig().getStringList("information_item")) {
                        addline.add(ChatColor.translateAlternateColorCodes('&', i));

                    }
                    StringBuilder str = new StringBuilder();
                    for (int i = 2; i < args.length; i++) {
                        str.append(args[i] + " ");
                    }
                    addline.add(str.toString());
                    plugin.getConfig().set("information_item", addline);
                    plugin.saveConfig();
                    for (Player i : Bukkit.getOnlinePlayers()) {
                        lobbyplugin.lobbyplugin.lobby_items.item_manager.refresh_items(i, plugin);
                    }
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fSor hozzáadva: \n &7- " + str.toString()));


                } else {
                    lobbyplugin.lobbyplugin.messages.messages.permission_error(sender);
                }


            } else if (args[1].equalsIgnoreCase("editline")) {
                if (args.length < 3){
                    error(sender);
                    return true;
                }
                int lines = 0;
                int counter = 0;


                for (String i : plugin.getConfig().getStringList("information_item")) {

                    lines++;


                }
                String[] tomb = new String[20];
                for (String i : plugin.getConfig().getStringList("information_item")) {

                    tomb[counter] = i;
                    counter++;
                }

                try {
                    int line = Integer.parseInt(args[2]);
                    if (line > lines) {
                        sender.sendMessage("§c§clNincs ilyen sor.");
                        return true;

                    }
                    ArrayList<String> vegleges = new ArrayList<>();
                    StringBuilder str1 = new StringBuilder();
                    for (int i = 3; i < args.length; i++) {
                        str1.append(args[i] + " ");
                    }
                    tomb[line] = str1.toString();


                    for (int i = 0; i < lines; i++) {
                        vegleges.add(tomb[i]);
                    }
                    plugin.getConfig().set("information_item", vegleges);
                    plugin.saveConfig();


                    for (Player i : Bukkit.getOnlinePlayers()) {
                        lobbyplugin.lobbyplugin.lobby_items.item_manager.refresh_items(i, plugin);
                    }
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "§7Line: " + line + " bellítva erre: '" + str1.toString() + "'§e."));


                } catch (Exception e) {
                    lobbyplugin.lobbyplugin.messages.messages.error_message(sender);
                    sender.sendMessage("Számot adj meg.");
                }



            } else {
                error(sender);
            }


        } else if (args[0].equalsIgnoreCase("check-join-message")) {
            if (!sender.hasPermission("lobby.check_join_message")){
                lobbyplugin.lobbyplugin.messages.messages.permission_error(sender);
                return true;
            }

            for (String i : plugin.getConfig().getStringList("join_messages")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',i));

            }

        } else if (args[0].equalsIgnoreCase("help"))  {
            if(!sender.hasPermission("lobby.help")){
                lobbyplugin.lobbyplugin.messages.messages.permission_error(sender);
                return true;
            }
            lobbyplugin.lobbyplugin.messages.messages.lobby_help(sender);

        } else if (args[0].equalsIgnoreCase("item_lock")){
            if(!sender.hasPermission("lobby.disable_locking")){
                lobbyplugin.lobbyplugin.messages.messages.permission_error(sender);
                return true;
            }
            lobbyplugin.lobbyplugin.lobby_items.item_manager.set_item_clock_bypass(sender);
        } else if (args[0].equalsIgnoreCase("mute-chat")) {
            if(!sender.hasPermission("lobby.mute-chat")){
                lobbyplugin.lobbyplugin.messages.messages.permission_error(sender);
                return true;

            }
            if(chat_mute.mute){
                chat_mute.setMute(false);
                sender.sendMessage("§e§lChat is §a§lEnabled§f.");

            } else {

                sender.sendMessage("§e§lChat is §c§lDisable§f.");
                chat_mute.setMute(true);

            }


        } else {

            error(sender);

        }



        return false;
    }
    public void reloadplugin(Plugin plugin, CommandSender sender){
        plugin.reloadConfig();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix+" §7Plugin §creloaded§e."));
        for (Player p : Bukkit.getOnlinePlayers()){
            lobbyplugin.lobbyplugin.lobby_items.item_manager.refresh_items(p, plugin);
        }

    }

    public void error(CommandSender sender){
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix+" §cUsage: /lobbyplugin help"));

    }

}
