package mod.upcraftlp.spookycraft.init;

import java.awt.Color;

import mod.upcraftlp.spookycraft.Main;
import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.entity.different.EntityMirror;
import mod.upcraftlp.spookycraft.entity.monster.EntityScareCrow;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletal;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalBat;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalChicken;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalCow;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalOcelot;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalParrot;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalPig;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalRabbit;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalSheep;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalWolf;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class SpookyEntities {

	private static final int eggPrimary = new Color(0x000000).getRGB();
	private static final int eggSecondary = new Color(0x974F1E).getRGB();

	public static void init() {
		EntityRegistry.registerModEntity(getEntityResource("ScareCrow"), EntityScareCrow.class, "ScareCrow", 0, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		//FIXME re-enable once finished!
		//EntityRegistry.registerModEntity(getEntityResource("Mirror"), EntityMirror.class, "Mirror", 1, Main.INSTANCE, 80, 3, false, eggPrimary, egg);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalBat"), EntitySkeletalBat.class, "SkeletalBat", 2, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalChicken"), EntitySkeletalChicken.class, "SkeletalChicken", 3, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalCow"), EntitySkeletalCow.class, "SkeletalCow", 4, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalParrot"), EntitySkeletalParrot.class, "SkeletalParrot", 5, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalPig"), EntitySkeletalPig.class, "SkeletalPig", 6, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalWolf"), EntitySkeletalWolf.class, "SkeletalWolf", 7, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalRabbit"), EntitySkeletalRabbit.class, "SkeletalRabbit", 8, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalSheep"), EntitySkeletalSheep.class, "SkeletalSheep", 9, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalOcelot"), EntitySkeletalOcelot.class, "SkeletalOcelot", 10, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);

		/*
		 * We want our mob to spawn in biomes. If you
		 * don't add this then it will not spawn automatically but you can of
		 * course still make it spawn manually
		 */
		EntityRegistry.addSpawn(EntityScareCrow.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		EntityRegistry.addSpawn(EntitySkeletalBat.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		EntityRegistry.addSpawn(EntitySkeletalChicken.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		EntityRegistry.addSpawn(EntitySkeletalCow.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		EntityRegistry.addSpawn(EntitySkeletalParrot.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.MUTATED_JUNGLE, Biomes.MUTATED_JUNGLE_EDGE);
		EntityRegistry.addSpawn(EntitySkeletalPig.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		EntityRegistry.addSpawn(EntitySkeletalWolf.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		EntityRegistry.addSpawn(EntitySkeletalRabbit.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		EntityRegistry.addSpawn(EntitySkeletalOcelot.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.MUTATED_JUNGLE, Biomes.MUTATED_JUNGLE_EDGE);
		EntityRegistry.addSpawn(EntitySkeletalSheep.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);

		/*
		 * Mob Placement
		 */
		EntitySpawnPlacementRegistry.setPlacementType(EntityScareCrow.class, SpawnPlacementType.ON_GROUND);
		EntitySpawnPlacementRegistry.setPlacementType(EntityMirror.class, SpawnPlacementType.ON_GROUND);
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalBat.class, SpawnPlacementType.IN_AIR);
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalChicken.class, SpawnPlacementType.ON_GROUND);
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalCow.class, SpawnPlacementType.ON_GROUND);
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalParrot.class, SpawnPlacementType.IN_AIR);
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalPig.class, SpawnPlacementType.ON_GROUND);
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalWolf.class, SpawnPlacementType.ON_GROUND);
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalOcelot.class, SpawnPlacementType.ON_GROUND);
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalSheep.class, SpawnPlacementType.ON_GROUND);
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalRabbit.class, SpawnPlacementType.ON_GROUND);

		/*
		 * This is the loot table for our mob
		 
		LootTableList.register(EntityScareCrow.LOOT_CROW);
		LootTableList.register(EntitySkeletal.LOOT_SKELETAL);
*/
	}

	private static ResourceLocation getEntityResource(String entityName) {
		return new ResourceLocation(Reference.MODID, entityName);
	}
}
