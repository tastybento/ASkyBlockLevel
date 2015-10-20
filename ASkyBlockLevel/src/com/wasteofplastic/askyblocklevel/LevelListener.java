package com.wasteofplastic.askyblocklevel;

import java.util.Iterator;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachmentInfo;

import com.wasteofplastic.askyblock.ASkyBlock;
import com.wasteofplastic.askyblock.events.IslandLevelEvent;

public class LevelListener implements Listener {

    private ASkyBlockLevel plugin;

    /**
     * @param plugin
     * @param topTenLocation
     * @param direction
     */
    public LevelListener(ASkyBlockLevel plugin) {
	this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onNewLevel(final IslandLevelEvent event) {
	//plugin.getLogger().info("Island level event");
	// Remove any old perms
	Player player = plugin.getServer().getPlayer(event.getPlayer());
	if (player == null) {
	    return;
	}
	//plugin.getLogger().info("Removing old perms for " + player.getName());
	// Remove old permissions
	Iterator<PermissionAttachmentInfo> it = player.getEffectivePermissions().iterator();
	while (it.hasNext()) {
	    PermissionAttachmentInfo perm = it.next();
	    if (perm.getPermission().startsWith(plugin.getPermName())) {
		//plugin.getLogger().info("Removing old perm " + perm.getPermission());
		VaultHelper.removePerm(player, perm.getPermission());
	    }
	}
	if (plugin.isGiveAllPerms()) {
	    // Add new permissions
	    for (int i = 1; i <= event.getLevel(); i++) {
		//plugin.getLogger().info("Adding new perm " + plugin.getPermName() + "." + i);
		VaultHelper.addPerm(player, plugin.getPermName() + "." + i);
	    }
	} else {
	    //plugin.getLogger().info("Adding new perm " + plugin.getPermName() + "." + event.getLevel());
	    VaultHelper.addPerm(player, plugin.getPermName() + "." + event.getLevel());
	}
    }


}
