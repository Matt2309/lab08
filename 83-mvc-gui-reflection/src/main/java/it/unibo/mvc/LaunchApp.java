package it.unibo.mvc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;

import it.unibo.mvc.view.*;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) throws Exception {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);

        for (String className : args) {
            className = className.substring (0 , 1).toUpperCase() + className.substring(1);
            final Class <?> cl = Class.forName(className);
            final Constructor <?> cns = cl.getConstructor();

            final Object o = cns.newInstance();
            app.addView((DrawNumberView)o);
        }
    }
}
