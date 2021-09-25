package asm.com.warmthdawn.mod.gugu_utils.asm.mixin;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ConstantDynamic;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.RecordComponentVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.TypePath;

public class AMRTDump implements Opcodes {

    public static byte[] dump() throws Exception {

        ClassWriter classWriter = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        RecordComponentVisitor recordComponentVisitor;
        MethodVisitor methodVisitor;
        AnnotationVisitor annotationVisitor0;

        classWriter.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "com/warmthdawn/mod/gugu_utils/asm/mixin/AMRT", null, "java/lang/Object", null);

        classWriter.visitSource("AMRT.java", null);

        classWriter.visitInnerClass("hellfirepvp/modularmachinery/common/tiles/TileMachineController$CraftingStatus", "hellfirepvp/modularmachinery/common/tiles/TileMachineController", "CraftingStatus", ACC_PUBLIC | ACC_STATIC);

        classWriter.visitInnerClass("hellfirepvp/modularmachinery/common/crafting/helper/RecipeCraftingContext$CraftingCheckResult", "hellfirepvp/modularmachinery/common/crafting/helper/RecipeCraftingContext", "CraftingCheckResult", ACC_PUBLIC | ACC_STATIC);

        {
            fieldVisitor = classWriter.visitField(ACC_PRIVATE, "tick", "I", null, null);
            fieldVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(8, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(9, label1);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitFieldInsn(PUTFIELD, "com/warmthdawn/mod/gugu_utils/asm/mixin/AMRT", "tick", "I");
            methodVisitor.visitInsn(RETURN);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLocalVariable("this", "Lcom/warmthdawn/mod/gugu_utils/asm/mixin/AMRT;", null, label0, label2, 0);
            methodVisitor.visitMaxs(2, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "tick", "(Lhellfirepvp/modularmachinery/common/tiles/TileMachineController;Lhellfirepvp/modularmachinery/common/crafting/helper/RecipeCraftingContext;)Lhellfirepvp/modularmachinery/common/tiles/TileMachineController$CraftingStatus;", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(12, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitVarInsn(ALOAD, 2);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/warmthdawn/mod/gugu_utils/asm/mixin/AMRT", "isCompleted", "(Lhellfirepvp/modularmachinery/common/tiles/TileMachineController;Lhellfirepvp/modularmachinery/common/crafting/helper/RecipeCraftingContext;)Z", false);
            Label label1 = new Label();
            methodVisitor.visitJumpInsn(IFEQ, label1);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(13, label2);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "hellfirepvp/modularmachinery/common/tiles/TileMachineController$CraftingStatus", "working", "()Lhellfirepvp/modularmachinery/common/tiles/TileMachineController$CraftingStatus;", false);
            methodVisitor.visitInsn(ARETURN);
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(17, label1);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitVarInsn(ALOAD, 2);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, "com/warmthdawn/mod/gugu_utils/asm/mixin/AMRT", "tick", "I");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "hellfirepvp/modularmachinery/common/crafting/helper/RecipeCraftingContext", "ioTick", "(I)Lhellfirepvp/modularmachinery/common/crafting/helper/RecipeCraftingContext$CraftingCheckResult;", false);
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitVarInsn(ASTORE, 3);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "hellfirepvp/modularmachinery/common/crafting/helper/RecipeCraftingContext$CraftingCheckResult", "isFailure", "()Z", false);
            Label label4 = new Label();
            methodVisitor.visitJumpInsn(IFNE, label4);
            Label label5 = new Label();
            methodVisitor.visitLabel(label5);
            methodVisitor.visitLineNumber(18, label5);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitFieldInsn(GETFIELD, "com/warmthdawn/mod/gugu_utils/asm/mixin/AMRT", "tick", "I");
            methodVisitor.visitInsn(ICONST_1);
            methodVisitor.visitInsn(IADD);
            methodVisitor.visitFieldInsn(PUTFIELD, "com/warmthdawn/mod/gugu_utils/asm/mixin/AMRT", "tick", "I");
            Label label6 = new Label();
            methodVisitor.visitLabel(label6);
            methodVisitor.visitLineNumber(19, label6);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "hellfirepvp/modularmachinery/common/tiles/TileMachineController$CraftingStatus", "working", "()Lhellfirepvp/modularmachinery/common/tiles/TileMachineController$CraftingStatus;", false);
            methodVisitor.visitInsn(ARETURN);
            methodVisitor.visitLabel(label4);
            methodVisitor.visitLineNumber(21, label4);
            methodVisitor.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"hellfirepvp/modularmachinery/common/crafting/helper/RecipeCraftingContext$CraftingCheckResult"}, 0, null);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "com/warmthdawn/mod/gugu_utils/asm/mixin/MixinActiveMachineRecipe", "inject_tick_isReset", "()Z", false);
            Label label7 = new Label();
            methodVisitor.visitJumpInsn(IFEQ, label7);
            Label label8 = new Label();
            methodVisitor.visitLabel(label8);
            methodVisitor.visitLineNumber(22, label8);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitFieldInsn(PUTFIELD, "com/warmthdawn/mod/gugu_utils/asm/mixin/AMRT", "tick", "I");
            methodVisitor.visitLabel(label7);
            methodVisitor.visitLineNumber(24, label7);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitVarInsn(ALOAD, 3);
            Label label9 = new Label();
            methodVisitor.visitLabel(label9);
            methodVisitor.visitLineNumber(25, label9);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "hellfirepvp/modularmachinery/common/crafting/helper/RecipeCraftingContext$CraftingCheckResult", "getUnlocalizedErrorMessages", "()Ljava/util/List;", false);
            methodVisitor.visitLdcInsn("");
            methodVisitor.visitMethodInsn(INVOKESTATIC, "com/google/common/collect/Iterables", "getFirst", "(Ljava/lang/Iterable;Ljava/lang/Object;)Ljava/lang/Object;", false);
            methodVisitor.visitTypeInsn(CHECKCAST, "java/lang/String");
            Label label10 = new Label();
            methodVisitor.visitLabel(label10);
            methodVisitor.visitLineNumber(24, label10);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "hellfirepvp/modularmachinery/common/tiles/TileMachineController$CraftingStatus", "failure", "(Ljava/lang/String;)Lhellfirepvp/modularmachinery/common/tiles/TileMachineController$CraftingStatus;", false);
            methodVisitor.visitInsn(ARETURN);
            Label label11 = new Label();
            methodVisitor.visitLabel(label11);
            methodVisitor.visitLocalVariable("this", "Lcom/warmthdawn/mod/gugu_utils/asm/mixin/AMRT;", null, label0, label11, 0);
            methodVisitor.visitLocalVariable("ctrl", "Lhellfirepvp/modularmachinery/common/tiles/TileMachineController;", null, label0, label11, 1);
            methodVisitor.visitLocalVariable("context", "Lhellfirepvp/modularmachinery/common/crafting/helper/RecipeCraftingContext;", null, label0, label11, 2);
            methodVisitor.visitLocalVariable("check", "Lhellfirepvp/modularmachinery/common/crafting/helper/RecipeCraftingContext$CraftingCheckResult;", null, label3, label11, 3);
            methodVisitor.visitMaxs(3, 4);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "isCompleted", "(Lhellfirepvp/modularmachinery/common/tiles/TileMachineController;Lhellfirepvp/modularmachinery/common/crafting/helper/RecipeCraftingContext;)Z", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(30, label0);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitInsn(IRETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lcom/warmthdawn/mod/gugu_utils/asm/mixin/AMRT;", null, label0, label1, 0);
            methodVisitor.visitLocalVariable("controller", "Lhellfirepvp/modularmachinery/common/tiles/TileMachineController;", null, label0, label1, 1);
            methodVisitor.visitLocalVariable("context", "Lhellfirepvp/modularmachinery/common/crafting/helper/RecipeCraftingContext;", null, label0, label1, 2);
            methodVisitor.visitMaxs(1, 3);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        return classWriter.toByteArray();
    }
}
