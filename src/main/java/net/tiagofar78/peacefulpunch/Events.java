package net.tiagofar78.peacefulpunch;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import net.tiagofar78.peacefulpunch.managers.ConfigManager;

public class Events implements Listener {
	
	@EventHandler
	public void onAttackMob(EntityDamageByEntityEvent e) {
		Entity eDamager = e.getDamager();
		
		if (!(eDamager instanceof Player)) {
			return;
		}
		
		Player damager = (Player) eDamager;
		@SuppressWarnings("deprecation")
		ItemStack item = damager.getItemInHand();
		
		if (item == null) {
			System.out.println("Material null");
			e.setCancelled(true);
			return;
		}
		
		ConfigManager config = ConfigManager.getInstance();
		
		if (config.isMaterialBlocked(item.getType())) {
			e.setCancelled(true);
			System.out.println("Material blocked");
			return;
		}
		
		if (!config.isMobHurtable(e.getEntity().getType())) {
			e.setCancelled(true);
			System.out.println("entity blocked");
			return;
		}
		
	}

}
