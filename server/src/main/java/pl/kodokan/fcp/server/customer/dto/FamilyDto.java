package pl.kodokan.fcp.server.customer.dto;

import java.util.HashSet;
import java.util.Set;

public class FamilyDto {

    private FamilyMemberDto father;
    private FamilyMemberDto mother;
    private Set<FamilyMemberDto> children;

    public FamilyMemberDto getFather() {
        return father;
    }

    public void setFather(FamilyMemberDto father) {
        this.father = father;
    }

    public FamilyMemberDto getMother() {
        return mother;
    }

    public void setMother(FamilyMemberDto mother) {
        this.mother = mother;
    }

    public Set<FamilyMemberDto> getChildren() {
        if(children == null)
            children = new HashSet<>();
        return children;
    }

    public void setChildren(Set<FamilyMemberDto> children) {
        this.children = children;
    }

    public void addChild(FamilyMemberDto child){
        if(children == null)
            children = new HashSet<>();
        children.add(child);
    }
}
