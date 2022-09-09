package net.kaupenjoe.tutorialmod.entity.ai;

import net.kaupenjoe.tutorialmod.entity.custom.ChomperEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class ChomperAttackGoal extends MeleeAttackGoal {
    private ChomperEntity entity;
    private int animCounter = 0;
    private int animTickLength = 20;
    
    public ChomperAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        entity = ((ChomperEntity) pMob);
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        if (pDistToEnemySqr <= this.getAttackReachSqr(pEnemy) && this.getTicksUntilNextAttack() <= 0) {
            if(entity != null) {
                entity.setAttacking(true);
                animCounter = 0;
            }
        }

        super.checkAndPerformAttack(pEnemy, pDistToEnemySqr);
    }

    @Override
    public void tick() {
        super.tick();
        if(entity.isAttacking()) {
            animCounter++;

            if(animCounter >= animTickLength) {
                animCounter = 0;
                entity.setAttacking(false);
            }
        }
    }


    @Override
    public void stop() {
        animCounter = 0;
        entity.setAttacking(false);
        super.stop();
    }
}
