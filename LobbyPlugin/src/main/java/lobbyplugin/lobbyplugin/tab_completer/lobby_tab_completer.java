package lobbyplugin.lobbyplugin.tab_completer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import javax.swing.text.TabExpander;
import java.util.ArrayList;
import java.util.List;

public class lobby_tab_completer implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 2) {
            List<String> cmds = new ArrayList<>();
            List<String> comp = new ArrayList<>();
            if (args[0].equalsIgnoreCase("news-item")) {
                comp.add("addline");
                comp.add("editline");

            }


            for (String s : comp) {
                if (s.toLowerCase().startsWith(args[1].toLowerCase())) {
                    cmds.add(s);


                }


            }
            return cmds;

        } else if (args.length == 1) {
            List<String> cmds1 = new ArrayList<>();
            List<String> comp1 = new ArrayList<>();
            comp1.add("reload");
            comp1.add("news-item");
            comp1.add("check-join-message");
            comp1.add("help");
            comp1.add("item_lock");




            for (String s : comp1) {
                if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
                    cmds1.add(s);


                }


            }
            return cmds1;
        }


        return null;
    }

}



