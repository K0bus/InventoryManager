package fr.k0bus.inventorymanager.database;

import fr.k0bus.inventorymanager.exceptions.InvalidDatabaseTypeException;

public enum DBType {
    MYSQL("mysql"),
    SQLITE("sqlite");

    private final String id;

    DBType(String id) {
        this.id = id;
    }

    public static DBType getFromID(String id) throws InvalidDatabaseTypeException {
        for(DBType type: DBType.values())
        {
            if(type.id.equals(id)) return type;
        }
        throw new InvalidDatabaseTypeException(id);
    }
}
