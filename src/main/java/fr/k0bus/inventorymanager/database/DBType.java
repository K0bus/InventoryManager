package fr.k0bus.inventorymanager.database;

public enum DBType {
    MYSQL("mysql"),
    SQLITE("sqlite");

    String id;

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
