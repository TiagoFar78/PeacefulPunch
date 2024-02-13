package net.tiagofar78.peacefulpunch;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener {
	
	private static final String[] WEAPONS_NAMES = { "SWORD", "AXE", "BOW", "TRIDENT" }; // Depois deve ser para tirar
	
	@EventHandler
	public void onAttackMob(EntityDamageByEntityEvent e) {
		Entity eDamager = e.getDamager();
		
		if (!(eDamager instanceof Player)) {
			return;
		}
		
		Player damager = (Player) eDamager;
		ItemStack item = damager.getItemInUse();
		
		if (item == null) {
			e.setCancelled(true);
			return;
		}
		
		if (!isWeapon(item)) {
			e.setCancelled(true);
			return;
		}
		
	}
	
	private boolean isWeapon(ItemStack item) {
		String materialName = item.getType().toString();
		
		for (String weapon : WEAPONS_NAMES) {
			if (materialName.contains(weapon)) {
				return true;
			}
		}
		
		return false;
	}

}
