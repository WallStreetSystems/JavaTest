/**
 * Copyright (c) 2014 Wall Street Systems, Delaware Inc.
 * All Rights reserved.
 *
 * This software is proprietary and confidential to Wall Street Systems, Delaware Inc.
 * and is protected by copyright law as an unpublished work.
 * Unauthorized access and disclosure strictly forbidden.
 */
package javatest;

/**
 * Holds all registered writers and dispatches messages to all of them.
 * In real life, this would be split into 2 interfaces.
 */
public interface LogManager {

    /**
     * Registers a new {@link LogWriter}.
     */
    void register(Class<? extends LogWriter> writerClass);

    /**
     * Dispatches a message to all the registered writers.
     */
    void dispatch(Severity severity, String category, String message);

}
