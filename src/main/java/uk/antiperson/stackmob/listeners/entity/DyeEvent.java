package uk.antiperson.stackmob.listeners.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.bukkit.metadata.FixedMetadataValue;
import uk.antiperson.stackmob.StackMob;
import uk.antiperson.stackmob.tools.StackTools;
import uk.antiperson.stackmob.tools.extras.GlobalValues;

public class DyeEvent implements Listener {

    private StackMob sm;
    public DyeEvent(StackMob sm) {
        this.sm = sm;
    }

    @EventHandler
    public void onSheepDye(SheepDyeWoolEvent event) {
        if(!(sm.getStackTools().hasValidStackData(event.getEntity()))) {
            return;
        }
        if(event.isCancelled()){
            return;
        }
        int stackSize = sm.getStackTools().getSize(event.getEntity());
        Entity oldEntity = event.getEntity();

        if (sm.config.getCustomConfig().getBoolean("divide-on.sheep-dye") && stackSize > 1) {
            Sheep newEntity = (Sheep) sm.tools.duplicate(oldEntity);
            newEntity.setColor(event.getEntity().getColor());
            sm.getStackTools().setSize(newEntity,stackSize - 1);
            newEntity.setMetadata(GlobalValues.NO_SPAWN_STACK, new FixedMetadataValue(sm, true));

            sm.getStackTools().setSize(oldEntity, 1);
            oldEntity.setCustomName(null);
        }
    }
}
