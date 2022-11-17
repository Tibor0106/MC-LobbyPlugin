package lobbyplugin.lobbyplugin.vote_manager;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class vote {
    int col_1 = 0;
    int col_2 = 0;
    static int dur = 0;

    public static void setDur(int dur) {
        dur = dur;
    }

    public int getCol_1() {
        return col_1;
    }

    public int getCol_2() {
        return col_2;
    }

    public static void  setCol_1(int col_1) {
      col_1 = col_1;
    }

    public static void setCol_2(int col_2) {
       col_2 = col_2;
    }

    public static void vote_prepare(CommandSender sender, String i, String f, Plugin plugin){

        Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            @Override
            public void run() {
                if (dur == 0){

                }
                dur--;



            }
        }, 20,20);

    }

}
