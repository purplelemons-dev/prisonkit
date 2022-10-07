package group.thebasement.plugins.prisonkits.kits;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import group.thebasement.plugins.prisonkits.PrisonKits;

public class Kit {

    public final String name;
    private final ItemStack[] items;
    public final int seconds;
    public final Map<Player, Integer> cooldowns = new HashMap<Player, Integer>();
    public final ItemStack icon;
    private final PrisonKits plugin;

    public Kit(String name, ItemStack[] items, int seconds, ItemStack icon, PrisonKits plugin) {
        this.name = "default";
        this.items = items;
        this.seconds = seconds;
        this.icon = icon;
        this.plugin = plugin;
    }

    public BukkitTask giveKit(Player player) {
        player.getInventory().addItem(items);
        // Set a reoccuring task that decrements the cooldown each tick
        cooldowns.put(player, seconds * 20);
        return plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
            cooldowns.put(player, cooldowns.get(player) - 1);
        }, 0, 1);
    }

}
