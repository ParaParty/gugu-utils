package com.warmthdawn.mod.gugu_utils.asm.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.*;

import java.util.Optional;

public final class AsmUtils {

    public static boolean matchMethod(@NotNull MethodNode method, @NotNull String name, @Nullable String desc) {
        return (name.equals(method.name)) &&
            (desc == null || desc.equals(method.desc));
    }

    public static Optional<MethodNode> findMethod(@NotNull ClassNode classNode, @NotNull String name, @Nullable String desc) {
        for (MethodNode method : classNode.methods) {
            if (matchMethod(method, name, desc)) {
                return Optional.of(method);
            }
        }
        return Optional.empty();
    }

    public static boolean matchMethodInsn(@NotNull AbstractInsnNode node, int opCode, @NotNull String name,
                                          @Nullable String owner, @Nullable String desc, @Nullable Boolean itf) {

        return matchMethodInsn(node, opCode, name, null, owner, desc, itf);
    }

    public static boolean matchMethodInsn(@NotNull AbstractInsnNode node, int opCode, @NotNull String name,
                                          @Nullable String srgName, @Nullable String owner, @Nullable String desc, @Nullable Boolean itf) {
        if (!(node instanceof MethodInsnNode))
            return false;
        MethodInsnNode methodInsnNode = (MethodInsnNode) node;
        return name.equals(methodInsnNode.name) ||
            (srgName != null && srgName.equals(methodInsnNode.name)) &&
            (opCode == methodInsnNode.getOpcode()) &&
            (owner == null || owner.equals(methodInsnNode.owner)) &&
            (desc == null || desc.equals(methodInsnNode.desc)) &&
            (itf == null || itf.equals(methodInsnNode.itf));
    }

    public static boolean matchFieldInsn(@NotNull AbstractInsnNode node, int opCode, @NotNull String name,
                                         @Nullable String owner, @Nullable String desc) {
        return matchFieldInsn(node, opCode, name, null, owner, desc);
    }

    public static boolean matchFieldInsn(@NotNull AbstractInsnNode node, int opCode, @NotNull String name,
                                         @Nullable String srgName, @Nullable String owner, @Nullable String desc) {
        if (!(node instanceof FieldInsnNode))
            return false;
        FieldInsnNode fieldInsnNode = (FieldInsnNode) node;
        return name.equals(fieldInsnNode.name)  ||
            (srgName != null && srgName.equals(fieldInsnNode.name)) &&
            (opCode == fieldInsnNode.getOpcode()) &&
            (owner == null || owner.equals(fieldInsnNode.owner)) &&
            (desc == null || desc.equals(fieldInsnNode.desc));
    }

}
