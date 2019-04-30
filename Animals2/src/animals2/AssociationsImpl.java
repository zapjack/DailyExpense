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
public class AssociationsImpl implements Associations {
    private final String name;
    private ArrayList<Entity> assocs = new ArrayList<Entity>();

    public AssociationsImpl(String name) {
        this.name = name;
    }
    
    @Override
    public Entity addEntity(Entity ent) {
        assocs.add(ent);
        return ent;
    }

    @Override
    public Entity removeEntity(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        if (assocs.size() <= 0)
            return "";
        else
            return "Assoc: " + name +  ": " + assocs.toString();
        
    }
}
