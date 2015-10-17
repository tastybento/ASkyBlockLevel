package com.wasteofplastic.askyblocklevel;


import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class ASkyBlockLevel extends JavaPlugin {

    @Override
    public void onEnable() {
	// Enable the plugin
	PluginManager manager = getServer().getPluginManager();
	// Check for ASkyBlock
	Plugin asb = manager.getPlugin("ASkyBlock");

	if (asb == null) {
	    getLogger().severe("ASkyBlock not loaded. Disabling plugin");
	    getServer().getPluginManager().disablePlugin(this);
	} else {
	    getLogger().info("Linking to ASkyblock Version " + asb.getDescription().getVersion());
	    if (!VaultHelper.setupPermissions()) {
		getLogger().severe("Cannot link with Vault for permissions! Disabling plugin!");
		getServer().getPluginManager().disablePlugin(this);
		return;
	    }
	    // Load config
	    saveDefaultConfig();
	    getServer().getPluginManager().registerEvents(new LevelListener(this),this);
	}

    }

    @Override
    public void onDisable() {
	//getLogger().info("DEBUG: disabling");
    }

}
