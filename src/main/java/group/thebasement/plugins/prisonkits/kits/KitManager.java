package group.thebasement.plugins.prisonkits.kits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import group.thebasement.plugins.prisonkits.PrisonKits;

public class KitManager {

    // Default kit: 1 of each iron tool, 1 of each iron armor, a golden apple, and a
    // stack of steak
    private Map<String, Kit> kits = new HashMap<String, Kit>();
    private final Map<String, List<Player>> cooldowns = new HashMap<String, List<Player>>();
    public Inventory kitInv;

    private final PrisonKits plugin;

    public KitManager(PrisonKits plugin) {
        this.plugin = plugin;

        kits.put("default", kitdefault());
        kits.put("globalrank", globalRank());
        kits.put("templar", templar());
        kits.put("inferno", inferno());
        kits.put("crusader", crusader());
        kits.put("marquis", marquis());
        kits.put("pendragon", pendragon());
        kits.put("goodwill", goodwill());
        kits.put("holy grail", holygrail());
        kits.put("overlord monthly", overlordmonthly());

        // Empty list of players for each kit
        System.out.println(cooldowns);
        List<Player> emptyList = new ArrayList<Player>();
        for (String kit : kits.keySet()) {
            cooldowns.put(kit, emptyList);
        }
        System.out.println(cooldowns);

        // Generate a 9x5 inventory for the kit selector
        kitInv = plugin.getServer().createInventory(null, 9 * 5, "Kit Selector");
        // Populate the inventory with nameless gray stained glass panes
        ItemStack pane = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = pane.getItemMeta();
        meta.setDisplayName(" ");
        pane.setItemMeta(meta);
        for (int i = 0; i < kitInv.getSize(); i++) {
            kitInv.setItem(i, pane);
        }
        // Populate the inventory with the kits
        int i = 10;
        for (String kitName : kits.keySet()) {
            kitInv.setItem(i, kits.get(kitName).icon);
            i = i + 2;
            if (i % 9 == 0)
                i++;
        }
    }

    private ItemStack makeIcon(String name, Material material, boolean enchanted) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (enchanted) {
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        // append reset and white codes
        meta.setDisplayName("§r§f" + name);
        item.setItemMeta(meta);
        return item;
    }

    public int getCooldown(Player player, String kit) {
        Kit playerKit = kits.get(kit);
        return (int) (playerKit.seconds + playerKit.cooldowns.get(player)) / 20;
    }

