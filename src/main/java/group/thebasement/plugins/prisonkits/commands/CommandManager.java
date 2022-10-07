package group.thebasement.plugins.prisonkits.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import group.thebasement.plugins.prisonkits.PrisonKits;

public class CommandManager implements CommandExecutor {

    public final PrisonKits plugin;

    public CommandManager(PrisonKits plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String name = cmd.getName().toLowerCase();
        if (name.equals("disablepk")) {
            if (args.length == 0) {
                // Ensure the sender is an operator
                if (sender.isOp()) {
                    sender.getServer().broadcastMessage("§cPrisonKits has been disabled!");
                    // Disable the plugin
                    sender.getServer().getPluginManager().disablePlugin(this.plugin);
                    return true;
                }
            }
        } else if (name.equals("kit") && sender instanceof Player player) {
            /*
             * String kit;
             * if (args.length == 0)
             * kit = "default";
             * else
             * kit = args[0];
             * if (plugin.kitManager.giveKit(player, kit)) {
             * player.sendMessage("§aYou have received the §b" + kit + " §akit!");
             * return true;
             * } else {
             * player.sendMessage("§cYou must wait §b" +
             * plugin.kitManager.getCooldown(player, kit)
             * + " §cseconds before using this kit again!");
             * return true;
             * }
             */
            player.openInventory(plugin.kitManager.kitInv);
            return true;
        } else if (name.equals("test") && sender instanceof Player player) {
            player.openInventory(plugin.testInv);
            return true;
        }
        return false;
    }
}
