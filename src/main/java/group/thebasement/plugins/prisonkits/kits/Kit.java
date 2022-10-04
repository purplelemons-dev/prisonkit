package group.thebasement.plugins.prisonkits.kits;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import group.thebasement.plugins.prisonkits.PrisonKits;

public class Kit {

    public final String name;
    private final ItemStack[] items;
    private final int seconds;
    public Map<Player, Integer> cooldowns = new HashMap<Player, Integer>();
    private final PrisonKits plugin;

    public Kit(String name, ItemStack[] items, int seconds, PrisonKits plugin) {
        this.name = "default";
        this.items = items;
        this.seconds = seconds;
        this.plugin = plugin;
    }

    public int giveKit(Player player) {
        player.getInventory().addItem(items);
        // Set a reoccuring task that decrements the cooldown each tick
        cooldowns.put(player, seconds);
        plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
            cooldowns.put(player, cooldowns.get(player) - 1);
        }, 0, 1);
        return seconds;
    }

}