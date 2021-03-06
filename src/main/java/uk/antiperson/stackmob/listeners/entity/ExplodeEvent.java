package uk.antiperson.stackmob.listeners.entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import uk.antiperson.stackmob.StackMob;
import uk.antiperson.stackmob.entity.StackTools;

public class ExplodeEvent implements Listener {

    private StackMob sm;
    public ExplodeEvent(StackMob sm){
        this.sm = sm;
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event){
        if(StackTools.hasSizeMoreThanOne(event.getEntity())){
            int stackSize = StackTools.getSize(event.getEntity());
            event.setYield(event.getYield() + (event.getYield() * (stackSize - 1) * 0.5f));
        }
    }
}
