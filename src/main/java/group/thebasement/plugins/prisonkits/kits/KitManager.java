package group.thebasement.plugins.prisonkits.kits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import group.thebasement.plugins.prisonkits.PrisonKits;

public class KitManager {

    // Default kit: 1 of each iron tool, 1 of each iron armor, a golden apple, and a
    // stack of steak
    private Map<String, Kit> kits = new HashMap<String, Kit>();
    private Map<String, List<Player>> cooldowns = new HashMap<String, List<Player>>();

    private final PrisonKits plugin;

    public KitManager(PrisonKits plugin) {
        this.plugin = plugin;

        kits.put("default", kitdefault());
        kits.put("globalrank", globalRank());
        kits.put("templar", templar());
        kits.put("inferno", inferno());
        kits.put("crusader", crusader());
        kits.put("marquis", marquis());

        // Empty list of players for each kit
        cooldowns.put("default", new ArrayList<Player>());
        cooldowns.put("globalrank", new ArrayList<Player>());
        cooldowns.put("templar", new ArrayList<Player>());
        cooldowns.put("inferno", new ArrayList<Player>());
        cooldowns.put("crusader", new ArrayList<Player>());
        cooldowns.put("marquis", new ArrayList<Player>());
    }

    public int getCooldown(Player player, String kit) {
        return kits.get(kit).cooldowns.get(player);
    }

    public boolean giveKit(Player player, String kit) {
        List<Player> players = cooldowns.get(kit);
        if (players.contains(player)) {
            player.sendMessage("§cYou must wait before using this kit again!");
            return false;
        }
        int cooldown = kits.get(kit).giveKit(player);
        players.add(player);
        this.plugin.getServer().getScheduler().runTaskLater(this.plugin, () -> {
            players.remove(player);
            player.sendMessage("§aYou can now use the " + kit + " kit again!");
        }, cooldown * 20);

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
                20,
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
                this.plugin);
    }

}
