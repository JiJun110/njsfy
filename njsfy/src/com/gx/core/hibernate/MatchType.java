package com.gx.core.hibernate;

/**
 * MatchType.
 * 
 * @author Lingo
 */
public enum MatchType {
    /** equals. */
    EQ,
    /** like. */
    LIKE,
    /** less than. */
    LT,
    /** greater than. */
    GT,
    /** less equals. */
    LE,
    /** greater equals. */
    GE,
    /** in. */
    IN,
    /** NOT. */
    NOT,
    /** unknown. */
    UNKNOWN,
    /** ASC*/
    ASC,
    /**DESC*/
    DESC;
}
