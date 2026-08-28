package tanukibear.ragdollscosmeticarmorfix.mixin;

import dev.leo.sableplayerragdoll.block.entity.RagdollPartBlockEntity;
import dev.leo.sableplayerragdoll.neoforge.client.RagdollPartBlockEntityRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tanukibear.ragdollscosmeticarmorfix.client.CosmeticArmorResolver;

@Mixin(value = RagdollPartBlockEntityRenderer.class, remap = false)
public abstract class RagdollPartBlockEntityRendererMixin {
    @Redirect(
            method = {"renderLayers", "renderEntity", "renderCape"},
            at = @At(
                    value = "INVOKE",
                    target = "Ldev/leo/sableplayerragdoll/block/entity/RagdollPartBlockEntity;itemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"
            ),
            remap = false
    )
    private ItemStack ragdollscosmeticarmorfix$resolveDisplayedArmor(
            RagdollPartBlockEntity blockEntity,
            EquipmentSlot slot
    ) {
        var actual = blockEntity.itemBySlot(slot);
        return CosmeticArmorResolver.resolve(blockEntity.skinProfile().getId(), slot, actual);
    }
}
