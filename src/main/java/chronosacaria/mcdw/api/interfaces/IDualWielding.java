/*
Timefall Development License 1.2
Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.

This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
*/
package chronosacaria.mcdw.api.interfaces;

public interface IDualWielding {

    float mcdw$getOffhandAttackCooldownProgressPerTick();

    float mcdw$getOffhandAttackCooldownProgress(float baseTime);

    void mcdw$resetLastAttackedOffhandTicks();

    int mcdw$getOffhandAttackedTicks();

    void mcdw$setOffhandAttackedTicks(int lastAttackedOffhandTicks);
}
