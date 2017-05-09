package ru.progrm_jarvis.permormoney.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.progrm_jarvis.permormoney.PermOrMoney;
import ru.progrm_jarvis.permormoney.console.Tracer;

/**
 * @see org.bukkit.command.CommandExecutor;
 * Class for /permormoney command execution
 */
public class CommandPermOrMoney implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        //Args 0 - игрок, 1 - permission, 2 - eco (, 3... - команда) } 3+
        if (args.length >= 3) {
            Player player = Bukkit.getPlayer(args[0]);
            //Check if player is not null
            if (player != null) {
                //Check if player has not permissions
                if (!PermOrMoney.getPermissions().has(player, args[1])) {
                    PermOrMoney.getPermissions().playerAdd(player, args[1]);
                    msg(sender, "Given permission " + args[1] + " to player " + player.getName() + ".");
                } else {
                    PermOrMoney.getEconomy().depositPlayer(player, Integer.parseInt(args[2]));
                    msg(sender, "Given $" + args[2] + " to player " + player.getName() + ".");
                    if (args.length >=4) {
                        StringBuilder cmd = new StringBuilder("");
                        for (int i = 3; i < args.length; i++) cmd.append(args[i]).append(" ");
                        Tracer.err(player.getName());
                        cmd = new StringBuilder(cmd.toString().replace("%p%", player.getName()));
                        cmd = new StringBuilder(cmd.toString().substring(0, cmd.length()-1));
                        if (PermOrMoney.getInstance().getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd.toString())) msg(sender, "Performed command " +
                                ChatColor.ITALIC + cmd + ChatColor.RESET + " for player " + player.getName() + ".");
                    }
                }
                return true;
            } else {
                err(sender, "Given player is null.");
            }
        } else {
            err(sender, "Not enough arguments. Right usage: ");
        }
        return false;
    }
    private static void msg(CommandSender sender, String message) {
        if (sender instanceof Player) {
            sender.sendMessage(message);
        } else {
            Tracer.msg(message);
        }
    }
    private static void err(CommandSender sender, String message) {
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.RED + message);
        } else {
            Tracer.err(message);
        }
    }
}
