package io.luan.hypercube.entity;

import io.luan.exp4j.expression.Expression;
import io.luan.exp4j.expression.expressions.ConstantExpression;
import io.luan.exp4j.expression.expressions.NumericExpression;
import io.luan.exp4j.expression.expressions.VariableExpression;

import java.math.BigDecimal;
import java.util.*;

/**
 * A Hypercube is a graph representation of a model
 * Using hypercube to calculate the value of any concept within this model
 */
public class Hypercube {

    /**
     * Key = Concept Name
     * Value = List of all facts with same concept
     */
    private Map<String, List<HyperNode>> factNodes;

    /**
     * Key = concept name
     */
    private Map<String, Concept> conceptMap;

    /**
     *
     */
    private Map<Fact, HyperNode> factMap;

    /**
     *
     */
    private Map<Constant, HyperNode> constMap;


    /**
     * Constant
     */
    private Map<String, List<HyperNode>> constNodes;

    public Hypercube() {
        factNodes = new HashMap<>(10);
        constNodes = new HashMap<>(10);
        conceptMap = new HashMap<>(10);
        factMap = new HashMap<>(10);
        constMap = new HashMap<>(10);
    }

    /**
     * 将一个fact插入hypercube
     */
    public void addFact(Fact fact) {
        if (fact == null) {
            throw new IllegalArgumentException("fact");
        }

        String conceptName = fact.getConcept().getName();
        List<HyperNode> list = factNodes.get(conceptName);
        if (list == null) {
            list = new ArrayList<>(10);
            factNodes.put(conceptName, list);
            conceptMap.put(conceptName, fact.getConcept());
        }

        // TODO: Check if the fact already exists
        // If exists, but not the same, throw exception
        HyperNode node = new HyperNode(fact);
        list.add(node);
        factMap.put(fact, node);
        fact.setHypercube(this);

        if (fact instanceof ComputedFact) {
            ComputedFact computedFact = (ComputedFact) fact;
            Expression exp = Expression.parse(computedFact.getFormula());
            for (VariableExpression varExp : exp.getVariables()) {
                HyperNode depNode = getFactNode(varExp.getName(), fact.getPeriod());
                node.addDependency(depNode);
            }
            for (ConstantExpression constExp: exp.getConstants()) {
                System.out.println(constExp);
                HyperNode depNode = getConstNode(constExp.getName().replace("#", ""));
                node.addDependency(depNode);
            }

        }
    }

    public void addConstant(Constant constant) {
        String conceptName = constant.getConcept().getName();
        List<HyperNode> list = constNodes.get(conceptName);
        if (list == null) {
            list = new ArrayList<>(10);
            constNodes.put(conceptName, list);
            conceptMap.put(conceptName, constant.getConcept());
        }

        HyperNode node = new HyperNode(constant);
        list.add(node);
        constMap.put(constant, node);

    }

    public Quantity getQuantity(Fact fact) {
        HyperNode node = factMap.get(fact);
        if (node.fact instanceof ComputedFact) {
            ComputedFact compFact = (ComputedFact) node.fact;
            Expression exp = Expression.parse(compFact.getFormula());
            Map<String, Object> input = new HashMap<>(10);
            for (HyperNode depNode: node.dependency) {
                //System.out.println(depNode);
                if (depNode.fact != null) {
                    String inputName = depNode.fact.getConcept().getName();
                    BigDecimal inputVal = depNode.fact.getQuantity().getValue();
                    input.put(inputName, inputVal);
                }

                if (depNode.constant != null) {
                    String inputName = "#" + depNode.constant.getConcept().getName();
                    BigDecimal inputVal = depNode.constant.getQuantity().getValue();
                    input.put(inputName, inputVal);
                }
            }
            NumericExpression result = (NumericExpression) exp.evaluate(input);
            return new Quantity(result.toDoubleValue(), "Unit");
        }
        else {
            return node.fact.getQuantity();
        }
    }

    private HyperNode getFactNode(String conceptName, Period period) {
        if (factNodes == null) {
            return null;
        }
        List<HyperNode> list = factNodes.get(conceptName);
        if (list == null) {
            return null;
        }

        for (HyperNode node : list) {
            if (node.fact.getPeriod().equals(period)) {
                return node;
            }
        }
        return null;
    }

    private HyperNode getConstNode(String conceptName) {
        if (constNodes == null) {
            return null;
        }
        List<HyperNode> list = constNodes.get(conceptName);
        if (list == null) {
            return null;
        }

        for (HyperNode node : list) {
            return node;
        }
        return null;
    }

    public Fact getFact(Concept concept, Period period, Map<Dimension, Member> dimMap) {
        HyperNode node = getFactNode(concept.getName(), period);
        if (node != null) {
            return node.fact;
        }
        return null;
    }


    private class HyperNode {
        Fact fact;
        Constant constant;
        Set<HyperNode> dependency;

        public HyperNode(Fact fact) {
            this.fact = fact;
        }
        public HyperNode(Constant constant) {
            this.constant = constant;
        }

        public void addDependency(HyperNode node) {
            if (dependency == null) {
                dependency = new HashSet<>(10);
            }
            dependency.add(node);
        }

        @Override
        public int hashCode() {
            if (fact != null) {
                return fact.hashCode();
            }
            if (constant != null) {
                return constant.hashCode();
            }
            return 0;
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
