package mod.upcraftlp.spookycraft.entity;

import java.awt.Color;

import mod.upcraftlp.spookycraft.Reference;
import net.minecraft.util.ResourceLocation;

public class ModEntities {
	public static void init() {
		int femalesharkegg = new Color(254, 85, 176).getRGB();

		// Every entity in our mod has an ID (local to this mod)

		// FemaleShark
		//EntityRegistry.registerModEntity(getEntityResource("FemaleShark"), EntitySharkFemale.class, "FemaleShark", 0,
			//	Raft.instance, 80, 3, false, 956291, femalesharkegg);
		
		/*
		 * We want our mob to spawn in Plains and ice plains biomes. If you
		 * don't add this then it will not spawn automatically but you can of
		 * course still make it spawn manually
		 */

		// Female Shark
		//EntityRegistry.addSpawn(EntitySharkFemale.class, Reference.Shark_Spawn_Rate, Reference.Shark_Spawn_MIN_Amount, Reference.Shark_Spawn_MAX_Amount, EnumCreatureType.WATER_CREATURE, Biomes.DEEP_OCEAN,
			//	Biomes.OCEAN, Biomes.FROZEN_OCEAN, Biomes.RIVER, Biomes.FROZEN_RIVER);
	
		/*
		 * Mob Placement
		 */

		// Female Shark
		//EntitySpawnPlacementRegistry.setPlacementType(EntitySharkFemale.class, SpawnPlacementType.IN_WATER);
		
		/*
		 * This is the loot table for our mob
		 */

		// Female Shark
		//LootTableList.register(EntitySharkFemale.LOOT_FEMALESHARK);
		
	}

	private static ResourceLocation getEntityResource(String entityName) {
		return new ResourceLocation(Reference.MODID, entityName);
	}
}
