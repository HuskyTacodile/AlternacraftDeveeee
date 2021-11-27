package com.huskytacodile.alternacraft.entities;

import com.huskytacodile.alternacraft.util.ModSoundEvents;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;

import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
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

public class JWGAFemaleSpinoEntity extends TameableEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    protected JWGAFemaleSpinoEntity(EntityType<? extends TameableEntity> p_i48575_1_, World p_i48575_2_) {
        super(p_i48575_1_, p_i48575_2_);
        this.setTame(false);
    }
    public boolean isFood(ItemStack p_70877_1_) {
        Item item = p_70877_1_.getItem();
        return item.isEdible() && item.getFoodProperties().isMeat();
    }


    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSoundEvents.SPINO_ROAR2.get();
    }


    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSoundEvents.SPINO_ROAR3.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return ModSoundEvents.SPINO_ROAR1.get();
    }
    public boolean doHurtTarget(Entity p_70652_1_) {
        boolean flag = p_70652_1_.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.doEnchantDamageEffects(this, p_70652_1_);
        }

        return flag;
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
        if (this.isOrderedToSit() || this.getHealth() < 0.01 || this.isDeadOrDying()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.spino.sleep", true));
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
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 69.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_DAMAGE, 11d);
    }
    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        Item item = itemstack.getItem();
        if (this.level.isClientSide) {
            boolean flag = this.isOwnedBy(p_230254_1_) || this.isTame() || item == Items.IRON_SWORD && !this.isTame();
            return flag ? ActionResultType.CONSUME : ActionResultType.PASS;
        } else {
            if (this.isTame()) {
                if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                    if (!p_230254_1_.abilities.instabuild) {
                        itemstack.shrink(1);
                    }

                    this.heal((float)item.getFoodProperties().getNutrition());
                    return ActionResultType.SUCCESS;
                }


                } else if (item == Items.IRON_SWORD && !this.isOnFire()) {
                if (!p_230254_1_.abilities.instabuild) {
                    itemstack.shrink(1);
                }

                if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, p_230254_1_)) {
                    this.tame(p_230254_1_);
                    this.navigation.stop();
                    this.setTarget((LivingEntity)null);
                    this.setOrderedToSit(true);
                    this.level.broadcastEntityEvent(this, (byte)7);
                } else {
                    this.level.broadcastEntityEvent(this, (byte)6);
                }

                return ActionResultType.SUCCESS;
            }

            return super.mobInteract(p_230254_1_, p_230254_2_);
        }
    }
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(2, new SitGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(5, new SwimGoal(this));
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }
}