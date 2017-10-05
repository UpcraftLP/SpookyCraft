package mod.upcraftlp.spookycraft.handler;

import mod.upcraftlp.spookycraft.block.BlockHauntedPumpkin;
import mod.upcraftlp.spookycraft.init.SpookyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.BlockStone;
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
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		EntityPlayer player = event.getPlayer();
		ItemStack stack = player.getActiveItemStack();
		ScaryNightHandler handler = new ScaryNightHandler();
		if (event.getWorld().getBlockState(pos).getBlock() instanceof BlockStone ) {

			if (!world.isRemote && handler.isNightTime(world)) {
				System.out.println("fired");
				world.playSound(player, player.posX, player.posY, player.posZ, SoundHandler.scarecrow_ambient.sound(), SoundCategory.AMBIENT,30, 10);
				player.playSound(SoundHandler.scarecrow_ambient.sound(), 30, 10);
			}
		}
	}
}
