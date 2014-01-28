/**
 * File:    App.java
 * Author:  Pavel Halas
 * Created: 24 Jan 2014
 *
 * Copyright (c) 2014 Wall Street Systems, Delaware Inc.
 * All Rights reserved.
 *
 * This software is proprietary and confidential to Wall Street Systems, Delaware Inc.
 * and is protected by copyright law as an unpublished work.
 * Unauthorized access and disclosure strictly forbidden.
 */
package javatest;

import javatest.impl.ConsoleWriter;
import javatest.impl.ConstantlyLoggingServiceRunningOnVariousThreads;
import javatest.impl.DatabaseWriter;
import javatest.impl.MailWriter;

import javax.inject.Inject;
import javax.inject.Named;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Example of multithreading application using loggers.
 */
public class App {

    @Inject
    private Log log;

    @Inject
    @Named("Internal")
    private Log internalLog;

    @Inject
    @Named("External")
    private Log externalLog;

    @Inject
    private LogManager manager;

    private void run() {
        manager.register(ConsoleWriter.class);

        // Expected to have 'DEFAULT' category
        log.info("Starting up");

        // Instantiated without Guice to show usage of different loggers
        new ConstantlyLoggingServiceRunningOnVariousThreads(internalLog);
        new ConstantlyLoggingServiceRunningOnVariousThreads(externalLog);

        // Writer registration may happen anytime, this is just an example
        manager.register(DatabaseWriter.class);
        manager.register(MailWriter.class);
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new LogModule());
        App app = injector.getInstance(App.class);
        app.run();
    }

}
