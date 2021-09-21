package com.warmthdawn.mod.gugu_utils.asm.common;

import org.objectweb.asm.tree.ClassNode;

public interface MyTransformer {
    void transform(ClassNode classNode);

    MyTransformer EMPTY_TRANSFORMER = new MyTransformer() {
        @Override
        public void transform(ClassNode classNode) {

        }
    };
}
