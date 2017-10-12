package by.epam.velooor.entity;

public enum Role {
    /**
     * administrator is the most influential role
     */
    ADMIN,
    /**
     * ordinary registered visitor, all functions available
     */
    USER,
    /**
     * not registered visitor, can stay only on greeting page
     */
    GUEST
}
