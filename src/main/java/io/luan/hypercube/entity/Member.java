package io.luan.hypercube.entity;

import java.util.Set;

/**
 * 维度下可选择的值。
 * 一个值可以是叶值（无子值），也可以有多个子值 （父子关系）
 */
public class Member {

    private Long id;

    private Dimension dimension;

    /**
     * Use a String to represent the value.
     */
    private String value;

    /**
     * Parent
     */
    private Member parentMember;

    /**
     * Set of children
     */
    private Set<Member> childMembers;

    public Member(Dimension dimension, String value) {
        setDimension(dimension);
        setValue(value);
    }

    public Set<Member> getChildMembers() {
        return childMembers;
    }

    public void setChildMembers(Set<Member> childMembers) {
        this.childMembers = childMembers;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getIsLeafMember() {
        if (childMembers != null && childMembers.size() > 0) {
            return false;
        }

        return true;
    }

    public Member getParentMember() {
        return parentMember;
    }

    public void setParentMember(Member parentMember) {
        this.parentMember = parentMember;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
