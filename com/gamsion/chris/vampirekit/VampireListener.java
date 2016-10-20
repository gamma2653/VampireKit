package com.gamsion.chris.vampirekit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class VampireListener implements Listener {
	private Plugin p;

	public VampireListener(Plugin p) {
		this.p = p;
	}

	protected static final String vampireHelm = ChatColor.DARK_GRAY
			+ "Vampire Helmet";
	protected static final String vampireChest = ChatColor.DARK_GRAY
			+ "Vampire Chest";
	protected static final String vampireLeggings = ChatColor.DARK_GRAY
			+ "Vampire Leggings";
	protected static final String vampireBoots = ChatColor.DARK_GRAY
			+ "Vampire Boots";
	protected static final String vampireSword = ChatColor.DARK_GRAY
			+ "Vampire Sword";

	@EventHandler
	public void onPlayerHit(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			double healed = 0.0;
			final Player attacker = (Player) e.getDamager();
			// Vampire Helmet
			if (attacker.getEquipment().getHelmet() !=null && attacker.getEquipment().getHelmet().hasItemMeta() && attacker.getEquipment().getHelmet().getType() == Material.CHAINMAIL_HELMET
					&& attacker.getEquipment().getHelmet().getItemMeta()
							.getDisplayName().equals(vampireHelm)) {
				if (attacker.hasPermission("vampirekit.useArmor")
						|| p.getConfig().getBoolean("needPermission")) {
					healed += (e.getFinalDamage() / 8);
				} else if (p.getConfig().getBoolean("harshConsequences")) {
					cursePlayer(attacker);
					e.setCancelled(true);
					return;
				} else {
					attacker.sendMessage(ChatColor.RED
							+ "You don't have permission to use that helmet!");
				}

			}

			// Vampire Chestplate
			if (attacker.getEquipment().getChestplate() !=null && attacker.getEquipment().getChestplate().hasItemMeta() && attacker.getEquipment().getChestplate().getType() == Material.CHAINMAIL_CHESTPLATE
					&& attacker.getEquipment().getChestplate().getItemMeta()
							.getDisplayName().equals(vampireChest)) {
				if (attacker.hasPermission("vampirekit.useArmor")
						|| p.getConfig().getBoolean("needPermission")) {
					healed += (e.getFinalDamage() / 8);
				} else if (p.getConfig().getBoolean("harshConsequences")) {
					cursePlayer(attacker);
					e.setCancelled(true);
					return;
				} else {
					attacker.sendMessage(ChatColor.RED
							+ "You don't have permission to use that chestplate!");
				}
			}

			// Vampire Leggings
			if (attacker.getEquipment().getLeggings() !=null && attacker.getEquipment().getLeggings().hasItemMeta() && attacker.getEquipment().getLeggings().getType() == Material.CHAINMAIL_LEGGINGS
					&& attacker.getEquipment().getLeggings().getItemMeta()
							.getDisplayName().equals(vampireLeggings)) {
				if (attacker.hasPermission("vampirekit.useArmor")
						|| p.getConfig().getBoolean("needPermission")) {
					healed += (e.getFinalDamage() / 8);
				} else if (p.getConfig().getBoolean("harshConsequences")) {
					cursePlayer(attacker);
					e.setCancelled(true);
					return;
				} else {
					attacker.sendMessage(ChatColor.RED
							+ "You don't have permission to use those leggings!");
				}
			}

			// Vampire Boots
			if (attacker.getEquipment().getBoots() !=null && attacker.getEquipment().getBoots().hasItemMeta() && attacker.getEquipment().getBoots().getType() == Material.CHAINMAIL_BOOTS
					&& attacker.getEquipment().getBoots().getItemMeta()
							.getDisplayName().equals(vampireBoots)) {
				if (attacker.hasPermission("vampirekit.useArmor")
						|| p.getConfig().getBoolean("needPermission")) {
					healed += (e.getFinalDamage() / 8);
				} else if (p.getConfig().getBoolean("harshConsequences")) {
					cursePlayer(attacker);
					e.setCancelled(true);
					return;
				} else {
					attacker.sendMessage(ChatColor.RED
							+ "You don't have permission to use those boots!");
				}
			}

			// Vampire Sword
			if (attacker.getEquipment().getItemInHand() !=null && attacker.getEquipment().getItemInHand().hasItemMeta() && attacker.getEquipment().getItemInHand().getType() == Material.STONE_SWORD
					&& attacker.getEquipment().getItemInHand().getItemMeta()
							.getDisplayName().equalsIgnoreCase(vampireSword)) {
				if (attacker.hasPermission("vampirekit.useWeapon")) {
					if (attacker.getLocation().getBlock().getLightLevel() == 4) {
						e.setDamage(e.getDamage() + 2.0);
					}
				} else if (p.getConfig().getBoolean("harshConsequences")) {
					cursePlayer(attacker);
					e.setCancelled(true);
					return;
				} else {
					attacker.sendMessage(ChatColor.RED
							+ "You don't have permission to use this sword!");
					e.setCancelled(true);
				}

			}

			// add health due to armor
			double health = attacker.getHealth() + healed;
			if (health > attacker.getMaxHealth())
				health = attacker.getMaxHealth();
			attacker.setHealth(health);
		}
	}

	/**
	 * Curse the cheater to a sad death.
	 * 
	 * @param attacker
	 *            - Cursed player
	 * 
	 */
	private void cursePlayer(final Player attacker) {
		attacker.sendMessage(ChatColor.DARK_GRAY
				+ "You thought you would get away with this?");
		attacker.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,
				Integer.MAX_VALUE, 255));
		Bukkit.getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
			public void run() {
				attacker.sendMessage(ChatColor.DARK_GRAY
						+ "You knew it was only a matter of time.");
			}
		}, 200);
		Bukkit.getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
			public void run() {
				attacker.sendMessage(ChatColor.DARK_GRAY + "It's ok.");
			}
		}, 300);
		Bukkit.getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
			public void run() {
				attacker.sendMessage(ChatColor.DARK_GRAY + "Sleep.");
			}
		}, 400);
		Bukkit.getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
			public void run() {
				attacker.addPotionEffect(new PotionEffect(
						PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 255));
			}
		}, 400);
		Bukkit.getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
			public void run() {
				attacker.addPotionEffect(new PotionEffect(
						PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 255));
			}
		}, 100);
		Bukkit.getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
			public void run() {
				attacker.addPotionEffect(new PotionEffect(
						PotionEffectType.CONFUSION, Integer.MAX_VALUE, 255));
			}
		}, 400);
		Bukkit.getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
			public void run() {
				attacker.addPotionEffect(new PotionEffect(
						PotionEffectType.HUNGER, Integer.MAX_VALUE, 255));
			}
		}, 200);
		Bukkit.getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
			public void run() {
				attacker.addPotionEffect(new PotionEffect(
						PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 255));
			}
		}, 250);
		Bukkit.getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
			public void run() {
				attacker.addPotionEffect(new PotionEffect(
						PotionEffectType.WITHER, Integer.MAX_VALUE, 255));
			}
		}, 300);
	}
}
