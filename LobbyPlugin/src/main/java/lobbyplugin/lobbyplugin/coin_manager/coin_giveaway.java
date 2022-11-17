package lobbyplugin.lobbyplugin.coin_manager;

import lobbyplugin.lobbyplugin.Lobbyplugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.sql.*;
import java.util.Random;

public class coin_giveaway implements CommandExecutor {
    int duration;
    int coin_amount;
    private BukkitRunnable timer_1;
    private BossBar bbs;
    int amount_all;
    Plugin plugin = Lobbyplugin.getPlugin(Lobbyplugin.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("lobby.giveaway")){
            if(args[0].equalsIgnoreCase("start")){
                if (args.length !=3){
                    err(sender);
                    return true;
                }


                try{
                    duration = Integer.parseInt(args[1]);
                    coin_amount = Integer.parseInt(args[2]);
                    start_ga(sender);


                }catch (Exception e){
                    err(sender);
                    e.printStackTrace();
                    return true ;
                }



            } else if(args[0].equalsIgnoreCase("edittime")){

                try{
                    duration = Integer.parseInt(args[1]);

                }catch(Exception e){
                    err(sender);
                }


            }

        } else {
            sender.sendMessage("§cYou have't parmission to use this command!");
        }
        return false;
    }
    public void start_ga(CommandSender sender) {
        BossBar bbs = Bukkit.createBossBar("§e§ka §c§lPUSZTÍTÓ §e§ka §a§lHamarosan megérkezik: §e-" + ChatColor.RED, BarColor.GREEN, BarStyle.SOLID);
        this.timer_1 = (BukkitRunnable) new BukkitRunnable() {

            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()){
                    bbs.addPlayer(p);
                    bbs.setTitle("§c§lHey! §aCOIN giveaway, Amount:§e "+coin_amount+" §aCounter:§c "+duration);


                }
                if (duration == 0){


                    int online = Bukkit.getOnlinePlayers().size();
                    Random random = new Random();
                    int rand = random.nextInt(online+1);
                    this.cancel();
                    int b = 0;
                    for (Player p : Bukkit.getOnlinePlayers()){
                        b++;
                        if (b == rand || rand == 0){
                            p.sendMessage("§l§8|=============| §5§lYOU WON! |§l§8=============|");
                            p.sendMessage(" ");
                            p.sendMessage("   §7- §b§l+"+coin_amount+" §aCoin");
                            p.sendMessage(" ");
                            p.sendMessage("§l§8========================================");
                            bbs.setTitle("§e§ko §a§l"+p.getName()+" Won! §e§ko§6§l Congratulation!");
                            addcoin_to_winner(p);
                            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                                @Override
                                public void run() {
                                    bbs.hide();
                                }
                            }, 200);
                        }

                    }

                }

               duration--;

            }


        }.runTaskTimer(plugin, 20, 20);

    }
        public void err(CommandSender sender){
        sender.sendMessage("§cUsage: /coin_giveaway start (duration) [coin amount] <--boss bar text-->");

    }
    public void addcoin_to_winner(Player p){

        try {

            //Data base

            final String username="root";
            final String password="";
            final String url = "jdbc:mysql://localhost:3306/minecraft";
            Connection connection = null;
            try {

                connection = DriverManager.getConnection(url, username, password);


            } catch (SQLException eq) {
                eq.printStackTrace();

            }
            try {



                String q1 = "SELECT `player_name`,`uuid`,`coin_amount` FROM player_coin WHERE player_name='"+p.getName()+"';";
                PreparedStatement stmt = connection.prepareStatement(q1);
                ResultSet rs = stmt.executeQuery(q1);
                if (rs.next()) {

                    String name =  rs.getString("player_name");
                    int coin_am = rs.getInt("coin_amount");
                    System.out.println("coin: " + rs.getInt("coin_amount"));
                    amount_all = coin_am+coin_amount;



                } else {

                }



            } catch(Exception e) {

                System.out.println(e);
            }






            String sql_1 = "UPDATE player_coin SET coin_amount= "+amount_all+" WHERE player_name = '"+p.getName()+"'";
            PreparedStatement stmt;


            try {

                stmt = connection.prepareStatement(sql_1);
                stmt.executeUpdate();



            } catch (SQLException e1) {
                e1.printStackTrace();

            }




        }catch(Throwable e){


        }


    }
}


