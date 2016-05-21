package io.luan.hypercube.entity;

/**
 * 一个金融概念，如Cash（现金），Asset（资产）或者Revenue（收入）
 */
public class Concept {
    public static final String DEFAULT_NAMESPACE = "io.luan.hypercube.default";

    /**
     * 概念的命名空间。如果不填则为默认
     * 不同公司之间，或者不同用处下可以定义命名空间。
     * 计算时候优先考虑同一命名空间下的。
     */
    private String namespace;

    /**
     * Concept的标示名，这个必须是同一命名空间下全局唯一的
     */
    private String name;

    /**
     * 是否是流量，流量代表了一个时间范围内的值。
     * 如：Revenue(收入)是流量（从2015/1/1到2015/12/31）
     * <p>
     * 存量代表了一个固定时刻下的值。
     * 如：Cash(现金)是存量（2016/1/1的现金值）
     */
    private boolean isFlow;

    public Concept(String name) {
        this(DEFAULT_NAMESPACE, name, false);
    }

    public Concept(String name, boolean isFlow) {
        this(DEFAULT_NAMESPACE, name, isFlow);
    }

    public Concept(String namespace, String name) {
        this(namespace, name, false);
    }

    public Concept(String namespace, String name, boolean isFlow) {
        this.namespace = namespace;
        this.name = name;
        this.isFlow = isFlow;
    }

    public boolean getIsFlow() {
        return isFlow;
    }

    public void setIsFlow(boolean isFlow) {
        this.isFlow = isFlow;
    }

    public boolean getIsStock() {
        return !isFlow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public int hashCode() {
        return (namespace + ":" + name).hashCode();
    }

    @Override
    public String toString() {
        return namespace + ":" + name;
    }
}
