package mod.upcraftlp.spookycraft.handler;

import core.upcraftlp.craftdev.api.util.Utils;
import mod.upcraftlp.spookycraft.ModConfig;
import mod.upcraftlp.spookycraft.block.BlockHauntedPumpkin;
import mod.upcraftlp.spookycraft.block.fluid.BlockFluidBoneMilk;
import mod.upcraftlp.spookycraft.init.SpookyBlocks;
import mod.upcraftlp.spookycraft.init.SpookyItems;
import mod.upcraftlp.spookycraft.util.EntityUtils;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author UpcraftLP
 */
@Mod.EventBusSubscriber
public class PlayerHandler {

	@SubscribeEvent
	public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
		ItemStack stack = event.getItemStack();
		if(stack.getItem() == Items.GLASS_BOTTLE && PotionUtils.getEffectsFromStack(stack).isEmpty()) {
			World world = event.getWorld();
			EntityPlayer player = event.getEntityPlayer();
			RayTraceResult result = EntityUtils.rayTracePlayer(world, player, true);
			if(result != null && result.typeOfHit == RayTraceResult.Type.BLOCK) {
				BlockPos pos = result.getBlockPos();
				if(world.getBlockState(pos).getBlock() instanceof BlockFluidBoneMilk && world.isBlockModifiable(event.getEntityPlayer(), pos) && world.getBlockState(pos).getValue(BlockFluidBoneMilk.LEVEL) == 0) {
					if(!world.isRemote) {
						world.setBlockToAir(pos);
						event.getItemStack().shrink(1);
						player.addItemStackToInventory(new ItemStack(SpookyItems.BONE_MILK_BOTTLE));
						world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
					}
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onRightClickPumpkin(PlayerInteractEvent.RightClickBlock event) {
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		IBlockState state = world.getBlockState(pos);
		ItemStack stack = event.getItemStack();
		if (state.getBlock() == Blocks.PUMPKIN && stack.getItem() == Item.getItemFromBlock(Blocks.TORCH)) {
			stack.shrink(1);
			world.setBlockState(pos, SpookyBlocks.Special.HAUNTED_PUMPKIN.getDefaultState().withProperty(BlockHauntedPumpkin.FACING, state.getValue(BlockPumpkin.FACING)));
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onBreakSpookyBlock(BlockEvent.BreakEvent event) {
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		if (ModConfig.soundsOnBlockBreak && !world.isRemote && world.getBlockState(pos).getMaterial() == Material.ROCK && ScaryNightHandler.isNightTime(world)) {
			SoundEvent soundEvent = Utils.getRandomElementFromList(BlockHauntedPumpkin.SOUNDS);
			if(soundEvent != null) if(world.rand.nextDouble() < 0.1D) world.playSound(null, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, soundEvent, SoundCategory.AMBIENT,30, 10);
		}
	}
}
