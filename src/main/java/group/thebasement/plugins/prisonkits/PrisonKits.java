package group.thebasement.plugins.prisonkits;

import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import group.thebasement.plugins.prisonkits.commands.CommandManager;
import group.thebasement.plugins.prisonkits.kits.KitManager;

//import group.thebasement.plugins.prisonkits.items.ItemManager;

public class PrisonKits extends JavaPlugin {

    public final KitManager kitManager;
    public Map<String, Map<Player, Integer>> cooldowns;

    public PrisonKits() {
        kitManager = new KitManager(this);
    }

    @Override
    public void onEnable() {
        getLogger().info("PrisonKits has been enabled!");

        // Register commands
        CommandManager manager = new CommandManager(this);
        getCommand("kit").setExecutor(manager);
        getCommand("disablepk").setExecutor(manager);

    }
}
