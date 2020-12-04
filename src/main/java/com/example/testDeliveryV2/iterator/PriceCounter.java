package com.example.testDeliveryV2.iterator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PriceCounter implements MyContainer {
    private Double[] prices;

    @Override
    public MyIterator getMyIterator() {
        return new PriceIterator();
    }
    private class PriceIterator implements MyIterator {
        private int index;
        @Override
        public boolean hasNext() {
            if (index < prices.length) {
                return true;
            }
            return false;
        }
        @Override
        public Object next() {
            if (this.hasNext()) {
                return prices[index++];
            }
            return null;
        }
    }
}
