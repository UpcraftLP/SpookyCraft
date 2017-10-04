package mod.upcraftlp.spookycraft.entity;

import java.awt.Color;

import mod.upcraftlp.spookycraft.Main;
import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.entity.different.EntityMirror;
import mod.upcraftlp.spookycraft.entity.monster.EntityScareCrow;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
	public static void init() {
		int egg = new Color(254, 85, 176).getRGB();

		// Every entity in our mod has an ID (local to this mod)

		//Scare Crow
		EntityRegistry.registerModEntity(getEntityResource("ScareCrow"), EntityScareCrow.class, "ScareCrow", 0,
				Main.INSTANCE, 80, 3, false, 956291, egg);
		EntityRegistry.registerModEntity(getEntityResource("Mirror"), EntityMirror.class, "Mirror", 1,
				Main.INSTANCE, 80, 3, false, 956291, egg);
		
		/*
		 * We want our mob to spawn in Plains and ice plains biomes. If you
		 * don't add this then it will not spawn automatically but you can of
		 * course still make it spawn manually
		 */

		//Scare Crow
		EntityRegistry.addSpawn(EntityScareCrow.class, 100, 1, 1, EnumCreatureType.MONSTER, Biomes.PLAINS);
	
		/*
		 * Mob Placement
		 */

		// Scare Crow
		EntitySpawnPlacementRegistry.setPlacementType(EntityScareCrow.class, SpawnPlacementType.ON_GROUND);
		EntitySpawnPlacementRegistry.setPlacementType(EntityMirror.class, SpawnPlacementType.ON_GROUND);
		
		/*
		 * This is the loot table for our mob
		 */

		// Scare Crow
		//LootTableList.register(EntitySharkFemale.LOOT_FEMALESHARK);
		
	}

	private static ResourceLocation getEntityResource(String entityName) {
		return new ResourceLocation(Reference.MODID, entityName);
	}
}
