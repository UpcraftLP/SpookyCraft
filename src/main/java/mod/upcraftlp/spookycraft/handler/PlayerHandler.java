package mod.upcraftlp.spookycraft.handler;

import mod.upcraftlp.spookycraft.block.BlockHauntedPumpkin;
import mod.upcraftlp.spookycraft.init.SpookyBlocks;
import mod.upcraftlp.spookycraft.init.SpookySounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
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
	public static void onRightClickPumpkin(PlayerInteractEvent.RightClickBlock event) {
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		IBlockState state = world.getBlockState(pos);
		ItemStack stack = event.getItemStack();
		if (state.getBlock() == Blocks.PUMPKIN && stack.getItem() == Item.getItemFromBlock(Blocks.TORCH)) {
			stack.shrink(1);
			world.setBlockState(pos, SpookyBlocks.Special.HAUNTED_PUMPKIN.getDefaultState()
					.withProperty(BlockHauntedPumpkin.FACING, state.getValue(BlockPumpkin.FACING)));
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onBreakSpookyBlock(BlockEvent.BreakEvent event) {
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		if (!world.isRemote && world.getBlockState(pos).getMaterial() == Material.ROCK && ScaryNightHandler.isNightTime(world)) {
			if(world.rand.nextDouble() < 0.1D) world.playSound(null, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, SpookySounds.SCARECROW_AMBIENT, SoundCategory.AMBIENT,30, 10);
		}
	}
}
