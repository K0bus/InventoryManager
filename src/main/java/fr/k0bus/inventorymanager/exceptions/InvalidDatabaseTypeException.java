package fr.k0bus.inventorymanager.exceptions;

public class InvalidDatabaseTypeException  extends Exception{
    public InvalidDatabaseTypeException (String databaseType)
    {
        super("Unknown database type : " + databaseType + ". Please use 'sqlite' or 'mysql'.");
    }
}
