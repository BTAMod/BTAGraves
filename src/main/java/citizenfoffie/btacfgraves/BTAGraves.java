package citizenfoffie.btacfgraves;

import citizenfoffie.btacfgraves.block.BlockGrave;
import citizenfoffie.btacfgraves.tileEntities.TitleEntityGraveChest;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSounds;
import net.minecraft.server.net.handler.NetServerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.GameStartEntrypoint;
import org.useless.dragonfly.helper.ModelHelper;
import org.useless.dragonfly.model.block.BlockModelDragonFly;

import java.util.Properties;

public class BTAGraves implements GameStartEntrypoint {
    public static final String MOD_ID = "btacfgraves";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ConfigHandler config;
	static {
		// Config
		Properties prop = new Properties();
		prop.setProperty("ids.graveID", "2760");

		config = new ConfigHandler(MOD_ID, prop);
	}
	public static final Block grave = new BlockBuilder(MOD_ID)
		.setBlockSound(BlockSounds.STONE)
		.setHardness(2.5f)
		.setTags(BlockTags.MINEABLE_BY_PICKAXE)
		.setBlockModel(block -> new BlockModelDragonFly(block, ModelHelper.getOrCreateBlockModel(MOD_ID, "grave.json"), null, null, false, 0.25f))
		.setTextures("minecraft:block/cobbled_stone_mossy")
		.build(new BlockGrave("grave",config.getInt("ids.graveID"), Material.stone));

	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {
		EntityHelper.createTileEntity(TitleEntityGraveChest.class, "Grave");
	}
	public static void logNetwork(String message){ // Might fix some weird class missing crash
		NetServerHandler.logger.info(message);
	}
}
