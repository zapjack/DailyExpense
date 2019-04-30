/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animals2;

import java.util.ArrayList;

/**
 *
 * @author agjackso
 */
public class Entity {
    private String name;
    private Entity back;
    
    private Environment environment;
    
    private TimeSpan lifeTime;
    
    private Associations mandatory = new AssociationsImpl("mandatory");
    private Associations common = new AssociationsImpl("common");
    
    private ArrayList<Flow> flow = new ArrayList<>();
    private ArrayList<Worth> worth = new ArrayList<>();

    public Entity(String name) {
        this.name = name;
    }

    Entity(String alan, TimeSpanImpl timeSpanImpl) {
        lifeTime = timeSpanImpl;
    }

    @Override
    public String toString() {
        return name + ": " + mandatory;
    }

    void addAssociation(String name, Entity... entities) {
        switch (name) {
            case "mandatory":
                for (Entity ent: entities) {
                    ent.setBack(this);
                    mandatory.addEntity(ent);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown name for Association");
        }
    }

    void setBack(Entity back) {
        this.back = back;
    }
    
    
}
