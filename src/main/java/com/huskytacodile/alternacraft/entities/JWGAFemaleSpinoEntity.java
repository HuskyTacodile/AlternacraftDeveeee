package com.huskytacodile.alternacraft.entities;

import com.huskytacodile.alternacraft.util.ModSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;

import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class JWGAFemaleSpinoEntity extends TameableEntity implements IAnimatable, IRideable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final DataParameter<Boolean> DATA_SADDLE_ID = EntityDataManager.defineId(PigEntity.class, DataSerializers.BOOLEAN);
    private final BoostHelper steering = new BoostHelper(this.entityData, DATA_BOOST_TIME,DATA_SADDLE_ID);
    private static final DataParameter<Integer> DATA_BOOST_TIME = EntityDataManager.defineId(PigEntity.class, DataSerializers.INT);
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
        boolean flag = super.doHurtTarget(p_70652_1_);
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
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.spino.sit", true));
            return PlayState.CONTINUE;
        }
        if (this.isSwimming() && !(animationSpeed > -0.10F && animationSpeed < 0.05F) && !this.isAggressive()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.spino.swim", true));
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
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.JWGAFEMALESPINO.get(), JWGAFemaleSpinoEntity.attributes().build());
    }
    public static AttributeModifierMap.MutableAttribute attributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 69.00D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D);
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
                p_230254_1_.startRiding(this);
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
    public boolean canBreatheUnderwater() {
        return true;
    }
    public Vector3d getDismountLocationForPassenger(LivingEntity p_230268_1_) {
        Direction direction = this.getMotionDirection();
        if (direction.getAxis() == Direction.Axis.Y) {
            return super.getDismountLocationForPassenger(p_230268_1_);
        } else {
            int[][] aint = TransportationHelper.offsetsForDirection(direction);
            BlockPos blockpos = this.blockPosition();
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

            for(Pose pose : p_230268_1_.getDismountPoses()) {
                AxisAlignedBB axisalignedbb = p_230268_1_.getLocalBoundsForPose(pose);

                for(int[] aint1 : aint) {
                    blockpos$mutable.set(blockpos.getX() + aint1[0], blockpos.getY(), blockpos.getZ() + aint1[1]);
                    double d0 = this.level.getBlockFloorHeight(blockpos$mutable);
                    if (TransportationHelper.isBlockFloorValid(d0)) {
                        Vector3d vector3d = Vector3d.upFromBottomCenterOf(blockpos$mutable, d0);
                        if (TransportationHelper.canDismountTo(this.level, p_230268_1_, axisalignedbb.move(vector3d))) {
                            p_230268_1_.setPose(pose);
                            return vector3d;
                        }
                    }
                }
            }

            return super.getDismountLocationForPassenger(p_230268_1_);
        }
    }
    protected SoundEvent getSwimSound() {
        return SoundEvents.FISH_SWIM;
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
    public void travel(Vector3d p_213352_1_) {
        this.travel(this, this.steering, p_213352_1_);
    }

    public float getSteeringSpeed() {
        return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.225F;
    }

    @Override
    public boolean travel(MobEntity p_233622_1_, BoostHelper p_233622_2_, Vector3d p_233622_3_) {
        return IRideable.super.travel(p_233622_1_, p_233622_2_, p_233622_3_);
    }

    @Override
    public boolean boost() {
        return false;
    }

    public void travelWithInput(Vector3d p_230267_1_) {
        super.travel(p_230267_1_);

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