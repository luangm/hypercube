package io.luan.hypercube.entity;

import java.util.Set;

/**
 * 维度的定义，一个维度必须和其他维度完全不重叠
 */
public class Dimension {

    private Long id;

    /**
     * Name must be unique
     */
    private String name;

    /**
     * For display purpose only
     */
    private String displayName;

    /**
     * Explicit Dimensions have a finite set of Members
     * A non-explicit (i.e. Implicit) dimension can have infinite number of members
     */
    private boolean isExplicit;

    /**
     * Explicit Members
     */
    private Set<Member> members;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean getIsExplicit() {
        return isExplicit;
    }

    public void setIsExplicit(boolean isExplicit) {
        this.isExplicit = isExplicit;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
