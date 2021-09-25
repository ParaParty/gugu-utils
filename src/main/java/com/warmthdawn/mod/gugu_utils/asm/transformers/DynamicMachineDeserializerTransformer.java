package com.warmthdawn.mod.gugu_utils.asm.transformers;

import com.warmthdawn.mod.gugu_utils.asm.common.MyTransformer;
import com.warmthdawn.mod.gugu_utils.asm.utils.AsmUtils;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.Optional;

public class DynamicMachineDeserializerTransformer implements MyTransformer {
    @Override
    public void transform(ClassNode classNode) {
        Optional<MethodNode> deserialize = AsmUtils.findMethod(classNode, "deserialize", null);
        if (!deserialize.isPresent())
            return;
        InsnList instructions = deserialize.get().instructions;
        InsnList hook = new InsnList();
        hook.add(new VarInsnNode(Opcodes.ALOAD, 1));
        MethodInsnNode call = new MethodInsnNode(Opcodes.INVOKESTATIC,
            "com/warmthdawn/mod/gugu_utils/asm/mixin/MixinModularMachinery",
            "inject_machineRecipe_deserialize", "(Lcom/google/gson/JsonElement;)V", false);
        hook.add(call);
        instructions.insert(hook);
    }
}
