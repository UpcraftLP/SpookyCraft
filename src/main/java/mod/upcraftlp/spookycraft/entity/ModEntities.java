package mod.upcraftlp.spookycraft.entity;

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

public class ModEntities {
	public static void init() {
		int egg = new Color(254, 85, 176).getRGB();

		// Every entity in our mod has an ID (local to this mod)

		// Scare Crow
		EntityRegistry.registerModEntity(getEntityResource("ScareCrow"), EntityScareCrow.class, "ScareCrow", 0,
				Main.INSTANCE, 80, 3, false, 956291, egg);
		// Mirror
		EntityRegistry.registerModEntity(getEntityResource("Mirror"), EntityMirror.class, "Mirror", 1, Main.INSTANCE,
				80, 3, false, 956291, egg);
		// S Bat
		EntityRegistry.registerModEntity(getEntityResource("SkeletalBat"), EntitySkeletalBat.class, "SkeletalBat", 2,
				Main.INSTANCE, 80, 3, false, 956291, egg);
		// S Chicken
		EntityRegistry.registerModEntity(getEntityResource("SkeletalChicken"), EntitySkeletalChicken.class,
				"SkeletalChicken", 3, Main.INSTANCE, 80, 3, false, 956291, egg);
		// S Cow
		EntityRegistry.registerModEntity(getEntityResource("SkeletalCow"), EntitySkeletalCow.class, "SkeletalCow", 4,
				Main.INSTANCE, 80, 3, false, 956291, egg);
		// S Parrot
		EntityRegistry.registerModEntity(getEntityResource("SkeletalParrot"), EntitySkeletalParrot.class,
				"SkeletalParrot", 5, Main.INSTANCE, 80, 3, false, 956291, egg);
		// S Pig
		EntityRegistry.registerModEntity(getEntityResource("SkeletalPig"), EntitySkeletalPig.class, "SkeletalPig", 6,
				Main.INSTANCE, 80, 3, false, 956291, egg);
		// S Wolf
		EntityRegistry.registerModEntity(getEntityResource("SkeletalWolf"), EntitySkeletalWolf.class, "SkeletalWolf", 7,
				Main.INSTANCE, 80, 3, false, 956291, egg);
		// S Rabitt
		EntityRegistry.registerModEntity(getEntityResource("SkeletalRabbit"), EntitySkeletalRabbit.class,
				"SkeletalRabbit", 8, Main.INSTANCE, 80, 3, false, 956291, egg);
		// S Sheep
		EntityRegistry.registerModEntity(getEntityResource("SkeletalSheep"), EntitySkeletalSheep.class, "SkeletalSheep",
				9, Main.INSTANCE, 80, 3, false, 956291, egg);
		// S Ocelot
		EntityRegistry.registerModEntity(getEntityResource("SkeletalOcelot"), EntitySkeletalOcelot.class,
				"SkeletalOcelot", 10, Main.INSTANCE, 80, 3, false, 956291, egg);

		/*
		 * We want our mob to spawn in Plains and ice plains biomes. If you
		 * don't add this then it will not spawn automatically but you can of
		 * course still make it spawn manually
		 */

		// Scare Crow
		EntityRegistry.addSpawn(EntityScareCrow.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		// S Bat
		EntityRegistry.addSpawn(EntitySkeletalBat.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		// S Chicken
		EntityRegistry.addSpawn(EntitySkeletalChicken.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		// S Cow
		EntityRegistry.addSpawn(EntitySkeletalCow.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		// S Parrot
		EntityRegistry.addSpawn(EntitySkeletalParrot.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.JUNGLE,
				Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.MUTATED_JUNGLE, Biomes.MUTATED_JUNGLE_EDGE);
		// S Pig
		EntityRegistry.addSpawn(EntitySkeletalPig.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		// S Wolf
		EntityRegistry.addSpawn(EntitySkeletalWolf.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		// S Rabbit
		EntityRegistry.addSpawn(EntitySkeletalRabbit.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
		// S Ocelot
		EntityRegistry.addSpawn(EntitySkeletalOcelot.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.JUNGLE,
				Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.MUTATED_JUNGLE, Biomes.MUTATED_JUNGLE_EDGE);
		// S Sheep
		EntityRegistry.addSpawn(EntitySkeletalSheep.class, 10, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);

		/*
		 * Mob Placement
		 */

		// Scare Crow
		EntitySpawnPlacementRegistry.setPlacementType(EntityScareCrow.class, SpawnPlacementType.ON_GROUND);
		// Mirror
		EntitySpawnPlacementRegistry.setPlacementType(EntityMirror.class, SpawnPlacementType.ON_GROUND);
		// S Bat
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalBat.class, SpawnPlacementType.IN_AIR);
		// S Chicken
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalChicken.class, SpawnPlacementType.ON_GROUND);
		// S Cow
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalCow.class, SpawnPlacementType.ON_GROUND);
		// S Parrot
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalParrot.class, SpawnPlacementType.IN_AIR);
		// S Pig
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalPig.class, SpawnPlacementType.ON_GROUND);
		// S Wolf
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalWolf.class, SpawnPlacementType.ON_GROUND);
		// S Ocelot
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalOcelot.class, SpawnPlacementType.ON_GROUND);
		// S Sheep
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalSheep.class, SpawnPlacementType.ON_GROUND);
		// S Rabbit
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletalRabbit.class, SpawnPlacementType.ON_GROUND);

		/*
		 * This is the loot table for our mob
		 */

		// Scare Crow
		LootTableList.register(EntityScareCrow.LOOT_CROW);
		// Skeletals
		LootTableList.register(EntitySkeletal.LOOT_SKELETAL);

	}

	private static ResourceLocation getEntityResource(String entityName) {
		return new ResourceLocation(Reference.MODID, entityName);
	}
}
