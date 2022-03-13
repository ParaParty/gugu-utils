package com.warmthdawn.mod.gugu_utils.psi;

import com.warmthdawn.mod.gugu_utils.config.GuGuUtilsConfig;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellPiece;
import vazkii.psi.common.lib.LibPieceGroups;
import vazkii.psi.common.spell.base.ModSpellPieces;

public class PsiCompact {
    private static final String TRICK_ACCELERATE = "trickAccelerate";
    private static ModSpellPieces.PieceContainer trickAccelerate;


    public static void initSpell() {
        if (GuGuUtilsConfig.Tweaks.ENABLE_ACCELATE_TRICK)
            trickAccelerate = register(PieceTrickAccelerate.class, TRICK_ACCELERATE, LibPieceGroups.EIDOS_REVERSAL);
    }

    public static ModSpellPieces.PieceContainer register(Class<? extends SpellPiece> clazz, String name, String group) {
        return register(clazz, name, group, false);
    }

    public static ModSpellPieces.PieceContainer register(Class<? extends SpellPiece> clazz, String name, String group, boolean main) {
        PsiAPI.registerSpellPieceAndTexture(name, clazz);
        PsiAPI.addPieceToGroup(clazz, group, main);
        return (Spell s) -> SpellPiece.create(clazz, s);
    }

    public interface PieceContainer {
        SpellPiece get(Spell s);
    }
}
