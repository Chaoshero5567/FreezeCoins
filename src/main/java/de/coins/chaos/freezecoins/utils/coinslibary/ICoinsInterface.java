package de.coins.chaos.freezecoins.utils.coinslibary;

import java.util.UUID;

public interface ICoinsInterface {

    //Get the Coins of a player
    public long getCoins(UUID uuid);

    //Set the coins of the player
    public long setCoins(UUID uuid, long coins);

    //Add coins to the bank of a player
    public long addCoins(UUID uuid, long coins);

    //Remove coins from the bank of a player
    public long removeCoins(UUID uuid, long coins);

    //Ask if the player has enough coins for an transaction, the amount is the amount you want to know if the players has that
    public boolean hasEnoughCoins(UUID uuid, long amount);

    //This is the userJoin method to check if the player joined for the first time
    public long userJoin(UUID uuid);
}