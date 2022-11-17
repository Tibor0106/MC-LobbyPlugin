package lobbyplugin.lobbyplugin.vote_manager;

import lobbyplugin.lobbyplugin.messages.messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class vote_command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("lobby.vote.start")){
            int duartion;
            try{
                duartion = Integer.parseInt(args[4]);
            }catch (NumberFormatException hiba){
                sender.sendMessage("§c§lSzámot adj meg.");
                return  true;


            }



        } else if (sender.hasPermission("lobby.vote")){

        } else{
            messages.permission_error(sender);
        }

        return false;
    }
}
