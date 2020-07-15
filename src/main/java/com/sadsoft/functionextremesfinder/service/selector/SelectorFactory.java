package com.sadsoft.functionextremesfinder.service.selector;

public class SelectorFactory {

    private static final String ROULETTE_WHEEL = "ROULETTE_WHEEL";
    private static final String TOURNAMENT = "TOURNAMENT";
    private static final String RANK = "RANK";

    private static Selector selector = null;

    public SelectorFactory build(String selectorType) {
        switch (selectorType) {
            case ROULETTE_WHEEL:
                selector = new RouletteWheel();
                return this;
            case TOURNAMENT:
                return this;
            case RANK:
                return this;
            default:
                selector = new RouletteWheel();
                return this;
        }
    }

    public Selector get() {
        return selector;
    }
}
