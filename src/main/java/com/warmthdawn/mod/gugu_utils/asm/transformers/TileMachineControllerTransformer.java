package com.warmthdawn.mod.gugu_utils.asm.transformers;

import com.warmthdawn.mod.gugu_utils.asm.common.MyTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.ListIterator;

public class TileMachineControllerTransformer implements MyTransformer {
    @Override
    public void transform(ClassNode classNode) {
        for(MethodNode method : classNode.methods) {
            if(method.name.equals("tryColorize")) {
                InsnList ins = method.instructions;
                ListIterator<AbstractInsnNode> inserator = ins.iterator();
                while(inserator.hasNext()) {
                    AbstractInsnNode in = inserator.next();

                    if(in instanceof MethodInsnNode) {
                        if(!(in.getOpcode() == Opcodes.INVOKEVIRTUAL)) {
                            continue;
                        }
                        MethodInsnNode insnNode = (MethodInsnNode) in;
                        if(!("getTileEntity".equals(insnNode.name))) {
                            continue;
                        }

                        InsnList hook = new InsnList();
                        hook.add(new VarInsnNode(Opcodes.ALOAD, 0));
                        hook.add(new VarInsnNode(Opcodes.ALOAD, 1));
                        hook.add(new VarInsnNode(Opcodes.ILOAD, 2));
                        hook.add(new VarInsnNode(Opcodes.ALOAD, 3));
                        MethodInsnNode call = new MethodInsnNode(Opcodes.INVOKESTATIC, "com/warmthdawn/mod/gugu_utils/asm/mixin/MixinModularMachinery", "inject_tryColorize", "(Lnet/minecraft/tileentity/TileEntity;Lnet/minecraft/util/math/BlockPos;ILnet/minecraft/tileentity/TileEntity;)V", false);
                        hook.add(call);

                        ins.insert(in.getNext(), hook);
                        return;
                    }
                }
            }
        }
    }
}
