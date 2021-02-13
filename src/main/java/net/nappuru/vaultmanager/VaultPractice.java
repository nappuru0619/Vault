package net.nappuru.vaultmanager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class VaultPractice extends JavaPlugin {

    public VaultManager val = null;

    @Override
    public void onEnable() {
        getLogger().info("VaultManagerが起動しました");
        getCommand("mbal").setExecutor(this);
        val = new VaultManager(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("VaultManagerを停止しました");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        UUID puuid = p.getUniqueId();
        double bal = val.getBalance(puuid);

        if (args[0] == "get") {
            sender.sendMessage("100円を追加しました");
            VaultManager.economy.withdrawPlayer(p, 100);
        }
        if (args[0] == "take") {
            sender.sendMessage("100円を引き出しました");
            VaultManager.economy.depositPlayer(p, 100);
        }
        if (args.length == 0) {
            sender.sendMessage("あなたは今" + bal + "円を持っています");
        }
        return true;
    }
}
