package lobbyplugin.lobbyplugin.lobby_items;

import lobbyplugin.lobbyplugin.coin_manager.coin_manager;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.StonecutterInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class item_manager {

    public static void refresh_items(Player p, Plugin plugin) {
        //set gamemode
        p.setGameMode(GameMode.ADVENTURE);
        //create lobby items and give
        //Szerver valaszto
        ItemStack sz_p = new ItemStack(Material.COMPASS);
        ItemMeta meta = sz_p.getItemMeta();
        meta.setDisplayName(net.md_5.bungee.api.ChatColor.of("#cf1553") + "§l« Szerver választó »");
        sz_p.setItemMeta(meta);
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§a§l§m[---------------------|----------------]");
        lore.add("§e§l       Válazd ki a kedvenc szerveredet.");
        lore.add("§e§l                     és");
        lore.add("§e§l                   JÁTSZ");
        lore.add("§a§l§m[---------------------|----------------]");
        meta.setLore(lore);

        sz_p.setItemMeta(meta);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        sz_p.setItemMeta(meta);
        sz_p.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        p.getInventory().setItem(4, sz_p);
        //item2
        ItemStack scute = new ItemStack(Material.SCUTE);
        ItemMeta meta1 = scute.getItemMeta();
        meta1.setDisplayName("§8[§2§l⭐§r§8]"+net.md_5.bungee.api.ChatColor.of("#075eeb") + " §lInformációk §8[§2§l⭐§r§8]");
        scute.setItemMeta(meta1);
        ArrayList<String> lore1 = new ArrayList<>();
        for (String i : plugin.getConfig().getStringList("information_item")) {
            lore1.add(ChatColor.translateAlternateColorCodes('&', i));

        }
        meta1.setLore(lore1);
        scute.setItemMeta(meta1);
        meta1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        scute.setItemMeta(meta1);
        scute.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        p.getInventory().setItem(6, scute);
        //Fun gun
        ItemStack f_g = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta2 = f_g.getItemMeta();
        meta2.setDisplayName("§8[§6§l⚒§r§8]"+net.md_5.bungee.api.ChatColor.of("#ff6f00") + " Fun ágyú §8[§6§l⚒§r§8]");
        f_g.setItemMeta(meta2);
        ArrayList<String> lore2 = new ArrayList<>();
        lore2.add("");
        lore2.add(" §f- §3§lHasznalható ELIT ranatól.");
        lore2.add(" §f- §3§lA tárgy 5 másodpercenként használható.");
        lore2.add("");
        lore2.add("§8(§7Jobb kattintás§8) §e--> §c§lRepülés.");
        lore2.add("§8(§7bal kattintás§8) §e--> §c§lHógolyó lövés.");
        meta2.setLore(lore2);
        f_g.setItemMeta(meta2);
        meta2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        f_g.setItemMeta(meta2);
        f_g.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        p.getInventory().setItem(2, f_g);
        //player head informations
        ItemStack skullItem = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();
        skullMeta.setOwner("" + p.getName());
        skullMeta.setDisplayName("§c§l" + p.getName());
        skullItem.setItemMeta(skullMeta);
        ArrayList<String> lore3 = new ArrayList<>();
        int coin = coin_manager.getCoin(p);
        lore3.add("§f- §ePlayer coin: §b"+coin);
        lore3.add("§f- §eServer: §blobby");
        lore3.add("§f- §ePing: §b"+p.getPing());

        skullMeta.setLore(lore3);
        skullItem.setItemMeta(skullMeta);
        p.getInventory().setItem(0, skullItem);

    }

    static List <String> al = new ArrayList<>();




    public static void set_item_clock_bypass(CommandSender sender) {

        for (String i : al){
            if (i.equalsIgnoreCase(sender.getName())){
                sender.sendMessage("§e§lLobby items lock: §a§lEnabled.");
                al.remove(sender.getName());
                Player p = (Player) sender;
                return;
            }


        }
        sender.sendMessage("§e§lLobby items lock: §c§lDisabled.");
        al.add(sender.getName());


    }
}






