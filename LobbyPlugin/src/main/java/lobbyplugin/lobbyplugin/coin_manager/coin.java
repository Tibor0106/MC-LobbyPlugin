package lobbyplugin.lobbyplugin.coin_manager;









import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import lobbyplugin.lobbyplugin.Lobbyplugin;
import lobbyplugin.lobbyplugin.lobby_items.item_manager;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scheduler.BukkitRunnable;


public class coin implements CommandExecutor {


    Plugin plugin = lobbyplugin.lobbyplugin.Lobbyplugin.getPlugin(Lobbyplugin.class);



    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            Usage(sender);

        } else {
            if (args[0].equalsIgnoreCase("view")) {
                if (args.length == 2) {
                    String p = args[1];
                    Player pa;
                    try{
                        pa = Bukkit.getServer().getPlayer(args[1]);

                        if (sender.hasPermission("coin.view")) {

                            sender.sendMessage("§7(§e§lCoin§r§7) §e» §§f§l"+pa.getName()+"§e §lJátékosnak ennyi coina van: §c"+coin_manager.getCoin(pa));

                        } else {
                            sender.sendMessage("§7( §4§l! §7) §7(§e§lCoin§r§7) §e» §cNincs jogod ehez!");
                        }
                    }catch (Exception e){
                        Usage(sender);
                        sender.sendMessage("Nincs ilyen játékos, vagy nem számot adtál meg.");
                        return true;
                    }
                } else {
                    Usage(sender);
                }

            } else if (args[0].equalsIgnoreCase("set")) {

                if(args.length == 3) {
                    if (sender.hasPermission("coin.admin")) {
                        int c;
                        Player p;
                        String p1 = args[1];
                        try{

                           c = Integer.parseInt(args[2]);
                           p = Bukkit.getPlayer(args[1]);
                           p.sendMessage("Az új coin egyenleged: "+ c);
                        }catch (Exception e){
                            Usage(sender);
                            sender.sendMessage("§7( §4§l! §7) §7(§e§lCoin§r§7) §e» §c§lNincs ilyen online  játékos, vagy nem számot adtál meg!");
                            return true;


                        }
                        coin_manager.setCoin(sender, p, c);

                    } else {
                        Usage(sender);
                    }
                } else {
                    Usage(sender);
                }
            } else if (args[0].equalsIgnoreCase("add")){
                if (sender.hasPermission("coin.admin")) {
                    if (args.length == 3) {
                        int add;
                        Player p;

                        try{

                            add = Integer.parseInt(args[2]);
                            p = Bukkit.getPlayer(args[1]);

                        }catch (Exception e){
                            Usage(sender);
                            sender.sendMessage("§7( §4§l! §7) §7(§e§lCoin§r§7) §e» §c§lNincs ilyen online  játékos, vagy nem számot adtál meg!");
                            return true;

                        }

                        int amout = coin_manager.getCoin(p);
                        int add_ing = amout+add;
                        coin_manager.setCoin(sender, p, add_ing);


                    } else {
                        Usage(sender);

                    }

                }

            } else if (args[0].equalsIgnoreCase("send")){
                if(args.length == 3) {
                    if (sender.hasPermission("coin.send")) {
                        final int[] time = {5};


                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if(time[0] == 0){
                                    sender.sendMessage("§7(§e§lCoin§r§7) §e» §eKüldés....");

                                    Player p = (Player) sender;
                                    ((Player) sender).getPlayer().playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f);
                                    int p_c = coin_manager.getCoin(p);
                                    int get = 0;
                                    Player pa;

                                    try{
                                        get = Integer.parseInt(args[2]);
                                        pa = Bukkit.getPlayer(args[1]);
                                        if(get > p_c){
                                            sender.sendMessage("§7( §4§l! §7) §7(§e§lCoin§r§7) §e» §c§lNincs elég egyenleged, hogy ezt megtedd.");
                                            this.cancel();

                                            return;

                                        }
                                        int send = coin_manager.getCoin(pa);
                                        sender.sendMessage("§7(§e§lCoin§r§7) §e» §e§lSikeresen küldtél §f"+pa.getName()+" §c"+get+" §e§lcoint.");
                                        pa.sendMessage("§7(§e§lCoin§r§7) §e» §e§lSikeresen kaptál §f"+sender.getName()+" §e§lJátékostól  §c"+get+" §e§lcoint.");


                                        coin_manager.setCoin(sender, pa, send+get);
                                        coin_manager.setCoin(sender, p, p_c-get);
                                    } catch (Exception w){
                                        sender.sendMessage("§7( §4§l! §7) §7(§e§lCoin§r§7) §e» §c§lNincs ilyen online  játékos, vagy nem számot adtál meg!");
                                        w.printStackTrace();
                                        this.cancel();
                                        return;

                                    }
                                    this.cancel();
                                    return;
                                }
                                ((Player) sender).getPlayer().playSound(((Player) sender).getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
                                sender.sendMessage("§7(§e§lCoin§r§7) §e» §eKüldés §c"+ time[0]);
                               time[0]--;
                            }
                        }.runTaskTimer(plugin, 20, 20);











                    }
                }

            } else {

                Usage(sender);

            }
        }



        return false;

    }
    public void Usage(CommandSender sender) {
        if (sender.hasPermission("coin.admin")) {
            sender.sendMessage("Commands:");
            sender.sendMessage("     -§c /coin view <player>");
            sender.sendMessage("     -§c /coin add <player>(amount)");
            sender.sendMessage("     -§c /coin set <player> (amount)");



        } else if (sender.hasPermission("coin.view")) {
            sender.sendMessage("Commands:");
            sender.sendMessage("     -§c /coin view <player>");

        } else {
            sender.sendMessage("§7( §4§l! §7) §7(§e§lCoin§r§7) §e» §c§lNincs jogod ehez!");
        }
    }
}






























