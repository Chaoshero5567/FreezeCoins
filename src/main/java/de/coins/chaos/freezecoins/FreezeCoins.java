package de.coins.chaos.freezecoins;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import de.coins.chaos.freezecoins.utils.coinslibary.ICoinsInterface;
import de.coins.chaos.freezecoins.utils.coinslibary.ICoinsRepository;
import de.coins.chaos.freezecoins.utils.configs.SQLConfig;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.sql.SQLException;

public final class FreezeCoins extends JavaPlugin {
    @Getter private static FreezeCoins instance;
    private SQLConfig sqlConfig;
    private JdbcPooledConnectionSource source;
    private ICoinsInterface iCoinsInterface;

    @Override
    public void onEnable() {
        instance = this;
        sqlConfig = new SQLConfig(this);
        source.setUrl("jdbc:mysql://" +  sqlConfig.getHost() + ":" + sqlConfig.getPort() + "/" + sqlConfig.getDatabase());
        source.setUsername(sqlConfig.getUser());
        source.setPassword(sqlConfig.getPassword());
        try {
            source.initialize();
        } catch (SQLException exception) {
            Bukkit.getConsoleSender().sendMessage("There was an problem activating FreezeCoins during the establishment of the database connection, check if you put in valid credentials");
        }

        iCoinsInterface = new ICoinsRepository(source);

    }

    @Override
    public void onDisable() {
        try {
            source.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
