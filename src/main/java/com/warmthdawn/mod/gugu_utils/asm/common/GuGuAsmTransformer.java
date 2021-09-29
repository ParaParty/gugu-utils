package com.warmthdawn.mod.gugu_utils.asm.common;


import com.warmthdawn.mod.gugu_utils.asm.GuGuUtilsCore;
import com.warmthdawn.mod.gugu_utils.asm.transformers.ActiveMachineRecipeTransformer;
import com.warmthdawn.mod.gugu_utils.asm.transformers.DynamicMachineDeserializerTransformer;
import com.warmthdawn.mod.gugu_utils.asm.transformers.TileMachineControllerTransformer;
import com.warmthdawn.mod.gugu_utils.asm.utils.SafeClassWriter;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.util.HashMap;
import java.util.Map;

public class GuGuAsmTransformer implements IClassTransformer {
    private static final Map<String, MyTransformer> tweakedClasses = new HashMap<>();

    static {
        tweakedClasses.put("hellfirepvp.modularmachinery.common.tiles.TileMachineController", new TileMachineControllerTransformer());
        tweakedClasses.put("hellfirepvp.modularmachinery.common.crafting.ActiveMachineRecipe", new ActiveMachineRecipeTransformer());
        tweakedClasses.put("hellfirepvp.modularmachinery.common.machine.DynamicMachine$MachineDeserializer", new DynamicMachineDeserializerTransformer());
    }



    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (!tweakedClasses.containsKey(transformedName)) {
            return basicClass;
        }

        GuGuUtilsCore.logger.info("Transforming: " + transformedName);
        try {
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(basicClass);
            classReader.accept(classNode, 0);

            tweakedClasses.getOrDefault(transformedName, MyTransformer.EMPTY_TRANSFORMER).transform(classNode);

            ClassWriter classWriter = new SafeClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            classNode.accept(classWriter);
            return classWriter.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return basicClass;
    }
}
