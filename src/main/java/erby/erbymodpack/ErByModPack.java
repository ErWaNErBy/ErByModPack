package erby.erbymodpack;

import erby.erbymodpack.lists.BlockList;
import erby.erbymodpack.lists.ItemList;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("erbymodpack")	//annotation which tells in forge that this is a mod with in parameter the mod ID
public class ErByModPack 
{
	public static ErByModPack instance;
	public static final String modid = "erbymodpack";	//variable for the mod ID
	private static final Logger logger = LogManager.getLogger(modid); //this logger can be use to print things out in the console and it will display any messages that we want to confirm that things are working correctly

	public static final ItemGroup erbyitemgroup = new ErByItemGroup();

	public ErByModPack()
	{
		instance = this;	//the way of referencing the main mod class outside of the main mod class
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);				//when mod is loading it will listen for everything inside of setup function
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);	//when mod is loading it will listen for everything inside of clientRegistries function
		MinecraftForge.EVENT_BUS.register(this);												//all the forge function will run on the mod
	}
	
	private void setup(final FMLCommonSetupEvent event)
	{
		logger.info("Setup method registered.");	//exemple of logger in setup
	}
	
	private void clientRegistries(final FMLClientSetupEvent event)
	{
		logger.info("clientRegistries method registered.");	//exemple of logger in clientRegistries
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class registryEvents		//to register our items
	{
		@SubscribeEvent 	//every function with SubscribeEvent is run on launch of the game at the same time that setup and client registries functions
		public static void registerItems(final RegistryEvent.Register<Item> event)	//this class for our items RegistryEvent
		{
			event.getRegistry().registerAll(
					//Items
				ItemList.ruby = new Item(new Item.Properties().group(erbyitemgroup)).setRegistryName(location("ruby")), //properties are information about the item // group is Item group in creative
				ItemList.solid_stick = new Item(new Item.Properties().group(erbyitemgroup)).setRegistryName(location("solid_stick")),
				ItemList.iron_stick = new Item(new Item.Properties().group(erbyitemgroup)).setRegistryName(location("iron_stick")),
				ItemList.gold_stick = new Item(new Item.Properties().group(erbyitemgroup)).setRegistryName(location("gold_stick")),
				ItemList.diamond_stick = new Item(new Item.Properties().group(erbyitemgroup)).setRegistryName(location("diamond_stick")),

					//Blocks Items
				ItemList.black_stone = new ItemBlock(BlockList.black_stone, new Item.Properties().group(erbyitemgroup)).setRegistryName(BlockList.black_stone.getRegistryName())
			);
			logger.info("Items registered.");
		}

		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event)	//this class for our blocks RegistryEvent
		{
			event.getRegistry().registerAll(
						//Blocks
				BlockList.black_stone = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(75.0f,35.0f).lightValue(0).sound(SoundType.METAL)).setRegistryName(location("black_stone")) //properties are information about the block // group is Block group in creative

			);
			logger.info("Blocks registered.");
		}

		private static ResourceLocation location(String name) //a function to symplify the resource location
		{
			return new ResourceLocation(modid, name);
		}
	}
}