    public boolean giveKit(Player player, String kit) {
        // The kit name has color codes, so we need to strip them
        List<Player> players = cooldowns.get(kit);
        System.out.println(cooldowns);
        if (players.contains(player)) {
            return false;
        }
        players.add(player);
        BukkitTask cooldown = kits.get(kit).giveKit(player);
        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            players.remove(player);
            cooldown.cancel();
            // Green message with light blue kit name
            player.sendMessage("§aYour §b" + kit + "§a kit's cooldown has ended!");
        }, kits.get(kit).seconds * 20);
        return true;
    }

    private final Kit kitdefault() {
        ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
        ironSword.setAmount(1);
        ItemStack ironPickaxe = new ItemStack(Material.IRON_PICKAXE);
        ironPickaxe.setAmount(1);
        ItemStack ironAxe = new ItemStack(Material.IRON_AXE);
        ironAxe.setAmount(1);
        ItemStack ironShovel = new ItemStack(Material.IRON_SHOVEL);
        ironShovel.setAmount(1);
        ItemStack ironHoe = new ItemStack(Material.IRON_HOE);
        ironHoe.setAmount(1);
        ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);
        ironHelmet.setAmount(1);
        ItemStack ironChestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ironChestplate.setAmount(1);
        ItemStack ironLeggings = new ItemStack(Material.IRON_LEGGINGS);
        ironLeggings.setAmount(1);
        ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);
        ironBoots.setAmount(1);
        ItemStack goldenApple = new ItemStack(Material.GOLDEN_APPLE);
        goldenApple.setAmount(1);
        ItemStack steak = new ItemStack(Material.COOKED_BEEF);
        steak.setAmount(64);
        ItemStack[] items = { ironSword, ironPickaxe, ironAxe, ironShovel, ironHoe, ironHelmet, ironChestplate,
                ironLeggings, ironBoots, goldenApple, steak };
        return new Kit(
                "default",
                items,
                // Cooldown for default kit is 24 hours
                24,
                makeIcon("Default", Material.STONE_SWORD, false),
                this.plugin);
    }

    private final Kit globalRank() {
        ItemStack slab = new ItemStack(Material.STONE_SLAB);
        slab.setAmount(1);
        ItemStack[] items = { slab };
        return new Kit(
                "globalrank",
                items,
                86400,
                makeIcon("Global Rank", Material.BEACON, false),
                this.plugin);
    }

    // templar kit: copy globalRank
    private final Kit templar() {
        ItemStack slab = new ItemStack(Material.STONE_SLAB);
        slab.setAmount(1);
        ItemStack[] items = { slab };
        return new Kit(
                "templar",
                items,
                86400,
                makeIcon("Templar", Material.IRON_SWORD, false),
                this.plugin);
    }

    // inferno kit: copy globalRank
    private final Kit inferno() {
        ItemStack slab = new ItemStack(Material.STONE_SLAB);
        slab.setAmount(1);
        ItemStack[] items = { slab };
        return new Kit(
                "inferno",
                items,
                86400,
                makeIcon("Inferno", Material.FIRE_CHARGE, false),
                this.plugin);
    }

    // Crusader kit: copy globalRank
    private final Kit crusader() {
        ItemStack slab = new ItemStack(Material.STONE_SLAB);
        slab.setAmount(1);
        ItemStack[] items = { slab };
        return new Kit(
                "crusader",
                items,
                86400,
                makeIcon("Crusader", Material.CHAINMAIL_CHESTPLATE, false),
                this.plugin);
    }

    // Marquis kit: copy globalRank
    private final Kit marquis() {
        ItemStack slab = new ItemStack(Material.STONE_SLAB);
        slab.setAmount(1);
        ItemStack[] items = { slab };
        return new Kit(
                "marquis",
                items,
                86400,
                makeIcon("Marquis", Material.SHIELD, false),
                this.plugin);
    }

    // Pendragon kit: copy globalRank
    private final Kit pendragon() {
        ItemStack slab = new ItemStack(Material.STONE_SLAB);
        slab.setAmount(1);
        ItemStack[] items = { slab };
        return new Kit(
                "pendragon",
                items,
                86400,
                makeIcon("Pendragon", Material.DRAGON_HEAD, false),
                this.plugin);
    }

    // Goodwill kit: copy globalRank
    private final Kit goodwill() {
        ItemStack slab = new ItemStack(Material.STONE_SLAB);
        slab.setAmount(1);
        ItemStack[] items = { slab };
        return new Kit(
                "goodwill",
                items,
                86400,
                makeIcon("Goodwill", Material.MUSHROOM_STEW, false),
                this.plugin);
    }

    // Holy Grail kit: copy globalRank
    private final Kit holygrail() {
        ItemStack slab = new ItemStack(Material.STONE_SLAB);
        slab.setAmount(1);
        ItemStack[] items = { slab };
        return new Kit(
                "holygrail",
                items,
                86400,
                makeIcon("Holy Grail", Material.GOLD_NUGGET, false),
                this.plugin);
    }

    // Overlord Monthly kit: copy globalRank
    private final Kit overlordmonthly() {
        ItemStack slab = new ItemStack(Material.STONE_SLAB);
        slab.setAmount(1);
        ItemStack[] items = { slab };
        return new Kit(
                "overlord",
                items,
                86400,
                makeIcon("Overlord", Material.ENDER_CHEST, false),
                this.plugin);
    }

}
