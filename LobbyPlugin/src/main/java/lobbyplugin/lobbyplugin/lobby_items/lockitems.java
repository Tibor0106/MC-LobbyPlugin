package lobbyplugin.lobbyplugin.lobby_items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class lockitems implements Listener {
    public item_manager item_manager;
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Item item = e.getItemDrop();
        Player p = e.getPlayer();
        e.setCancelled(true);


    }

    @EventHandler
    public void LockInv(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        for (String i : lobbyplugin.lobbyplugin.lobby_items.item_manager.al){
            if(i.equalsIgnoreCase(p.getName())){
                return;
            }
        }
        if (!p.getWorld().getName().equalsIgnoreCase("world")) {
            return;
        }
        e.setCancelled(true);


    }

    @EventHandler
    public void ClickManager(PlayerInteractEvent e) {
        boolean a = true;
        Player p = e.getPlayer();
        if (!p.getWorld().getName().equalsIgnoreCase("world")) {
            a=false;

        }

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {

            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(net.md_5.bungee.api.ChatColor.of("#cf1553") + "§l« Szerver választó »")) {
                if (!a){
                    p.sendMessage("§cEz csak a 'world' világban használaható");
                    return;
                }

                Inventory gui = Bukkit.getServer().createInventory(p, 27, net.md_5.bungee.api.ChatColor.of("#cf1553") + "§lSzerver választó");
                p.openInventory(gui);
                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 1.0f);


            } else {

            }
            if (e.getItem() !=null) {

            }
        }
    }
}




