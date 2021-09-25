package com.warmthdawn.mod.gugu_utils.asm.transformers;

import com.warmthdawn.mod.gugu_utils.asm.common.MyTransformer;
import com.warmthdawn.mod.gugu_utils.asm.utils.AsmUtils;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.ListIterator;
import java.util.Optional;

public class ActiveMachineRecipeTransformer implements MyTransformer {
    @Override
    public void transform(ClassNode classNode) {
        Optional<MethodNode> tick = AsmUtils.findMethod(classNode, "tick", "(Lhellfirepvp/modularmachinery/common/tiles/TileMachineController;Lhellfirepvp/modularmachinery/common/crafting/helper/RecipeCraftingContext;)Lhellfirepvp/modularmachinery/common/tiles/TileMachineController$CraftingStatus;");
        if (!tick.isPresent())
            return;

        InsnList ins = tick.get().instructions;
        ListIterator<AbstractInsnNode> inserator = ins.iterator();
        while (inserator.hasNext()) {
            AbstractInsnNode in = inserator.next();

            if (!AsmUtils.matchFieldInsn(in, Opcodes.PUTFIELD, "tick",
                "hellfirepvp/modularmachinery/common/crafting/ActiveMachineRecipe", "I")) {
                continue;
            }

            AbstractInsnNode previous = in.getPrevious();
            if (!(previous.getOpcode() == Opcodes.ICONST_0)) {
                continue;
            }

            ins.remove(previous);
            InsnList hook = new InsnList();
            hook.add(new VarInsnNode(Opcodes.ALOAD, 0));
            FieldInsnNode getFieldTick = new FieldInsnNode(Opcodes.GETFIELD,
                "hellfirepvp/modularmachinery/common/crafting/ActiveMachineRecipe",
                "tick", "I");
            hook.add(getFieldTick);
            hook.add(new VarInsnNode(Opcodes.ALOAD, 0));
            FieldInsnNode getFieldRecipe = new FieldInsnNode(Opcodes.GETFIELD,
                "hellfirepvp/modularmachinery/common/crafting/ActiveMachineRecipe",
                "recipe", "Lhellfirepvp/modularmachinery/common/crafting/MachineRecipe;");
            hook.add(getFieldRecipe);
            MethodInsnNode call = new MethodInsnNode(Opcodes.INVOKESTATIC,
                "com/warmthdawn/mod/gugu_utils/asm/mixin/MixinModularMachinery",
                "inject_tick_onFailure", "(ILhellfirepvp/modularmachinery/common/crafting/MachineRecipe;)I", false);
            hook.add(call);
            ins.insertBefore(in, hook);
            return;

        }
    }
}
