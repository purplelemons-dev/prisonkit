package group.thebasement.plugins.prisonkits.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import group.thebasement.plugins.prisonkits.PrisonKits;

public class InventoryHandler implements Listener {

    private final PrisonKits plugin;

    public InventoryHandler(PrisonKits plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent event) {
        if (event.getInventory().equals(plugin.testInv) && event.getSlot() == 4 && event.getCurrentItem() != null) {
            event.setCancelled(true);
            // event.getCurrentItem().setAmount(-1);
        } else if (event.getInventory().equals(plugin.kitManager.kitInv) && event.getCurrentItem() != null) {
            event.setCancelled(true);
            if (plugin.kitManager.giveKit((org.bukkit.entity.Player) event.getWhoClicked(),
                    event.getCurrentItem().getItemMeta().getDisplayName())) {
                event.getWhoClicked().sendMessage("§aYou have received the §b"
                        + event.getCurrentItem().getItemMeta().getDisplayName() + " §akit!");
            } else {
                event.getWhoClicked()
                        .sendMessage("§cYou must wait §b"
                                + plugin.kitManager.getCooldown((org.bukkit.entity.Player) event.getWhoClicked(),
                                        event.getCurrentItem().getItemMeta().getDisplayName())
                                + " §cseconds before using this kit again!");
            }
        }
    }

}
