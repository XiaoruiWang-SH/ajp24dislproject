package ex1;

import ch.usi.dag.disl.guard.GuardBase;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;

public class StaticFieldAccessGuard extends GuardBase {
    @Override
    public boolean matches(AbstractInsnNode instruction) {
        // Detect static field access (GETSTATIC or PUTSTATIC opcodes)
        return instruction.getOpcode() == Opcodes.GETSTATIC || instruction.getOpcode() == Opcodes.PUTSTATIC;
    }
}
