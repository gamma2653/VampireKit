package com.gamsion.chris.vampirekit;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class VampireKit extends JavaPlugin{
	@Override
	public void onEnable(){
		this.saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(new VampireListener(this), this);
		this.getLogger().info("VampireKit has been enabled.");
	}
	
	@Override
	public void onDisable(){
		this.getLogger().info("VampireKit has been disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(sender instanceof Player){
			Player player = (Player)sender;
			if(cmd.getName().equals("vampirekit")){
				if(player.hasPermission("vampirekit.spawn")){
					ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS);
					ItemStack chest = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
					ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET);
					ItemStack legs = new ItemStack(Material.CHAINMAIL_LEGGINGS);
					ItemStack sword = new ItemStack(Material.STONE_SWORD);
					
					ItemMeta bootsim = boots.getItemMeta();
					ItemMeta chestim = chest.getItemMeta();
					ItemMeta helmetim = helmet.getItemMeta();
					ItemMeta legsim = legs.getItemMeta();
					ItemMeta swordim = sword.getItemMeta();
					bootsim.setDisplayName(VampireListener.vampireBoots);
					chestim.setDisplayName(VampireListener.vampireChest);
					helmetim.setDisplayName(VampireListener.vampireHelm);
					legsim.setDisplayName(VampireListener.vampireLeggings);
					swordim.setDisplayName(VampireListener.vampireSword);
					
					boots.setItemMeta(bootsim);
					chest.setItemMeta(chestim);
					helmet.setItemMeta(helmetim);
					legs.setItemMeta(legsim);
					sword.setItemMeta(swordim);
					
					player.getInventory().addItem(boots, chest, helmet, legs, sword);
				}else{
					player.sendMessage(ChatColor.RED + "Sorry you do not have permission to spawn the vampire kit.");
				}
			}
		}
		
		return true;
	}
}
