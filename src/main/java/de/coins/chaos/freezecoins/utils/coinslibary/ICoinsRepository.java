package de.coins.chaos.freezecoins.utils.coinslibary;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import de.coins.chaos.freezecoins.utils.daos.CoinsDAO;
import de.coins.chaos.freezecoins.utils.daos.DAOManager;

import java.sql.SQLException;
import java.util.UUID;

public class ICoinsRepository implements ICoinsInterface {
    public JdbcPooledConnectionSource connectionSource;
    public DAOManager<CoinsDAO, UUID> daoManager;
    public ICoinsRepository(JdbcPooledConnectionSource jdbcPooledConnectionSource) {
        this.connectionSource = jdbcPooledConnectionSource;
        this.daoManager = new DAOManager<CoinsDAO, UUID>(CoinsDAO.class, connectionSource);
    }



    @Override
    public long getCoins(UUID uuid) {
        CoinsDAO coinsDAO = null;
        try {
            coinsDAO = daoManager.getDAO().queryForId(uuid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        assert coinsDAO != null;
        return coinsDAO.getCoins();
    }

    @Override
    public long setCoins(UUID uuid, long coins) {
        CoinsDAO coinsDAO = new CoinsDAO(uuid, coins);
        try {
            daoManager.getDAO().update(coinsDAO);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return getCoins(uuid);
    }

    @Override
    public long addCoins(UUID uuid, long coins) {
        long l = Math.addExact(getCoins(uuid), coins);
        CoinsDAO coinsDAO = new CoinsDAO(uuid, l);
        try {
            daoManager.getDAO().update(coinsDAO);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return getCoins(uuid);
    }

    @Override
    public long removeCoins(UUID uuid, long coins) {
        long l = Math.subtractExact(getCoins(uuid), coins);
        CoinsDAO coinsDAO = new CoinsDAO(uuid, l);
        try {
            daoManager.getDAO().update(coinsDAO);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return getCoins(uuid);
    }

    @Override
    public boolean hasEnoughCoins(UUID uuid, long amount) {
        if (getCoins(uuid) >= amount) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public long userJoin(UUID uuid) {
        try {
            if (daoManager.getDAO().queryForId(uuid) == null) {
                CoinsDAO coinsDAO = CoinsDAO.builder()
                        .uuid(uuid)
                        .coins(1000L)
                        .build();
                daoManager.getDAO().createOrUpdate(coinsDAO);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }
}
