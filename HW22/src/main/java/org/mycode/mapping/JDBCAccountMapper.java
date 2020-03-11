package org.mycode.mapping;

import org.apache.log4j.Logger;
import org.mycode.exceptions.NoSuchEntryException;
import org.mycode.model.Account;
import org.mycode.model.AccountStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCAccountMapper implements Mapper<Account, ResultSet, Long> {
    private static final Logger log = Logger.getLogger(JDBCAccountMapper.class);

    @Override
    public Account map(ResultSet source, Long searchId) throws SQLException, NoSuchEntryException {
        int currentRow = source.getRow();
        source.beforeFirst();
        long id = -1;
        String name = "";
        AccountStatus accountStatus = null;
        while (source.next()) {
            if (source.getLong(1) == searchId) {
                id = source.getLong(1);
                name = source.getString(2);
                accountStatus = AccountStatus.valueOf(source.getString(3));
            }
        }
        source.absolute(currentRow);
        if (id == -1) {
            log.warn("No such entry with ID: " + searchId);
            throw new NoSuchEntryException("Reading from DB is failed");
        }
        return new Account(id, name, accountStatus);
    }
}
