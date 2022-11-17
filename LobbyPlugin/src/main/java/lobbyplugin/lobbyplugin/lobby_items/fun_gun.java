package lobbyplugin.lobbyplugin.lobby_items;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class fun_gun implements Listener {
    @EventHandler
    public void fun_gun_click(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK ){

            if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8[§6§l⚒§r§8]"+net.md_5.bungee.api.ChatColor.of("#ff6f00") + " Fun ágyú §8[§6§l⚒§r§8]")){
                if (p.hasPermission("lobby.fun_gun.use")){
                    if(p.getCooldown(Material.BLAZE_ROD) != 0) {
                        return;
                    }
                    Snowball ball = p.getWorld().spawn(p.getEyeLocation(), Snowball.class);
                    ball.setShooter(p);
                    ball.setItem(new ItemStack(Material.FEATHER));
                    ball.setVelocity(p.getLocation().getDirection().multiply(1.5));
                    p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1.0f, 1.0f);
                    ball.setPassenger(p);
                    p.setCooldown(Material.BLAZE_ROD,60);

                }
            }
        } else  if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK ){
            if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8[§6§l⚒§r§8]"+net.md_5.bungee.api.ChatColor.of("#ff6f00") + " Fun ágyú §8[§6§l⚒§r§8]")) {
                if (p.hasPermission("lobby.fun_gun.use")) {
                    if(p.getCooldown(Material.BLAZE_ROD) != 0){
                        return;
                    }
                    Snowball ball = p.getWorld().spawn(p.getEyeLocation(), Snowball.class);
                    ball.setShooter(p);
                    ball.setCustomNameVisible(true);
                    ball.setCustomName(" §c❤ §b§l" + p.getName() + "'s §f§lSHOT §c❤");
                    ball.setVelocity(p.getLocation().getDirection().multiply(1.5));
                    p.playSound(p.getLocation(), Sound.ENTITY_SNOWBALL_THROW, 1.0f, 1.0f);
                    p.setCooldown(Material.BLAZE_ROD,60);
                }
            } else{

            }
        } else {

        }
    }
}
