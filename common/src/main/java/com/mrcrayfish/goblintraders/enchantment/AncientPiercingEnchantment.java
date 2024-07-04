package com.mrcrayfish.goblintraders.enchantment;

import com.mrcrayfish.goblintraders.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ArrowPiercingEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

/**
 * Author: MrCrayfish
 */
public class AncientPiercingEnchantment extends ArrowPiercingEnchantment implements IAncientEnchantment
{
    private final Enchantment original;

    public AncientPiercingEnchantment(EnchantmentDefinition definition, Enchantment original)
    {
        super(definition);
        this.original = original;
    }

    @Override
    public boolean checkCompatibility(Enchantment enchantment)
    {
        return super.checkCompatibility(enchantment) && enchantment != Enchantments.MULTISHOT && enchantment != this.original;
    }

    @Override
    public Component getFullname(int level)
    {
        Component name = Component.translatable(this.original.getDescriptionId());
        return Component.translatable("enchantment.goblintraders.ancient", name)
            .withStyle(ChatFormatting.LIGHT_PURPLE);
    }

    @Override
    public boolean isTreasureOnly()
    {
        return Config.SERVER.ancientEnchantments.treasureOnly.get();
    }

    @Override
    public boolean isDiscoverable()
    {
        return !Config.SERVER.ancientEnchantments.goblinsOnly.get();
    }

    @Override
    public boolean isTradeable()
    {
        return !Config.SERVER.ancientEnchantments.goblinsOnly.get() && !Config.SERVER.ancientEnchantments.treasureOnly.get();
    }

    @Override
    public Enchantment getAncientEnchantment()
    {
        return this;
    }

    @Override
    public Enchantment getOriginal()
    {
        return this.original;
    }

    @Override
    public int getAncientLevel(int level)
    {
        return this.original.getMaxLevel() + level * Config.SERVER.ancientEnchantments.bonusLevels.get();
    }

    // For Forge (yes, the method is spelled wrong on purpose)
    //@Override
    public boolean canApplyAtEnchatingTable(ItemStack stack)
    {
        return this.isDiscoverable();
    }

    // For NeoForge
    //@Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return this.canApplyAtEnchatingTable(stack);
    }
}
