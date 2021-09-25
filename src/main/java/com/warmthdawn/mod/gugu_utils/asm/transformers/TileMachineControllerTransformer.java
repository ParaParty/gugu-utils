package com.warmthdawn.mod.gugu_utils.asm.transformers;

import com.warmthdawn.mod.gugu_utils.asm.common.MyTransformer;
import com.warmthdawn.mod.gugu_utils.asm.utils.AsmUtils;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.ListIterator;
import java.util.Optional;

public class TileMachineControllerTransformer implements MyTransformer {
    @Override
    public void transform(ClassNode classNode) {
        Optional<MethodNode> tryColorize = AsmUtils.findMethod(classNode, "tryColorize", null);
        if (!tryColorize.isPresent())
            return;

        InsnList ins = tryColorize.get().instructions;
        ListIterator<AbstractInsnNode> inserator = ins.iterator();
        while (inserator.hasNext()) {
            AbstractInsnNode in = inserator.next();

            if (!AsmUtils.matchMethodInsn(in, Opcodes.INVOKEVIRTUAL, "getTileEntity", null, null, null)) {
                continue;
            }
            InsnList hook = new InsnList();
            hook.add(new VarInsnNode(Opcodes.ALOAD, 0));
            hook.add(new VarInsnNode(Opcodes.ALOAD, 1));
            hook.add(new VarInsnNode(Opcodes.ILOAD, 2));
            hook.add(new VarInsnNode(Opcodes.ALOAD, 3));
            MethodInsnNode call = new MethodInsnNode(Opcodes.INVOKESTATIC,
                "com/warmthdawn/mod/gugu_utils/asm/mixin/MixinModularMachinery",
                "inject_tryColorize",
                "(Lnet/minecraft/tileentity/TileEntity;Lnet/minecraft/util/math/BlockPos;ILnet/minecraft/tileentity/TileEntity;)V", false);
            hook.add(call);

            ins.insert(in.getNext(), hook);
            return;

        }
    }
}
