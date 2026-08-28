package tanukibear.ragdollscosmeticarmorfix.client;

import lain.mods.cos.api.CosArmorAPI;
import lain.mods.cos.impl.ModConfigs;
import lain.mods.cos.impl.client.PlayerRenderHandler;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public final class CosmeticArmorResolver {
    private CosmeticArmorResolver() {
    }

    public static ItemStack resolve(UUID playerId, EquipmentSlot slot, ItemStack actual) {
        var index = slotIndex(slot);
        if (playerId == null || index < 0) {
            return actual;
        }

        var inventory = CosArmorAPI.getCAStacksClient(playerId);
        var cosmetic = inventory.getStackInSlot(index);
        return switch (sourceFor(
                PlayerRenderHandler.Disabled,
                inventory.isSkinArmor(index),
                ModConfigs.getCosArmorStackRendering(),
                !cosmetic.isEmpty()
        )) {
            case ACTUAL -> actual;
            case COSMETIC -> cosmetic;
            case HIDDEN -> ItemStack.EMPTY;
        };
    }

    static DisplaySource sourceFor(boolean disabled, boolean hidden, boolean stackRendering, boolean hasCosmetic) {
        if (disabled) {
            return DisplaySource.ACTUAL;
        }
        if (hidden) {
            return DisplaySource.HIDDEN;
        }
        if (stackRendering) {
            return DisplaySource.ACTUAL;
        }
        return hasCosmetic ? DisplaySource.COSMETIC : DisplaySource.ACTUAL;
    }

    static int slotIndex(EquipmentSlot slot) {
        return switch (slot) {
            case HEAD -> 3;
            case CHEST -> 2;
            case LEGS -> 1;
            case FEET -> 0;
            default -> -1;
        };
    }

    enum DisplaySource {
        ACTUAL,
        COSMETIC,
        HIDDEN
    }
}
