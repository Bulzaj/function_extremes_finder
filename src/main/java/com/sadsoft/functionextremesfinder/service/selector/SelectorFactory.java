package com.sadsoft.functionextremesfinder.service.selector;

public class SelectorFactory {

    private static final String ROULETTE_WHEEL = "ROULETTE_WHEEL";
    private static final String TOURNAMENT = "TOURNAMENT";
    private static final String RANK = "RANK";

    private static Selector selector = null;

    public SelectorFactory build(String selectorType) {
        switch (selectorType) {
            case ROULETTE_WHEEL:
                selector = new RouletteWheelSelector();
                break;
            case TOURNAMENT:
                break;
            case RANK:
                break;
            default: selector = new RouletteWheelSelector();
        };
        return this;
    }

    public Selector get() {
        return selector;
    }
}
