package com.huskytacodile.alternacraft.entities;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class JWGAFemaleSpinoEntity extends CreatureEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    protected JWGAFemaleSpinoEntity(EntityType<? extends CreatureEntity> p_i48575_1_, World p_i48575_2_) {
        super(p_i48575_1_, p_i48575_2_);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (!(animationSpeed > -0.10F && animationSpeed < 0.05F) && !this.isAggressive()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.spino.walk", true));
            return PlayState.CONTINUE;
        }
        if (this.isAggressive() && !(this.dead || this.getHealth() < 0.01 || this.isDeadOrDying())) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.spino.attack", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.spino.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<JWGAFemaleSpinoEntity>
                (this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
    public static AttributeModifierMap.MutableAttribute attributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 60d)
                .add(Attributes.MOVEMENT_SPEED, 0.2d)
                .add(Attributes.FOLLOW_RANGE, 16D)
                .add(Attributes.ATTACK_DAMAGE, 10.5d);
    }
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(5, new SwimGoal(this));
    }
}