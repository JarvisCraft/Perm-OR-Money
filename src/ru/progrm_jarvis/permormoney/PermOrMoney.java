package ru.progrm_jarvis.permormoney;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import ru.progrm_jarvis.permormoney.command.CommandPermOrMoney;
import ru.progrm_jarvis.permormoney.console.Tracer;

/**
 * @author PROgrm_JARvis on
 * @see org.bukkit.plugin.java.JavaPlugin
 * Main class of Plugin PermOrMoney
 */
public class PermOrMoney extends JavaPlugin {
    private static PermOrMoney permOrMoney;
    public static PermOrMoney getInstance() {
        return permOrMoney;
    }
    @Override
    public void onEnable() {
        if (permOrMoney == null) {
            permOrMoney = this;
        }
        enableLogger();
        PluginDescriptionFile pluginDescriptionFile = getDescription();
        if (!setupEconomy() ) {
            Tracer.err("Could not load Vault-Economy!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (!setupPermissions()) {
            Tracer.err("Could not load Vault-Permissions!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        registerCommands();
        Tracer.msg(pluginDescriptionFile.getName() + " has been enabled. Version: " + pluginDescriptionFile.getVersion());
    }
    @Override
    public void onDisable() {
        PluginDescriptionFile pluginDescriptionFile = getDescription();
        Tracer.msg(pluginDescriptionFile.getName() + " has been disabled. Version: " + pluginDescriptionFile.getVersion());
    }
    //Logging
    private void enableLogger () {
        Tracer.setLogger(getLogger());
    }
    //Economy
    private static Economy economy;
    private static Permission permissions;
    public static Economy getEconomy() {
        return economy;
    }
    public static Permission getPermissions() {
        return permissions;
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }
    //Permissions
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        permissions = rsp.getProvider();
        return permissions != null;
    }
    //Commands
    private void registerCommands() {
        getCommand("permormoney").setExecutor(new CommandPermOrMoney());
    }
}
