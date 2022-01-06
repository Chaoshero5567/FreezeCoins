package de.coins.chaos.freezecoins.listeners;

import de.coins.chaos.freezecoins.utils.coinslibary.ICoinsInterface;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    private final ICoinsInterface coinsInterface;
    public JoinListener(ICoinsInterface iCoinsInterface) {
        this.coinsInterface = iCoinsInterface;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        // Checking if player Joined for the first time!
        coinsInterface.userJoin(event.getPlayer().getUniqueId());
    }
}
