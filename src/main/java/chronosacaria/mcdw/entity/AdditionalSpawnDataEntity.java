package chronosacaria.mcdw.entity;

import net.minecraft.network.PacketByteBuf;

public interface AdditionalSpawnDataEntity {
    void writeToBuffer(PacketByteBuf buffer);

    void readFromBuffer(PacketByteBuf buffer);
}
