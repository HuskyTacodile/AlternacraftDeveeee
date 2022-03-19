package com.scp.scpmadness.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;

import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;



public class SCP4158Entity extends AnimalEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    protected SCP4158Entity(EntityType<? extends AnimalEntity> p_i48575_1_, World p_i48575_2_) {
        super(p_i48575_1_, p_i48575_2_);
    }
    public boolean isFood(ItemStack p_70877_1_) {
        Item item = p_70877_1_.getItem();
        return item.isEdible() && item.getFoodProperties().isMeat();
    }


    @Override
    protected SoundEvent getAmbientSound()
    {
        //return ModSoundEvents.SPINO_ROAR2.get();
        return null;
    }


    @Override
    protected SoundEvent getDeathSound()
    {
        //return ModSoundEvents.SPINO_ROAR3.get();
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        //return ModSoundEvents.SPINO_ROAR1.get();
        return null;
    }
    public boolean doHurtTarget(Entity p_70652_1_) {
        boolean flag = super.doHurtTarget(p_70652_1_);
        if (flag) {
            this.doEnchantDamageEffects(this, p_70652_1_);
        }

        return flag;
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (!(animationSpeed > -0.10F && animationSpeed < 0.05F) && !this.isAggressive()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("4158.walk", true));
            return PlayState.CONTINUE;
        }
        if (this.isAggressive() && !(this.dead || this.getHealth() < 0.01 || this.isDeadOrDying())) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("4158.attack", true));
            return PlayState.CONTINUE;
        }
        if (this.isSwimming() && !(animationSpeed > -0.10F && animationSpeed < 0.05F) && !this.isAggressive()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("4158.walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("4158.idle", true));

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<SCP4158Entity>
                (this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public static AttributeModifierMap.MutableAttribute attributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 150.00D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FOLLOW_RANGE, 24.0D)
                .add(Attributes.ATTACK_DAMAGE, 15.0D);
    }
    public boolean canBreatheUnderwater() {
        return true;
    }
    protected SoundEvent getSwimSound() {
        //return SoundEvents.FISH_SWIM;
        return null;
    }

    protected void playStepSound(BlockPos p_180429_1_, BlockState p_180429_2_) {
    }

    @javax.annotation.Nullable
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    public boolean canBeControlledByRider() {
        Entity entity = this.getControllingPassenger();
        if (!(entity instanceof PlayerEntity)) {
            return false;
        } else {
            PlayerEntity playerentity = (PlayerEntity)entity;
            return playerentity.getMainHandItem().getItem() == Items.NETHERITE_SWORD || playerentity.getOffhandItem().getItem() == Items.NETHERITE_SWORD;
        }
    }

    public void travelWithInput(Vector3d p_230267_1_) {
        super.travel(p_230267_1_);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.of(Items.NETHERITE_SWORD), false));
        this.goalSelector.addGoal(0,new RandomSwimmingGoal(this,0,1));
        this.goalSelector.addGoal(2, new SwimGoal(this));
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }
}