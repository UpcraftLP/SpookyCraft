package mod.upcraftlp.spookycraft.init;

import java.awt.Color;

import mod.upcraftlp.spookycraft.Main;
import mod.upcraftlp.spookycraft.ModConfig;
import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.entity.different.EntityMirror;
import mod.upcraftlp.spookycraft.entity.different.EntitySkeletalLlamaSpit;
import mod.upcraftlp.spookycraft.entity.monster.EntityScareCrow;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalBat;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalChicken;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalCow;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalLlama;
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
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class SpookyEntities {

	private static final int eggPrimary = new Color(0x000000).getRGB();
	private static final int eggSecondary = new Color(0x974F1E).getRGB();
	private static int entityID = 0;

	public static void init() {
		EntityRegistry.registerModEntity(getEntityResource("ScareCrow"), EntityScareCrow.class, "ScareCrow", entityID++, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		//FIXME re-enable once finished!
		//EntityRegistry.registerModEntity(getEntityResource("Mirror"), EntityMirror.class, "Mirror", entityID++, Main.INSTANCE, 80, 3, false, eggPrimary, egg);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalBat"), EntitySkeletalBat.class, "SkeletalBat", entityID++, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalChicken"), EntitySkeletalChicken.class, "SkeletalChicken", entityID++, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalCow"), EntitySkeletalCow.class, "SkeletalCow", entityID++, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalParrot"), EntitySkeletalParrot.class, "SkeletalParrot", entityID++, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalPig"), EntitySkeletalPig.class, "SkeletalPig", entityID++, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalWolf"), EntitySkeletalWolf.class, "SkeletalWolf", entityID++, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalRabbit"), EntitySkeletalRabbit.class, "SkeletalRabbit", entityID++, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalSheep"), EntitySkeletalSheep.class, "SkeletalSheep", entityID++, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalOcelot"), EntitySkeletalOcelot.class, "SkeletalOcelot", entityID++, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		EntityRegistry.registerModEntity(getEntityResource("SkeletalLlama"), EntitySkeletalLlama.class, "SkeletalLlama", entityID++, Main.INSTANCE, 80, 3, false, eggPrimary, eggSecondary);
		EntityRegistry.registerModEntity(getEntityResource("LlamaSpit"), EntitySkeletalLlamaSpit.class, "LlamaSpit", entityID++, Main.INSTANCE, 80, 3, false);

		/*
		 * We want our mob to spawn in biomes. If you don't add this then it
		 * will not spawn automatically but you can of course still make it
		 * spawn manually
		 */
		EntityRegistry.addSpawn(EntityScareCrow.class, ModConfig.mobSpawns.scareCrow, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		EntityRegistry.addSpawn(EntitySkeletalBat.class, ModConfig.mobSpawns.bat, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		EntityRegistry.addSpawn(EntitySkeletalChicken.class, ModConfig.mobSpawns.chicken, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		EntityRegistry.addSpawn(EntitySkeletalCow.class, ModConfig.mobSpawns.cow, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		EntityRegistry.addSpawn(EntitySkeletalParrot.class, ModConfig.mobSpawns.parrot, 1, 1, EnumCreatureType.MONSTER, Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.MUTATED_JUNGLE, Biomes.MUTATED_JUNGLE_EDGE);
		EntityRegistry.addSpawn(EntitySkeletalPig.class, ModConfig.mobSpawns.pig, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		EntityRegistry.addSpawn(EntitySkeletalWolf.class, ModConfig.mobSpawns.wolf, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		EntityRegistry.addSpawn(EntitySkeletalRabbit.class, ModConfig.mobSpawns.rabbit, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		EntityRegistry.addSpawn(EntitySkeletalOcelot.class, ModConfig.mobSpawns.ocelot, 1, 4, EnumCreatureType.MONSTER, Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.MUTATED_JUNGLE, Biomes.MUTATED_JUNGLE_EDGE);
		EntityRegistry.addSpawn(EntitySkeletalSheep.class, ModConfig.mobSpawns.sheep, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		EntityRegistry.addSpawn(EntitySkeletalLlama.class, ModConfig.mobSpawns.llama, 1, 1, EnumCreatureType.MONSTER, Biomes.SAVANNA);

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
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalLlama.class, SpawnPlacementType.ON_GROUND);

		/*
		 * This is the loot table for our mob
		 * 
		 * LootTableList.register(EntityScareCrow.LOOT_CROW);
		 * LootTableList.register(EntitySkeletal.LOOT_SKELETAL);
		 */
	}

	private static ResourceLocation getEntityResource(String entityName) {
		return new ResourceLocation(Reference.MODID, entityName);
	}
}
