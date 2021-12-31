package de.coins.chaos.freezecoins.utils.coinslibary;

import java.util.UUID;

public interface ICoinsInterface {
    public long getCoins(UUID uuid);
    public long setCoins(UUID uuid, long coins);
    public long addCoins(UUID uuid, long coins);
    public long removeCoins(UUID uuid, long coins);
    public boolean hasEnoughCoins(UUID uuid, long amount);
    public long userJoin(UUID uuid);
}
