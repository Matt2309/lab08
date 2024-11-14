package it.unibo.mvc.view;

import java.io.PrintStream;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public class DrawNumberCliView implements DrawNumberView{
    private static final String FRAME_NAME = "Draw Number App";
    private static final String NEW_GAME = ": a new game starts!";

    private DrawNumberController controller;
    private static final PrintStream LOG = System.out;

    public DrawNumberCliView(){}

    @Override
    public void setController(final DrawNumberController observer) {
        this.controller = observer;
    }

    @Override
    public void start() {
        LOG.println(NEW_GAME);
    }

    @Override
    public void result(final DrawResult res) {
        switch (res) {
            case YOURS_HIGH, YOURS_LOW -> {
                LOG.println(res.getDescription());
                return;
            }
            case YOU_WON -> LOG.println(res.getDescription() + NEW_GAME);
            case YOU_LOST -> LOG.println("Lost: "+res.getDescription() + NEW_GAME);
            default -> throw new IllegalStateException("Unknown game state");
        }
    }
}
