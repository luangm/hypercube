package io.luan.hypercube.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * A Hypercube is a graph representation of a model
 * Using hypercube to calculate the value of any concept within this model
 */
public class Hypercube {

    private Set<HyperNode> nodes;

    public Hypercube() {
        //
    }

    /**
     * 将一个fact插入hypercube
     */
    public void addFact(Fact fact) {
        if (nodes == null) {
            nodes = new HashSet<>(10);
        }
        // TODO: Check if the fact already exists
        // If exists, but not the same, throw exception

        HyperNode node = new HyperNode(fact);
        nodes.add(node);
    }

    private class HyperNode {
        Fact fact;
        Set<HyperNode> dependency;

        public HyperNode(Fact fact) {
            this.fact = fact;
        }

        @Override
        public int hashCode() {
            return fact.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof HyperNode)) {
                return false;
            }
            HyperNode node = (HyperNode) obj;
            return fact.equals(node.fact);
        }
    }
}
