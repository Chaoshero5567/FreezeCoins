package de.coins.chaos.freezecoins.utils.configs;

import lombok.Getter;
import org.bukkit.plugin.Plugin;

public class SQLConfig {
    private FileBuilder fileBuilder;
    public SQLConfig(Plugin plugin) {
        fileBuilder = new FileBuilder(plugin.getDataFolder().getPath(), "SQLConfig");
    }
    @Getter
    public final String host = fileBuilder.getString("host");
    @Getter
    public final int port = fileBuilder.getInteger("port");
    @Getter
    public final String database = fileBuilder.getString("database");
    @Getter
    public final String user = fileBuilder.getString("user");
    @Getter
    public final String password = fileBuilder.getString("password");
}
