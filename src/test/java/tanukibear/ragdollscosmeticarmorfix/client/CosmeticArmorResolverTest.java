package tanukibear.ragdollscosmeticarmorfix.client;

import net.minecraft.world.entity.EquipmentSlot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tanukibear.ragdollscosmeticarmorfix.client.CosmeticArmorResolver.DisplaySource.ACTUAL;
import static tanukibear.ragdollscosmeticarmorfix.client.CosmeticArmorResolver.DisplaySource.COSMETIC;
import static tanukibear.ragdollscosmeticarmorfix.client.CosmeticArmorResolver.DisplaySource.HIDDEN;

class CosmeticArmorResolverTest {
    @Test
    void globalDisableKeepsActualArmor() {
        assertEquals(ACTUAL, CosmeticArmorResolver.sourceFor(true, true, false, true));
    }

    @Test
    void hiddenSlotSuppressesArmorEvenInStackRenderingMode() {
        assertEquals(HIDDEN, CosmeticArmorResolver.sourceFor(false, true, true, true));
    }

    @Test
    void stackRenderingKeepsActualArmorForTheCosmeticLayerToAppendTo() {
        assertEquals(ACTUAL, CosmeticArmorResolver.sourceFor(false, false, true, true));
    }

    @Test
    void normalModeUsesCosmeticArmorWhenPresent() {
        assertEquals(COSMETIC, CosmeticArmorResolver.sourceFor(false, false, false, true));
    }

    @Test
    void normalModeFallsBackToActualArmorWhenCosmeticSlotIsEmpty() {
        assertEquals(ACTUAL, CosmeticArmorResolver.sourceFor(false, false, false, false));
    }

    @Test
    void mapsVanillaArmorSlotsToCosmeticArmorIndices() {
        assertEquals(3, CosmeticArmorResolver.slotIndex(EquipmentSlot.HEAD));
        assertEquals(2, CosmeticArmorResolver.slotIndex(EquipmentSlot.CHEST));
        assertEquals(1, CosmeticArmorResolver.slotIndex(EquipmentSlot.LEGS));
        assertEquals(0, CosmeticArmorResolver.slotIndex(EquipmentSlot.FEET));
        assertEquals(-1, CosmeticArmorResolver.slotIndex(EquipmentSlot.MAINHAND));
    }
}
