package group.thebasement.plugins.prisonkits;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import group.thebasement.plugins.prisonkits.commands.CommandManager;
import group.thebasement.plugins.prisonkits.kits.KitManager;
import group.thebasement.plugins.prisonkits.listeners.InventoryHandler;

//import group.thebasement.plugins.prisonkits.items.ItemManager;

public class PrisonKits extends JavaPlugin {

    public final KitManager kitManager;
    public Map<String, Map<Player, Integer>> cooldowns;

    public Inventory testInv;

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
        getCommand("test").setExecutor(manager);

        // Register events
        new InventoryHandler(this);

        testInv = this.getServer().createInventory(null, 9, "Test");
        testInv.setItem(4, new ItemStack(Material.BEDROCK, -1));

    }
}
