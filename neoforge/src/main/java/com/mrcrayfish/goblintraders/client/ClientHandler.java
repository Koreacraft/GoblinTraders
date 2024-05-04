package com.mrcrayfish.goblintraders.client;

import com.mrcrayfish.goblintraders.Constants;
import com.mrcrayfish.goblintraders.client.renderer.entity.GoblinModelLayers;
import com.mrcrayfish.goblintraders.client.renderer.entity.GoblinTraderRenderer;
import com.mrcrayfish.goblintraders.client.renderer.entity.model.GoblinTraderModel;
import com.mrcrayfish.goblintraders.core.ModEntities;
import com.mrcrayfish.goblintraders.core.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

/**
 * Author: MrCrayfish
 */
@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientHandler
{
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(ModEntities.GOBLIN_TRADER.get(), GoblinTraderRenderer::new);
        event.registerEntityRenderer(ModEntities.VEIN_GOBLIN_TRADER.get(), GoblinTraderRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(GoblinModelLayers.GOBLIN_TRADER, GoblinTraderModel::createBodyLayer);
        event.registerLayerDefinition(GoblinModelLayers.VEIN_GOBLIN_TRADER, GoblinTraderModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterCreativeTab(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey().equals(CreativeModeTabs.SPAWN_EGGS))
        {
            event.accept(ModItems.GOBLIN_TRADER_SPAWN_EGG.get());
            event.accept(ModItems.VEIN_GOBLIN_TRADER_SPAWN_EGG.get());
        }
    }
}
